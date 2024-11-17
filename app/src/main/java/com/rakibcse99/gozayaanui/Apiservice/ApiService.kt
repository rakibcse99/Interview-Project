package com.rakibcse99.gozayaanui.Apiservice

import com.rakibcse99.gozayaanui.models.RecommendedModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/")
    suspend fun getRecommendedList(): Response<MutableList<RecommendedModel>>
}