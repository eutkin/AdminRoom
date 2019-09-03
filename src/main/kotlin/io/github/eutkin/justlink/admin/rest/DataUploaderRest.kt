package io.github.eutkin.justlink.admin.rest

import io.github.eutkin.justlink.admin.service.Uploader
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.InputStream
import kotlin.text.Charsets.UTF_8

@RestController
class DataUploaderRest(val uploader: Uploader) {

    @PostMapping("/upload/parameter", params = ["input"])
    fun uploadParameter(@RequestParam("input") file: MultipartFile) {
        uploader.uploadParameter(file.inputStream)
    }

    @PostMapping("/upload/redirect", params = ["input"])
    fun uploadRedirect(@RequestParam("input") file: MultipartFile) {
        uploader.uploadRedirect(file.inputStream)
    }

    @PostMapping("/upload/parameter", consumes = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun uploadParameter(@RequestBody file: ByteArray) {
        uploader.uploadParameter(file.inputStream())
    }

    @PostMapping("/upload/parameter", consumes = [MediaType.TEXT_PLAIN_VALUE])
    fun uploadParameter(@RequestBody file: String) {
        uploader.uploadParameter(file.byteInputStream(UTF_8))
    }

    @PostMapping("/upload/redirect")
    fun uploadRedirect(@RequestBody file: InputStream) {
        uploader.uploadRedirect(file)
    }

}
