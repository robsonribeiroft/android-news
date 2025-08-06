package br.com.robsonribeiroft.androidnews.data

import androidx.compose.ui.test.isRoot
import androidx.paging.PagingSource
import br.com.robsonribeiroft.androidnews.data.repository.AppRepository
import br.com.robsonribeiroft.androidnews.data.service.ApiService
import br.com.robsonribeiroft.androidnews.model.News
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class AppRepositoryTest {

    private val apiService: ApiService = mockk(relaxed = true)
    private lateinit var repository: AppRepository

    @Before
    fun setup() {
        repository = AppRepository(apiService)
    }


    @Test(expected = Exception::class)
    fun `getNews throws exception when API fails`() = runTest {
        // Arrange
        coEvery { apiService.getFeed(any())}.throws(Exception())
        coEvery { apiService.getFeedPage(any(), any(), any()) }.throws(Exception())

        // Act
        val result = repository.getPaginatedFeedNews("g1")
        assertTrue(result is PagingSource.LoadResult.Page<Int, News>)
    }
}