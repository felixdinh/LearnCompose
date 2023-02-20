package com.example.firstcomposeapp.data

import com.example.firstcomposeapp.R
import com.example.firstcomposeapp.model.HeroModel

class Datasource {
    val heroes = listOf<HeroModel>(
        HeroModel(
            name = R.string.hero1,
            quote = R.string.quote1,
            image = R.drawable.android_superhero1
        ),
        HeroModel(
            name = R.string.hero2,
            quote = R.string.quote1,
            image = R.drawable.android_superhero2
        ),
        HeroModel(
            name = R.string.hero3,
            quote = R.string.quote1,
            image = R.drawable.android_superhero3
        ),
        HeroModel(
            name = R.string.hero4,
            quote = R.string.quote1,
            image = R.drawable.android_superhero4
        ),
        HeroModel(
            name = R.string.hero5,
            quote = R.string.quote1,
            image = R.drawable.android_superhero5
        ),
        HeroModel(
            name = R.string.hero6,
            quote = R.string.quote1,
            image = R.drawable.android_superhero6
        ),
    )
}