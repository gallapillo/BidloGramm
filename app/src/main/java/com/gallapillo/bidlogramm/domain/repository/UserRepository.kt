package com.gallapillo.bidlogramm.domain.repository

import com.gallapillo.bidlogramm.common.Response
import com.gallapillo.bidlogramm.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserDetails(userid: String): Flow<Response<User>>

    fun setUserDetails(userid: String, name: String, userName: String, bio: String, websiteUrl: String) : Flow<Response<Boolean>>
}