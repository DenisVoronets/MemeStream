package com.testapp.memestream

import com.testapp.memestream.data.Post
import com.testapp.memestream.data.User
import com.testapp.memestream.models.HomeScreenViewModel
import com.testapp.memestream.network.apiRequests.PostApiRequests
import com.testapp.memestream.network.apiRequests.UserApiRequests
import io.ktor.http.HttpStatusCode
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlin.test.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.component.get
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.test.KoinTest

//Doesnt work need to finish
@ExperimentalCoroutinesApi
class HomeScreenViewModelTest : KoinTest {
    private lateinit var homeScreenViewModel: HomeScreenViewModel

    private val testModules = module {
        single<PostApiRequests> { PostApiRequests(get()) }
        single<UserApiRequests> {
            UserApiRequests(get())
        }
        single {
            mockHttpClient(HttpStatusCode.OK)
        }
        viewModel { HomeScreenViewModel(get(), get()) }
    }

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        startKoin {
            modules(testModules)
        }
        homeScreenViewModel = get<HomeScreenViewModel>()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        stopKoin()
    }
    @Test
    fun `should inject my components`() = runTest {
        assertNotNull(homeScreenViewModel)
    }
    @Test
    fun `fetchUsersWithPosts should update usersWithPosts and memesUrls`() = runTest {
        assertNotNull(homeScreenViewModel.usersWithPosts.value)
        assertEquals(listOf(user to post), homeScreenViewModel.usersWithPosts.value)
        assertNotNull(homeScreenViewModel.memesUrls.value)
        assertEquals(
            listOf(mem.copy(url = "http://test.com/meme.jpg")),
            homeScreenViewModel.memesUrls.value
        )
    }

    @Test
    fun `fetchUsersWithPosts should handle API failure gracefully`() = runTest {
        assertTrue(homeScreenViewModel.usersWithPosts.value.isEmpty())
        assertTrue(homeScreenViewModel.memesUrls.value.isEmpty())
    }

    @Test
    fun `fetchUsersWithPosts should handle empty user list`() = runTest {
        assertTrue(homeScreenViewModel.usersWithPosts.value.isEmpty())
        assertTrue(homeScreenViewModel.memesUrls.value.isEmpty())
    }
}