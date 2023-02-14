package com.example.firstcomposeapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ArtWork(val image: Int, val title: String, val artist: String, val year: Int)

@Composable
fun ArtSpaceScreen() {
    val gallery = listOf(
        ArtWork(image = R.drawable.art_1, title = "Ehem", artist = "Mohamad De Ma", year = 2021),
        ArtWork(image = R.drawable.art_2, title = "Huzalo bzio alohala", artist = "The pix", year = 1876),
        ArtWork(image = R.drawable.art_3, title = "Muzan Samalo ca de sour", artist = "Handy", year = 2000),
        ArtWork(image = R.drawable.art_4, title = "Broeatza malo", artist = "Alasto Madia", year = 2022),
        ArtWork(image = R.drawable.art_5, title = "Io si", artist = "Posilaka Dekov", year = 1999),
    )

    var currentArtWork by remember {
        mutableStateOf(0)
    }

    val currentItem = gallery[currentArtWork]

    Log.d("gallery: size", "${gallery.size}")
    Log.d("gallery: ", "current${currentArtWork}")
    Surface(
        Modifier
            .fillMaxSize()
            .padding(vertical = 16.dp),
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            var showTooltip by remember { mutableStateOf(false) }
            BoxWithToolTip {
                Card(
                    Modifier
                        .background(MaterialTheme.colors.background)
                        .wrapContentSize()
                        .border(1.dp, Color.Black)
                        .weight(5f)
                        .align(Alignment.CenterHorizontally),
                    elevation = 10.dp,
                ) {
                    Image(
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(24.dp),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.FillHeight,
                        painter = painterResource(id = currentItem.image),
                        contentDescription = currentItem.title
                    )
                }
            }
            Spacer(Modifier.height(8.dp))
            ArtworkTitle(
                title = currentItem.title,
                artist = currentItem.artist,
                year = currentItem.year,
                modifier = Modifier.weight(1f)
            )
            Text(
                "${currentArtWork + 1}/${gallery.size}",
                Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )
            ButtonGroup(onPre = {
                if (currentArtWork >= 1) {
                    currentArtWork--
                }
            }, onNext = {
                Log.d("gallery: isNext", "${currentArtWork <= gallery.size - 1}")
                if (currentArtWork < (gallery.size - 1)) {
                    currentArtWork++
                }

            })
        }

    }
}

@Composable
fun BoxWithToolTip(content: @Composable () -> Unit) {
    var toolTipShow by remember { mutableStateOf(false) }
    Box() {
        content
        Surface(
            modifier = Modifier.shadow(4.dp),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text("Do a little action!!",modifier = Modifier.padding(10.dp))


        }
    }
}

@Composable
fun ArtworkTitle(title: String, artist: String, year: Int, modifier: Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentSize(),
        elevation = 10.dp
    ) {
        Column(
            Modifier
                .wrapContentSize()
                .padding(16.dp),

            ) {
            Text(
                title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                "$artist ($year)",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun ButtonGroup(onPre: () -> Unit, onNext: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
    ) {
        Button(
            onClick = onPre,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            Text(text = "Previous")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(text = "Next")
        }
    }
}