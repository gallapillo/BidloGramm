package com.gallapillo.bidlogramm.domain.model

data class Post(
    val postId : String = "",
    val postImage : String = "",
    var postDescription : String = "",
    var time : Long? = null,
    var userId : String = "",
    var userImage : String = "",
    var userName : String = ""
)
