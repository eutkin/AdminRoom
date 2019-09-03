package io.github.eutkin.justlink.admin.repository

import io.github.eutkin.justlink.admin.entity.Parameter
import io.github.eutkin.justlink.admin.entity.ParameterKey
import org.springframework.data.jpa.repository.JpaRepository

interface ParameterRepository : JpaRepository<Parameter, ParameterKey> {
}
