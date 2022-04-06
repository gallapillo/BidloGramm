package com.gallapillo.bidlogramm.domain.use_cases.auth_use_cases

import com.gallapillo.bidlogramm.domain.use_cases.firebase.*

data class AuthenticationUseCases(
    val isUserAuthenticated: IsUserAuthenticated,
    val firebaseAuthState: FirebaseAuthState,
    val firebaseSignIn: FirebaseSignIn,
    val firebaseSignOut: FirebaseSignOut,
    val firebaseSignUp: FirebaseSignUp
) {
}