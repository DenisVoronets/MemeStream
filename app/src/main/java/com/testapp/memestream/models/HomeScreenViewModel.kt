package com.testapp.memestream.models

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testapp.memestream.data.Mem
import com.testapp.memestream.data.Post
import com.testapp.memestream.data.User
import com.testapp.memestream.network.apiRequests.PostApiRequests
import com.testapp.memestream.network.apiRequests.UserApiRequests
import com.testapp.memestream.network.apiResponses.ApiResult
import com.testapp.memestream.utils.Constances.logger
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val postApiRequests: PostApiRequests,
    private val userApiRequests: UserApiRequests
) : ViewModel() {
    private val _usersWithPosts = MutableStateFlow<List<Pair<User,Post>>>(emptyList())
    private val _memesUrls = MutableStateFlow<List<Mem>>(emptyList())
    val usersWithPosts: StateFlow<List<Pair<User, Post>>> = _usersWithPosts.asStateFlow()
    val memesUrls: StateFlow<List<Mem>> = _memesUrls.asStateFlow()

    init {
        fetchUsersWithPosts()
    }
    private fun fetchUsersWithPosts() {
        viewModelScope.launch {
            val users = fetchUsers()
            val userPosts = users.map { user ->
                async { fetchPostsForUser(user) }
            }.awaitAll().flatten()
            _usersWithPosts.value = userPosts
            val memes = fetchMemes(userPosts.size)
            _memesUrls.value = memes
        }
    }
    private suspend fun fetchUsers() : List<User> {
        val result = userApiRequests.getUsers()
        return when (result) {
            is ApiResult.Success -> result.responseBody
            else -> emptyList()
        }
    }

    private suspend fun fetchPostsForUser(user: User): List<Pair<User, Post>> {
        val result = postApiRequests.getPosts(user.id, "2")
        return when (result) {
            is ApiResult.Success -> result.responseBody.map { post -> user to post }
            else -> emptyList()
        }
    }
    private suspend fun fetchMemes(amountOfMemes:Int): List<Mem> {
        val memes = postApiRequests.fetchMemes(amountOfMemes)
        return when (memes) {
            is ApiResult.Success -> memes.responseBody.map { mem ->
                mem.copy(
                    url = mem.url.replace("'", "").replace("webp", "jpg")
                )
            }
            else -> emptyList()
        }
    }
}