package io.github.eutkin.justlink.admin.service

import io.github.eutkin.justlink.admin.entity.Source
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource

class SourceCsvParserTest {

    private val parser : SourceCsvParser = SourceCsvParser()

    @Test
    fun parse() {
        val csv = ClassPathResource("sources.csv").inputStream
        val sources : List<Source> = parser.parse(csv)
    }
}
