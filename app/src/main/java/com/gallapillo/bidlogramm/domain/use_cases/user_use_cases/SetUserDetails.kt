package com.gallapillo.bidlogramm.domain.use_cases.user_use_cases

import com.gallapillo.bidlogramm.domain.repository.UserRepository
import javax.inject.Inject

class SetUserDetails @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(
        userid: String,
        name: String,
        userName: String,
        bio: String,
        websiteUrl: String,
    ) = repository.setUserDetails(
        userid = userid,
        name = name,
        userName = userName,
        bio = bio,
        websiteUrl = websiteUrl
    )
}