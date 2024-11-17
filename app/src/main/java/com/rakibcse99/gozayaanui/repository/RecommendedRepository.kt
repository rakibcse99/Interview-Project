package com.rakibcse99.gozayaanui.repository

import com.rakibcse99.gozayaanui.base.ErrorResponse
import com.rakibcse99.gozayaanui.base.Resource
import com.rakibcse99.gozayaanui.models.RecommendedModel

interface RecommendedRepository {
    suspend fun getRecommendedList(): Resource<MutableList<RecommendedModel>, ErrorResponse>
}