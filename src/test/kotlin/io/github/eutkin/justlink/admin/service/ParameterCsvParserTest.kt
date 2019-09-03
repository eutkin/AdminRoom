package io.github.eutkin.justlink.admin.service

import io.github.eutkin.justlink.admin.entity.Parameter
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource

class ParameterCsvParserTest {

    private val parameterCsvParser : ParameterCsvParser = ParameterCsvParser()

    @Test
    fun parse() {
        val inputStream = ClassPathResource("parameters.csv")
                .inputStream
        val parameters : List<Parameter> = parameterCsvParser.parse(inputStream)

        val firstParameter = parameters[0]
        assert(firstParameter.domain == "stoloto.ru")
        assert(firstParameter.srcName == "utm_source")
        assert(firstParameter.destName == "source")
        assert(!firstParameter.isSlash())

        val secondParameter = parameters[1]
        assert(secondParameter.domain == "stoloto.ru")
        assert(secondParameter.srcName == "utm_source")
        assert(secondParameter.destName == "source")
        assert(!secondParameter.isSlash())

        val thirdParameter = parameters[2]
        assert(thirdParameter.domain == "stoloto.ru")
        assert(thirdParameter.srcName == "utm_medium")
        assert(thirdParameter.destName == null)
        assert(thirdParameter.isSlash())
    }
}
