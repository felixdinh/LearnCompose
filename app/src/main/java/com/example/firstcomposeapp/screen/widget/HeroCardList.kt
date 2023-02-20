package com.example.firstcomposeapp.screen.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.firstcomposeapp.model.HeroModel

@Composable
fun HeroCardList(heroes: List<HeroModel>, modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        items(heroes) {
            HeroCard(hero = it, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
        }
    }
}