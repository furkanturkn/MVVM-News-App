package com.furkan.mvvm_news_app.util

import com.furkan.mvvm_news_app.R
import com.furkan.mvvm_news_app.data.remote.responses.NewsResponse
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

object ApiResponseUtils {
    fun <T> handleException(e: Throwable): Resource<T> {
        Timber.e(e, "Exception caught: ${e.message}")
        return when (e) {
            is IOException -> Resource.Error(
                UiText.StringResource(R.string.error_couldnt_reach_server)
            )
            is HttpException -> {
                Resource.Error(
                    UiText.DynamicString(e.parseErrorBody())
                )
            }
            else -> Resource.Error(UiText.unknownError())
        }
    }
}
