package io.github.eutkin.justlink.admin.repository

import io.github.eutkin.justlink.admin.entity.Source
import org.springframework.data.jpa.repository.JpaRepository

interface SourceRepository : JpaRepository<Source, String>
