package com.example.firstcomposeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun BirthdayGreetingWithText(name: String, from: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Hi I'm $name!", fontSize = 36.sp)
        Text("I come from $from ", fontSize = 24.sp)
    }

}

@Composable
fun BirthdayGreetingWithImage(name: String, from: String) {
    val image = painterResource(R.drawable.androidparty)
    Box {
        Image(
            painter = image,
            contentDescription = "Birthday cake image",
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        BirthdayGreetingWithText(name, from)
    }
}

@Composable
fun Article() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.bg_compose_background),
            null,
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Jetpack Compose tutorial", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
            Text(
                "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Justify,
            )
            Text(
                "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.",
                modifier = Modifier.padding(bottom = 16.dp),
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Composable
fun TaskManager() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_task_completed),
            contentDescription = null,
            modifier = Modifier.wrapContentSize(),
        )
        Text(
            "All task completed",
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Text("Nice work", fontSize = 16.sp)
    }
}

@Composable
fun Quadrant() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(Modifier.weight(1f)) {
            Box(
                Modifier
                    .weight(1f, fill = true)
                    .background(Color.Green)
                    .fillMaxHeight()
            ) {
                Text("hehe")
            }
            Box(
                Modifier
                    .weight(1f)
                    .background(Color.Yellow)
                    .fillMaxHeight()
            ) {
                Text("hehe")
            }
        }
        Row(Modifier.weight(1f)) {
            Box(
                Modifier
                    .weight(1f, fill = true)
                    .background(Color.Blue)
                    .fillMaxHeight()
            ) {
                Text("hehe")
            }
            Box(
                Modifier
                    .weight(1f)
                    .background(Color.Gray)
                    .fillMaxHeight()
            ) {
                Text("hehe")
            }
        }
    }
}