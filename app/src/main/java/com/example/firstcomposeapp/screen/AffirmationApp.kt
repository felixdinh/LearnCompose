package com.example.firstcomposeapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstcomposeapp.R
import com.example.firstcomposeapp.data.Datasource
import com.example.firstcomposeapp.model.Affirmation
import com.example.firstcomposeapp.ui.theme.FirstComposeAppTheme

@Composable
fun AffirmationApp() {
    val context = LocalContext.current
    FirstComposeAppTheme{
        AffirmationList(affirmationList = Datasource().loadAffirmation(), modifier = Modifier)
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(8.dp),
        elevation = 4.dp
    ) {
        Column() {
            Image(
                painter = painterResource(id = affirmation.imageResourceId),
                contentDescription = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                stringResource(id = affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
private fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier){
    LazyColumn(){
        items(affirmationList){
            affirmationItem -> AffirmationCard(affirmation = affirmationItem)
        }
    }
}

@Preview
@Composable
fun AffirmationPreview(){
    AffirmationCard(affirmation = Affirmation(R.string.affirmation1,R.drawable.art_1))
}