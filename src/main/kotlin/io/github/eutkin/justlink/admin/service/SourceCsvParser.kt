package io.github.eutkin.justlink.admin.service

import io.github.eutkin.justlink.admin.entity.Destination
import io.github.eutkin.justlink.admin.entity.RedirectType
import io.github.eutkin.justlink.admin.entity.Source
import org.springframework.stereotype.Component
import java.io.InputStream
import java.net.URL

@Component
class SourceCsvParser : Parser<Source> {

    private val splitterArray: Regex = "\\s*,\\s*".toRegex()

    override fun parse(input: InputStream): List<Source> {
        return Parser.read(input)
                .filter(String::isNotEmpty)
                .map { it.split("\\s*;\\s*".toRegex()) }
                .map {
                    val defaultDestination = it[5].toDestinations(default = true)
                    val otherDestinations = it[7].toDestinations()
                    Source(
                            path = it[0],
                            type = it[1].toRedirectType(),
                            random = it[2].toBoolean(),
                            blackList = it[3].toSet(','),
                            blackUrl = it[4].toURL(),
                            description = it[6].ifEmpty { null },
                            destinations = defaultDestination + otherDestinations
                    )
                }
    }

    private fun String.toSet(delimiter: Char): Set<String> {
        return this
                .split("\\s*" + delimiter + "\\s*".toRegex())
                .toSet()
    }

    private fun String.toURL(): URL {
        return URL(this)
    }

    private fun String.toDestinations(default: Boolean = false): Set<Destination> {
        return this
                .split(splitterArray)
                .map { it.toURL() }
                .map { Destination(it, default) }
                .toSet()
    }

    private fun String.toRedirectType(): RedirectType {
        return when (this) {
            "META" -> RedirectType.META
            "JS" -> RedirectType.JS
            "307" -> RedirectType.STATUS_307
            "308" -> RedirectType.STATUS_308
            else -> throw IllegalArgumentException()
        }
    }
}
