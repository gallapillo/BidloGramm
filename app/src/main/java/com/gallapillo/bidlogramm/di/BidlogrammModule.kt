package com.gallapillo.bidlogramm.di

import com.gallapillo.bidlogramm.data.AuthenticationRepositoryImpl
import com.gallapillo.bidlogramm.data.PostRepositoryImpl
import com.gallapillo.bidlogramm.data.UserRepositoryImpl
import com.gallapillo.bidlogramm.domain.repository.AuthenticationRepository
import com.gallapillo.bidlogramm.domain.repository.PostRepository
import com.gallapillo.bidlogramm.domain.repository.UserRepository
import com.gallapillo.bidlogramm.domain.use_cases.auth_use_cases.AuthenticationUseCases
import com.gallapillo.bidlogramm.domain.use_cases.firebase.*
import com.gallapillo.bidlogramm.domain.use_cases.post_use_cases.GetPostUser
import com.gallapillo.bidlogramm.domain.use_cases.post_use_cases.PostUseCases
import com.gallapillo.bidlogramm.domain.use_cases.post_use_cases.UploadPost
import com.gallapillo.bidlogramm.domain.use_cases.user_use_cases.GetUserDetails
import com.gallapillo.bidlogramm.domain.use_cases.user_use_cases.SetUserDetails
import com.gallapillo.bidlogramm.domain.use_cases.user_use_cases.UserUseCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BidlogrammModule {

    @Singleton
    @Provides
    fun provideFirebaseAuthentication(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFireBaseFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFireBaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun provideAuthenticationRepository(auth: FirebaseAuth, firestore: FirebaseFirestore): AuthenticationRepository {
        return AuthenticationRepositoryImpl(auth = auth, firestore = firestore)
    }

    @Singleton
    @Provides
    fun provideAuthUsesCases(repository: AuthenticationRepositoryImpl) = AuthenticationUseCases(
        isUserAuthenticated = IsUserAuthenticated(repository = repository),
        firebaseAuthState = FirebaseAuthState(repository = repository),
        firebaseSignOut = FirebaseSignOut(repository),
        firebaseSignIn = FirebaseSignIn(repository),
        firebaseSignUp = FirebaseSignUp(repository)
    )

    @Singleton
    @Provides
    fun provideUserRepository(firebaseFirestore: FirebaseFirestore): UserRepository {
        return UserRepositoryImpl(firebaseFirestore = firebaseFirestore)
    }

    @Singleton
    @Provides
    fun provideUserUseCases(repository: UserRepository) = UserUseCases(
        getUserDetails = GetUserDetails(repository = repository),
        setUserDetails = SetUserDetails(repository = repository)
    )

    @Provides
    @Singleton
    fun providePostRepository(firebaseFirestore: FirebaseFirestore): PostRepository {
        return PostRepositoryImpl(firebaseFirestore = firebaseFirestore)
    }

    @Provides
    @Singleton
    fun providesPostsUseCases(repository: PostRepository) = PostUseCases(
        getAllPosts = GetPostUser(repository = repository),
        uploadPost = UploadPost(repository = repository)
    )

}