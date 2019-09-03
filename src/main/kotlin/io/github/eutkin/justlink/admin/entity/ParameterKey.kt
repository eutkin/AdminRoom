package io.github.eutkin.justlink.admin.entity

import java.io.Serializable
import javax.persistence.Id

class ParameterKey() : Serializable {

    @Id
    lateinit var domain: String

    @Id
    lateinit var srcName: String


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ParameterKey

        if (domain != other.domain) return false
        if (srcName != other.srcName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = domain.hashCode()
        result = 31 * result + srcName.hashCode()
        return result
    }
}
