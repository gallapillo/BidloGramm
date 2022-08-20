package com.gallapillo.bidlogramm.presentation.profile.posts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gallapillo.bidlogramm.common.Response
import com.gallapillo.bidlogramm.domain.model.Post
import com.gallapillo.bidlogramm.domain.use_cases.post_use_cases.PostUseCases
import com.gallapillo.bidlogramm.domain.use_cases.user_use_cases.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
) : ViewModel() {

    private val _postState = mutableStateOf<Response<List<Post>>>(Response.Loading)
    var postState : State<Response<List<Post>>> = _postState

    private val _uploadPostState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    var uploadPostState : State<Response<Boolean>> = _uploadPostState

    fun getAllPostByUser(userid : String) {
        viewModelScope.launch {
            postUseCases.getAllPosts(userid).collect {
                _postState.value = it
            }
        }
    }

    fun uploadPost(
        postDescription : String,
        postImage : String,
        userId : String,
        userName : String,
        userImage : String,
        time : Long
    ) {
        viewModelScope.launch {
            postUseCases.uploadPost(
                userid = userId,
                userName = userName,
                userImage = userImage,
                postDescription = postDescription,
                time = time,
                postImage = postImage
            )
        }
    }
}