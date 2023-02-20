package com.example.firstcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.firstcomposeapp.data.Datasource
import com.example.firstcomposeapp.screen.widget.HeroCardList
import com.example.firstcomposeapp.ui.theme.FirstComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstComposeAppTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    Scaffold(topBar = {
        Box(
            Modifier.fillMaxWidth(),
            Alignment.Center
        ) {
            Text(
                stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center
            )
        }
    }) { paddingValues ->
        HeroCardList(Datasource().heroes, Modifier.padding(paddingValues.calculateBottomPadding()))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    FirstComposeAppTheme {
        MyApp()
    }
}