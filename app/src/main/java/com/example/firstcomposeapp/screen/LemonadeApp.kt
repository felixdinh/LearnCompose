package com.example.firstcomposeapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstcomposeapp.R

@Composable
fun LemonadeApp() {
    var currentState by remember { mutableStateOf(1) }
    var squeuezze by remember { mutableStateOf(0) }
    when (currentState) {
        1 -> {
            LemonScreen(text = R.string.tree_txt, description = R.string.tree_state, image = R.drawable.lemon_tree) {
                currentState = 2
                squeuezze = (2..4).random()
            }
        }

        2 -> {
            LemonScreen(
                text = R.string.squeeze_txt,
                description = R.string.lemon_state,
                image = R.drawable.lemon_squeeze
            ) {
                // Decrease the squeeze count by 1 for each click the user performs
                squeuezze--
                // When we're done squeezing the lemon, move to the next step
                if (squeuezze == 0) {
                    currentState = 3
                }
            }
        }

        3 -> {
            LemonScreen(text = R.string.drink_txt, description = R.string.glass_state, image = R.drawable.lemon_drink) {
                currentState = 4
            }
        }

        4 -> {
            LemonScreen(
                text = R.string.empty_txt,
                description = R.string.empty_state,
                image = R.drawable.lemon_restart
            ) {
                currentState = 1
            }
        }


    }
}
@Composable
fun LemonScreen(text: Int, description: Int, image: Int, onImageClick: () -> Unit) {
    Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        Text(stringResource(text), fontSize = 18.sp)
        Spacer(Modifier.height(16.dp))
        Image(
            painterResource(image), contentDescription = stringResource(description), modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    width = 1.dp, Color.Green
                )
                .padding(16.dp)
        )
    }
}