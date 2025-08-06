package br.com.robsonribeiroft.androidnews.data

import androidx.paging.PagingSource
import br.com.robsonribeiroft.androidnews.data.repository.NewsPagingSource
import br.com.robsonribeiroft.androidnews.data.service.ApiService
import br.com.robsonribeiroft.androidnews.mock.mockFeedResponseDto
import br.com.robsonribeiroft.androidnews.model.toNewsList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NewsPagingSourceTest {

    private val apiService: ApiService = mockk(relaxed = true)
    private lateinit var pagingSource: NewsPagingSource

    @Before
    fun setup() {
        pagingSource = NewsPagingSource(apiService, product = "g1")
    }

    @Test
    fun `load returns page when successful`() {
        runBlocking {

            val mockResponse = mockFeedResponseDto

            coEvery { apiService.getFeed("g1") } returns mockResponse

            val result = pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 1,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )

            val expected = PagingSource.LoadResult.Page(
                data = mockFeedResponseDto.toNewsList(),
                prevKey = null,
                nextKey = 2
            )

            assertEquals(expected, result)
        }
    }

    @Test
    fun `load returns error when exception is thrown`() = runBlocking {
        coEvery { apiService.getFeed(any()) } throws IOException()

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertTrue(result is PagingSource.LoadResult.Error)
    }

}