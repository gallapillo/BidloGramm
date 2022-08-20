package com.gallapillo.bidlogramm.data

import com.gallapillo.bidlogramm.common.Response
import com.gallapillo.bidlogramm.domain.model.Post
import com.gallapillo.bidlogramm.domain.repository.PostRepository
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : PostRepository {

    private var isOperationSuccessful = false

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAllPostByUser(userid: String): Flow<Response<List<Post>>> = callbackFlow {
        val snapshotListener = firebaseFirestore.collection("posts")
            .whereNotEqualTo("userId", userid)
            .addSnapshotListener { value, error ->
                val response = if (value != null) {
                    val postList = value.toObjects(Post::class.java)
                    Response.Success<List<Post>>(postList)
                } else {
                    Response.Error(error?.message?:error.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun uploadPost(
        postImage: String,
        postDescription: String,
        time: Long,
        userId: String,
        userName: String,
        userImage: String
    ): Flow<Response<Boolean>> = flow {
        isOperationSuccessful = false
        try {
            val postId = firebaseFirestore.collection("posts").document().id
            val post = Post(
                postImage = postImage,
                postDescription = postDescription,
                postId = postId,
                time = time,
                userName = userName,
                userImage = userImage,
                userId = userId
            )
            firebaseFirestore.collection("posts").document(postId).set(post)
                .addOnSuccessListener {
                    isOperationSuccessful = true
                }.await()
            if (isOperationSuccessful) {
                emit(Response.Success(isOperationSuccessful))
            } else {
                emit(Response.Error("Post Upload error!"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.localizedMessage?:"An Unknown error occurred"))
        }
    }
}