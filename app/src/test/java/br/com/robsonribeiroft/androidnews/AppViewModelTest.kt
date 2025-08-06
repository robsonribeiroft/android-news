package br.com.robsonribeiroft.androidnews


import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import br.com.robsonribeiroft.androidnews.data.repository.AppRepository
import br.com.robsonribeiroft.androidnews.domain.GetFeedNewsByProductPaginatedUseCase
import br.com.robsonribeiroft.androidnews.mock.mockFeedResponseDto
import br.com.robsonribeiroft.androidnews.model.News
import br.com.robsonribeiroft.androidnews.model.toNewsList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AppViewModelTest {

    private val repository: AppRepository = mockk(relaxed = true)
    private lateinit var getFeedNewsPag: GetFeedNewsByProductPaginatedUseCase
    private lateinit var viewModel: AppViewModel

    @get:Rule
    val dispatcherRule = MainDispatcherRule()


    @Before
    fun setup() {
        getFeedNewsPag = GetFeedNewsByProductPaginatedUseCase(repository)
        viewModel = AppViewModel(getFeedNewsPag)
    }

    @Test
    fun `newsFlow emits expected articles for product`() = runTest {
        // Arrange
        val news = mockFeedResponseDto.toNewsList()
        val pagingData = PagingData.from(news)


        coEvery { repository.getPaginatedFeedNews(any()) } returns flowOf(pagingData)

        val flow = viewModel.getFeedNewsByProduct("g1")

        // Act
        val result: List<News> = flow.first().collectData()

        // Assert
        assertEquals(result.size, 1)
        assertEquals(result.first().title, "title")
    }
}

suspend fun <T : Any> PagingData<T>.collectData(): List<T> {
    val differ = AsyncPagingDataDiffer(
        diffCallback = DiffCallbackStub<T>(),
        updateCallback = NoopListCallback(),
        mainDispatcher = Dispatchers.Main,
        workerDispatcher = Dispatchers.Main
    )

    differ.submitData(this)
    return differ.snapshot().items
}

class NoopListCallback : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}

class DiffCallbackStub<T : Any> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
}