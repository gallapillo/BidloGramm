package com.gallapillo.bidlogramm.domain.use_cases.firebase

import com.gallapillo.bidlogramm.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignOut @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke() = repository.firebaseSignOut()
}