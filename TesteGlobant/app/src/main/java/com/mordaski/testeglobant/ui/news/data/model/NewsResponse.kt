package com.mordaski.testeglobant.ui.news.data.model

import com.google.gson.annotations.SerializedName

class NewsResponse {
    @SerializedName("id_documento")
    var id: Int = -1
    @SerializedName("chapeu")
    var chapeu: String? = null
    @SerializedName("titulo")
    var titulo: String? = ""
    @SerializedName("linha_fina")
    var linhaFina: String? = ""
    @SerializedName("data_hora_publicacao")
    var dataHora: String? = ""
    @SerializedName("url")
    var url: String? = ""
    @SerializedName("imagem")
    var imagem: String? = ""
    @SerializedName("source")
    var source: String? = ""
}