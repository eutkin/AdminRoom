package io.github.eutkin.justlink.admin.entity.converter

import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = false)
class StringsToStringConverter : AttributeConverter<Set<String>, String> {

    override fun convertToDatabaseColumn(attribute: Set<String>?): String? {
        return if (attribute.isNullOrEmpty()) null else attribute.joinToString(SEPARATOR)
    }

    override fun convertToEntityAttribute(dbData: String?): Set<String> {
        return if (dbData.isNullOrBlank())
            emptySet()
        else dbData.split(SEPARATOR).toSet()
    }

    companion object StringsToStringConverter {
        private const val SEPARATOR = ";"
    }
}
