package com.gallapillo.bidlogramm.domain.use_cases.firebase

import com.gallapillo.bidlogramm.domain.repository.AuthenticationRepository
import javax.inject.Inject

class FirebaseSignUp @Inject constructor(
    private val repository: AuthenticationRepository
) {
    operator fun invoke(email: String, password: String, userName: String) = repository.firebaseSignUp(email, password, userName)
}