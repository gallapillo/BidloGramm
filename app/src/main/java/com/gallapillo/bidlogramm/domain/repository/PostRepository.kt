package com.gallapillo.bidlogramm.domain.repository

import com.gallapillo.bidlogramm.common.Response
import com.gallapillo.bidlogramm.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    fun getAllPostByUser(userid : String) : Flow<Response<List<Post>>>

    fun uploadPost(
        postImage : String,
        postDescription : String,
        time : Long,
        userId: String,
        userName : String,
        userImage : String
    ) : Flow<Response<Boolean>>
}