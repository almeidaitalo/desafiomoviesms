package com.ifs.desafiomovies.data.remote.helper

import com.ifs.desafiomovies.data.extensions.getThrowable
import com.ifs.desafiomovies.data.exception.ResponseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

suspend fun safeCall(call: suspend () -> Response<*>): Any? {
    return withContext(Dispatchers.IO) {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                response.body()
            } else {
                response.getThrowable()
            }
        }
        catch (e:IOException){
            ResponseError.IOErrorException
        }
    }
}