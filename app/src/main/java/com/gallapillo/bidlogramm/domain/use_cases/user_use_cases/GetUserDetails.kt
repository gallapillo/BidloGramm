package com.gallapillo.bidlogramm.domain.use_cases.user_use_cases

import com.gallapillo.bidlogramm.common.Response
import com.gallapillo.bidlogramm.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetails @Inject constructor(
    private val repository: UserRepository
) {
    operator fun invoke(userid:String) = repository.getUserDetails(userid = userid)
}