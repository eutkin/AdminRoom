package io.github.eutkin.justlink.admin.service

import java.io.InputStream
import kotlin.text.Charsets.UTF_8

interface Parser<T> {

    fun parse(input : InputStream) : List<T>

    companion object Parser {
        fun read(input : InputStream) : List<String> {
            return input.bufferedReader(UTF_8).readLines()
        }
    }
}
