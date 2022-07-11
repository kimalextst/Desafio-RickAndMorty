package br.com.zup.rickandmorty.data.model


import com.google.gson.annotations.SerializedName

data class Info(
    @SerializedName("count")
    var count: Int = 0,
    @SerializedName("next")
    var next: String = "",
    @SerializedName("pages")
    var pages: Int = 0,
    @SerializedName("prev")
    var prev: Any? = null
)