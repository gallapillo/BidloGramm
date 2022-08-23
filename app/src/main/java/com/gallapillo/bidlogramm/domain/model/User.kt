package com.gallapillo.bidlogramm.domain.model

import com.gallapillo.bidlogramm.common.Constant

data class User(
    var name: String = "",
    var userName: String = "",
    var userId: String = "",
    var email : String = "",
    var password: String = "",
    var imageURl : String = Constant.DEFAULT_PHOTO_URL,
    var following : List<String> = emptyList(),
    var followers : List<String> = emptyList(),
    var totalPosts : String = "0",
    var bio : String = "",
    var url : String = "",
    var isConfirmed : Boolean = false
)
