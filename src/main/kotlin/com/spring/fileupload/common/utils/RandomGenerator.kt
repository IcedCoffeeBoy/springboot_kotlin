package com.spring.fileupload.common.utils

import java.util.*

object RandomGenerator {
    fun generate(): String {
        return toString(UUID.randomUUID())
    }

    private fun toString(uuid: UUID): String {
        return digits(uuid.mostSignificantBits shr 32, 8) +
                digits(uuid.mostSignificantBits shr 16, 4) +
                digits(uuid.mostSignificantBits, 4) +
                digits(uuid.leastSignificantBits shr 48, 4) +
                digits(uuid.leastSignificantBits, 12)
    }

    private fun digits(`val`: Long, digits: Int): String {
        val hi = 1L shl digits * 4
        return java.lang.Long.toHexString(hi or (`val` and hi - 1)).substring(1)
    }
}