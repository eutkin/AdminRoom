package io.github.eutkin.justlink.admin

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.eutkin.justlink.admin.entity.Source
import io.github.eutkin.justlink.admin.service.SourceCsvParser
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.JsonTest
import org.springframework.core.io.ClassPathResource
import org.springframework.test.context.junit.jupiter.SpringExtension

@JsonTest
@ExtendWith(SpringExtension::class)
class SourceToJsonTest {

    @Autowired
    lateinit var mapper: ObjectMapper

    @Test
    fun serialize() {
        val source: Source = SourceCsvParser().parse(ClassPathResource("sources.csv").inputStream)
                .first()
        val json = mapper.writeValueAsString(source)
    }
}
