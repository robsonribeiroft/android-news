package br.com.robsonribeiroft.androidnews.component.menu

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.robsonribeiroft.androidnews.model.MenuItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MenuItemComponentTest {

    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()


    @Test
    fun menuItem_displaysTitle() {
        with(composeTestRule) {
            val mockItem = MenuItem(title = "Agro", url = "mock_url")
            setContent {
                MenuItemComponent(menu = mockItem, onClick = {  })
            }


            onNodeWithText(text = "Agro")
                .assertIsDisplayed()
                .performClick()


        }
    }

}