package br.com.robsonribeiroft.androidnews.component.menu

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import br.com.robsonribeiroft.androidnews.model.MenuItem
import org.junit.Rule
import org.junit.Test

class MenuListComponentTest {
    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun menuList_displayed() {
        with(composeTestRule) {
            val mockItem = listOf(MenuItem(title = "Agro", url = "mock_url"))
            setContent {
                MenuListComponent(modifier = Modifier, menuItems = mockItem, onItemClick = {  })
            }

            onNodeWithTag(MenuListComponentTestTag.TAG)
                .assertIsDisplayed()
                .performScrollToIndex(0)
                .performClick()
        }
    }
}