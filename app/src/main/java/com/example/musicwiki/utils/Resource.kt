package com.example.musicwiki.utils

enum class Status {
    SUCCESS,
    ERROR
}

sealed class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    data class Success<out R>(val response: R) : Resource<R>(
        status = Status.SUCCESS,
        data = response,
        message = null
    )

    data class Error(val exception: String? = null) : Resource<Nothing>(
        status = Status.ERROR,
        data = null,
        message = exception
    )
}