package io.github.eutkin.justlink.admin.rest

import io.github.eutkin.justlink.admin.entity.Source
import io.github.eutkin.justlink.admin.repository.SourceRepository
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.POST
import org.springframework.web.bind.annotation.RequestMethod.PUT

@RestController
@RequestMapping("/api/redirect")
class RedirectRest(val redirectRepository: SourceRepository) {

    @GetMapping
    fun findAll() : List<Source> {
        return redirectRepository.findAll();
    }

    @GetMapping("/{path}")
    fun findOne(@PathVariable path :String) : Source {
        return redirectRepository.findById(path).get()
    }

    @RequestMapping(method = [POST, PUT])
    fun merge(@RequestBody source : Source) : Source {
        return redirectRepository.save(source)
    }

    @DeleteMapping("/{path}")
    fun delete(@PathVariable path : String) {
        redirectRepository.deleteById(path)
    }
}
