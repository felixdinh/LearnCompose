package com.example.firstcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstcomposeapp.model.Affirmation
import com.example.firstcomposeapp.screen.AffirmationApp
import com.example.firstcomposeapp.screen.AffirmationCard
import com.example.firstcomposeapp.ui.theme.FirstComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstComposeAppTheme {
                // A surface container using the 'background' color from the theme
                AffirmationApp()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun DefaultPreview() {
    FirstComposeAppTheme {
//        BirthdayGreetingWithImage(name = "Felix", from = "Viet Nam")
//        Article()
//        TaskManager()
//        lemonadeApp()
        //CalculateTipScreen()
        //ArtSpaceScreen()
        AffirmationCard(affirmation = Affirmation( R.string.affirmation1,R.drawable.image1))
    }
}