package com.spring.fileupload.filecontent.service

import com.google.common.hash.Hashing
import com.google.common.io.ByteSource
import org.slf4j.LoggerFactory

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.io.InputStream

object FileContentUtils {
    private val log = LoggerFactory.getLogger(javaClass)

    fun getSha256(file: MultipartFile): String {
        return try {
            val byteSource: ByteSource = object : ByteSource() {
                @Throws(IOException::class)
                override fun openStream(): InputStream {
                    return file.inputStream
                }
            }
            byteSource.hash(Hashing.sha256()).toString()
        } catch (e: IOException) {
            log.error("Something went wrong when extracting SHA-256")
            log.error(e.message)
            throw RuntimeException("Something went wrong when uploading file")
        }
    }

    fun getSha256(file: Resource): String {
        return try {
            val byteSource: ByteSource = object : ByteSource() {
                @Throws(IOException::class)
                override fun openStream(): InputStream {
                    return file.inputStream
                }
            }
            byteSource.hash(Hashing.sha256()).toString()
        } catch (e: IOException) {
            log.error("Something went wrong when extracting SHA-256")
            log.error(e.message)
            throw RuntimeException("Something went wrong when uploading file")
        }
    }

    fun convertMultipartToBytes(file: MultipartFile): ByteArray {
        return try {
            val byteSource: ByteSource = object : ByteSource() {
                @Throws(IOException::class)
                override fun openStream(): InputStream {
                    return file.inputStream
                }
            }
            byteSource.read()
        } catch (e: IOException) {
            log.error("An error occurred while converting file to bytes")
            log.error(e.message)
            throw RuntimeException("Something went wrong when uploading file")
        }
    }
}