package io.github.eutkin.justlink.admin.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.Table

@Entity
@Table(name = "parameters")
@IdClass(ParameterKey::class)
class Parameter(

        @Id
        val domain: String,

        @Id
        val srcName: String,

        val destName: String?
) {

    fun isSlash() : Boolean = destName.isNullOrBlank()


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Parameter

        if (domain != other.domain) return false
        if (srcName != other.srcName) return false
        if (destName != other.destName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = domain.hashCode()
        result = 31 * result + srcName.hashCode()
        result = 31 * result + (destName?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "'$domain': $srcName -> $destName)"
    }


}

