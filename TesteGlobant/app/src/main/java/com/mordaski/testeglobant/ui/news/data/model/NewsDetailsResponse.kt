package com.mordaski.testeglobant.ui.news.data.model

import com.google.gson.annotations.SerializedName

class NewsDetailsResponse {
    @SerializedName("documento")
    var documento: Document? = null
}

class Document{
    @SerializedName("url")
    var url: String? = ""
    @SerializedName("source")
    var source: String? = ""
    @SerializedName("produto")
    var produto: String? = ""
    @SerializedName("editoria")
    var editoria: String? = ""
    @SerializedName("subeditoria")
    var subeditoria: String? = ""
    @SerializedName("titulo")
    var titulo: String? = ""
    @SerializedName("credito")
    var credito: String? = ""
    @SerializedName("datapub")
    var datapub: String? = ""
    @SerializedName("horapub")
    var horapub: String? = ""
    @SerializedName("imagem")
    var imagem: String? = ""
    @SerializedName("thumbnail")
    var thumbnail: String? = ""
    @SerializedName("creditoImagem")
    var creditoImagem: String? = ""
    @SerializedName("legendaImagem")
    var legendaImagem: String? = ""
    @SerializedName("origem")
    var origem: String? = ""
    @SerializedName("id")
    var id: String? = ""
    @SerializedName("corpoformatado")
    var corpoformatado: String? = ""
}