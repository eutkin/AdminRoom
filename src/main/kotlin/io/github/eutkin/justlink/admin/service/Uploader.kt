package io.github.eutkin.justlink.admin.service

import io.github.eutkin.justlink.admin.entity.Parameter
import io.github.eutkin.justlink.admin.entity.Source
import io.github.eutkin.justlink.admin.repository.ParameterRepository
import io.github.eutkin.justlink.admin.repository.SourceRepository
import org.springframework.stereotype.Service
import java.io.InputStream

@Service
class Uploader(
        val parameterParser: Parser<Parameter>,
        val parameterRepository: ParameterRepository,
        val redirectParser : Parser<Source>,
        val sourceRepository: SourceRepository
) {

    fun uploadParameter(file : InputStream) : Unit {
        val parameters = parameterParser.parse(file)
        parameterRepository.saveAll(parameters)
    }

    fun uploadRedirect(file: InputStream) : Unit {
        val sources = redirectParser.parse(file)
        sourceRepository.saveAll(sources)
    }
}
