package com.keabyte.transactionengine.mapper

import java.util.*

class MapperUtils {

    companion object {
        const val GENERATE_UUID_EXPRESSION = "java(com.keabyte.transactionengine.mapper.MapperUtils.generateUUID())"

        @JvmStatic
        fun generateUUID(): UUID {
            return UUID.randomUUID()
        }
    }
}