package io.github.eutkin.justlink.admin.service

import io.github.eutkin.justlink.admin.entity.Parameter
import org.springframework.stereotype.Component
import java.io.InputStream

@Component
class ParameterCsvParser : Parser<Parameter> {

    override fun parse(input: InputStream): List<Parameter> {
        val lines = Parser.read(input)
        return lines
                .filter(String::isNotBlank)
                .flatMap { line ->
                    val values = line.split(Regex("\\s*;\\s*"))
                    if (values.size % 2 == 0) {
                        // FIXME добавить логгирование об ошибочной строке
                        return emptyList()
                    }
                    val domain = values.first()
                    values
                            .filterIndexed { index, _ -> index != 0 }
                            .windowed(2, 2)
                            .map { params -> Parameter(domain, params[0], params[1].ifEmpty { null }) }
                }
    }
}
