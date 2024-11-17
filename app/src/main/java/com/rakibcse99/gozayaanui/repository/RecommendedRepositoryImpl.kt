package com.rakibcse99.gozayaanui.repository

import com.rakibcse99.gozayaanui.Apiservice.ApiService
import com.rakibcse99.gozayaanui.base.ErrorResponse
import com.rakibcse99.gozayaanui.base.Resource
import com.rakibcse99.gozayaanui.models.RecommendedModel
import javax.inject.Inject

class RecommendedRepositoryImpl @Inject constructor(private val apiService: ApiService):
    RecommendedRepository {
    override suspend fun getRecommendedList(): Resource<MutableList<RecommendedModel>, ErrorResponse> {
        val response = apiService.getRecommendedList()
        return if (response.isSuccessful) {
            Resource.success(response.body())
        } else {
            Resource.error(
                null,
                error = ErrorResponse(
                    success = false,
                    message = response.message(),
                    code = response.code()
                )
            )
        }
    }


}