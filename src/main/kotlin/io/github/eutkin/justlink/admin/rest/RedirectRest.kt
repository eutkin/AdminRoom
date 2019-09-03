package io.github.eutkin.justlink.admin.rest

import io.github.eutkin.justlink.admin.entity.Source
import io.github.eutkin.justlink.admin.repository.SourceRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.Collections.singletonList

@RestController
@RequestMapping("/api/redirect")
class RedirectRest(val redirectRepository: SourceRepository) {

    @GetMapping
    fun findAll() : List<Source> {
        return redirectRepository.findById("stoloto").toList()
    }

    private fun <U> Optional<U>.toList() : List<U> {
        return this.map { singletonList(it) }.orElse(emptyList())
    }
}
