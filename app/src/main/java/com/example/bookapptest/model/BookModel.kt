package com.example.bookapptest.model

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("kind") val kind: String,
    @SerializedName("totalItems") val totalItems: Int,
    @SerializedName("items") val items: List<Book>
)

data class Book(
    @SerializedName("id") val title: String,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("imageLinks") val imageLink: ImageLink?,
)

data class ImageLink(
    @SerializedName("smallThumbnail") val smallThumbnail: String?
)