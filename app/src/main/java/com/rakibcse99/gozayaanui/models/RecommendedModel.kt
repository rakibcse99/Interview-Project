package com.rakibcse99.gozayaanui.models


import androidx.annotation.Keep
import java.io.Serializable
import com.google.gson.annotations.SerializedName


@Keep
data class RecommendedModel(
    @SerializedName("currency")
    val currency: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("detail_images")
    val detailImages: MutableList<String>,
    @SerializedName("fare")
    val fare: Double,
    @SerializedName("fare_unit")
    val fareUnit: String,
    @SerializedName("hero_image")
    val heroImage: String,
    @SerializedName("is_available")
    val isAvailable: Boolean,
    @SerializedName("location")
    val location: String,
    @SerializedName("property_name")
    val propertyName: String,
    @SerializedName("rating")
    val rating: Double
):Serializable