package io.github.eutkin.justlink.admin.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.net.URL
import java.util.*
import javax.persistence.*
import javax.persistence.FetchType.EAGER
import kotlin.collections.HashSet

@Entity
@Table(name = "destinations")
class Destination(

        val url : URL,

        val byDefault : Boolean = false

) : Serializable {

    @Id
    val id : UUID = UUID.randomUUID()

    @ManyToOne
    @JoinColumn(name= "source_path", insertable = false, updatable = false)
    @JsonIgnore
    lateinit var source : Source

    @OneToMany(fetch = EAGER)
    @JoinColumn(name = "domain", referencedColumnName = "domain")
    var parameters : Set<Parameter> = HashSet()

    val domain : String = url.host

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Destination

        if (id != other.id) return false
        if (url != other.url) return false
        if (byDefault != other.byDefault) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + byDefault.hashCode()
        return result
    }
}
