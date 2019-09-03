package io.github.eutkin.justlink.admin.entity

import io.github.eutkin.justlink.admin.entity.converter.StringsToStringConverter
import java.net.URL
import javax.persistence.*
import javax.persistence.CascadeType.ALL
import javax.persistence.EnumType.STRING
import javax.persistence.FetchType.EAGER

@Entity
@Table(name = "sources")
class Source(

        @Id
        val path: String,

        @Enumerated(STRING)
        val type: RedirectType,

        @Column(name = "is_random")
        val random: Boolean,

        @Convert(converter = StringsToStringConverter::class)
        val blackList: Set<String>,

        val blackUrl: URL,

        val description: String?,

        @OneToMany(mappedBy = "source", fetch = EAGER, cascade = [ALL], orphanRemoval = true)
        val destinations: Set<Destination>
) {
    init {
        this.destinations.map { it.source = this }
    }

    fun getDestinations(): List<Destination> {
        return destinations.filterNot(Destination::byDefault)
    }

    fun getDefaultDestination(): Destination {
        return destinations.first(Destination::byDefault)
    }
}
