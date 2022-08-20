package com.gallapillo.bidlogramm.domain.use_cases.post_use_cases

import com.gallapillo.bidlogramm.domain.repository.PostRepository
import javax.inject.Inject

class GetPostUser @Inject constructor(
    private val repository: PostRepository
) {
    operator fun invoke(userid: String) = repository.getAllPostByUser(userid = userid)
}