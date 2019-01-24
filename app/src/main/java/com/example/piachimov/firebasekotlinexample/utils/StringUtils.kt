package com.example.piachimov.firebasekotlinexample.utils

object StringUtils {

    fun isEmpty(value: String?): Boolean {
        return value == null || value.length == 0 || value === ""
    }
}