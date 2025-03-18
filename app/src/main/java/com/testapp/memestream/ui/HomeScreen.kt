package com.testapp.memestream.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.testapp.memestream.R
import com.testapp.memestream.data.Mem
import com.testapp.memestream.data.Post
import com.testapp.memestream.data.User
import com.testapp.memestream.models.HomeScreenViewModel
import com.testapp.memestream.ui.customViews.IconButtonWithText
import com.testapp.memestream.ui.customViews.PostPlaceHolder
import com.testapp.memestream.ui.customViews.TextRegular
import com.testapp.memestream.ui.theme.Black
import com.testapp.memestream.ui.theme.Red
import com.testapp.memestream.ui.theme.White
import com.testapp.memestream.ui.theme.regular
import com.testapp.memestream.utils.Constances.logger
import kotlin.random.Random

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel
) {
    val usersWithPosts = homeScreenViewModel.usersWithPosts.collectAsState()
    val memesUrls = homeScreenViewModel.memesUrls.collectAsState()
    if (usersWithPosts.value.isEmpty()) { //Not best solution, rework it
        PostPlaceHolder()
    } else {
        HomeScreenContent(usersWithPosts.value, memesUrls.value)
    }
}

@Composable
fun HomeScreenContent(
    usersWithPosts: List<Pair<User, Post>>,
    memesUrls: List<Mem>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Black),
        state = rememberLazyListState(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(usersWithPosts) { index, (user, post) ->
            PostItem(user, post, memesUrls.getOrNull(index)?.url)
            if (index != usersWithPosts.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp),
                    thickness = 1.dp,
                    color = White
                )
            }
        }
    }
}

@Composable
fun PostItem(
    user: User,
    post: Post,
    memeUrl: String?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PostHeader(userName = user.name)
        PostBody(
            postCaption = post.title,
            postImage = memeUrl
        )
        PostFooter()// Todo add real comments
    }
}

@Composable
fun PostHeader(
    userName: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_icon_filled),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.dp, White, CircleShape)

        )
        TextRegular(
            text = userName,
            textStyle = regular().copy(fontWeight = FontWeight.SemiBold),
        )
    }
}

@Composable
fun PostBody(
    postImage: String?,
    postCaption: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(horizontal = 5.dp),
            alignment = Alignment.Center,
            model = postImage,
            contentScale = ContentScale.Fit,
            onSuccess = {
                logger("image loaded")
            },
            error = painterResource(R.drawable.img),
            contentDescription = ""
        )
        TextRegular(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = postCaption,
            textStyle = regular().copy(textAlign = TextAlign.Start),
        )
    }
}

@Composable
fun PostFooter() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        var isLiked by remember { mutableStateOf(false) } //Todo transfer to viewModel
        var likeCount by remember { mutableIntStateOf(Random.nextInt(9, 99)) } //Todo transfer to viewModel
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButtonWithText(
                text = likeCount.toString(),
                iconTint = if (isLiked) Red else White,
                icon = painterResource(id = if (isLiked) R.drawable.like_icon_filled else R.drawable.like_icon_outlined),
                onIconClick = {
                    isLiked = !isLiked
                    likeCount = if (isLiked) likeCount + 1 else likeCount - 1
                }
            )
            IconButtonWithText(
                text = "23",
                iconTint = White,
                icon = painterResource(id = R.drawable.comments_outlined),
                onIconClick = {}
            )
        }
    }
}