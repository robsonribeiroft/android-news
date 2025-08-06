package br.com.robsonribeiroft.androidnews.component.toolbar

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import br.com.robsonribeiroft.androidnews.component.menu.MenuListComponent
import br.com.robsonribeiroft.androidnews.component.menu.MenuListComponentTestTag
import br.com.robsonribeiroft.androidnews.model.MenuItem
import org.junit.Rule
import org.junit.Test


class TopBarComponentTest {

    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun topBar_displayed() {
        with(composeTestRule) {
            val mockItem = listOf(MenuItem(title = "Agro", url = "mock_url"))
            setContent {
                TopBarComponent(showBackButton = true) {  }
            }

            onNodeWithTag(TopBarComponentTestTag.BUTTON_TAG)
                .assertIsDisplayed()
                .performClick()
            onNodeWithTag(TopBarComponentTestTag.IMAGE_TAG)
                .assertIsDisplayed()
        }
    }
}