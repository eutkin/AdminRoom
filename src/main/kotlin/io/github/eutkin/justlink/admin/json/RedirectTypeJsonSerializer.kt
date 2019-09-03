package io.github.eutkin.justlink.admin.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import io.github.eutkin.justlink.admin.entity.RedirectType
import io.github.eutkin.justlink.admin.entity.RedirectType.*
import org.springframework.boot.jackson.JsonComponent

@JsonComponent
class RedirectTypeJsonSerializer : JsonSerializer<RedirectType>() {
    override fun serialize(value: RedirectType?, jgen: JsonGenerator, serializers: SerializerProvider?) {
        when (value) {
            JS, META -> jgen.writeString(value.name)
            STATUS_307, STATUS_308 -> jgen.writeString(value.name.takeLast(3))
            else -> jgen.writeNull()
        }
    }

}
