package com.example.firstcomposeapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.example.firstcomposeapp.screen.CalculateTipScreen
import com.example.firstcomposeapp.ui.theme.FirstComposeAppTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent(){
        composeTestRule.setContent{
            FirstComposeAppTheme {
                CalculateTipScreen()
            }
        }
        composeTestRule.onNodeWithText("Bill amount").performTextInput("10")
        composeTestRule.onNodeWithText("Tip(%)").performTextInput("20")
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip amount: $expectedTip").assertExists(
            "No node with this text was found."
        )
    }
}