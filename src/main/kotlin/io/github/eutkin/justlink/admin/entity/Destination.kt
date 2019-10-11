package io.github.eutkin.justlink.admin.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.net.URL
import java.util.*
import javax.persistence.*
import javax.persistence.AccessType.PROPERTY
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
    @JoinColumn(name = "domain", referencedColumnName = "domain", insertable = false, updatable = false)
    @Access(PROPERTY)
    var parameters : Set<Parameter> = HashSet()

    /*
        Дополнительное поле решает следующую проблему.
        Когда мы читаем из бд Source, он тянет за собой Destination.
        Destination тянет за собой параметры, но набор параметров может быть
        одинаковым для разных Destination (если Destination принадлежат одному домену).
        А Jpa запрещает ссылаться разным entity на одну и ту же коллекцию.
        Поэтому мы сначала присваем коллекцию параметров специальному полю,
        а затем уже полю параметров
     */
    @Transient
    private var tempCachedParameter: Set<Parameter>? = null

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

    @PostLoad
    private fun post() {
        this.parameters = if (Objects.isNull(tempCachedParameter))
            HashSet()
        else
            HashSet<Parameter>(tempCachedParameter!!)
        this.tempCachedParameter = null
    }
}
