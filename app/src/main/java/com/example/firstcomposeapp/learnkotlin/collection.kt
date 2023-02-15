package com.example.firstcomposeapp.learnkotlin

fun main() {
    //Array
    val rockPlanets = arrayOf<String>("Mercury", "Venus", "Earth", "Mars")
    val gasPlanets = arrayOf<String>("Jupiter", "Saturn", "Uranus", "Neptune")

    val solarSystem = rockPlanets + gasPlanets

//    rockPlanets[4] = "fjijeif"
//    println(rockPlanets.size)

    //Map
    val groupMenu = cookies.groupBy { it.softBaked }
    val softBakedMenu = groupMenu[true] ?: listOf()
    val crunchBakedMenu = groupMenu[false] ?: listOf()

    println("Soft cookies:")
    softBakedMenu.sortedBy { it.name }.forEach{ println("${it.name} - $${it.price}")}
    println("\nCrunch cookies:")
    crunchBakedMenu.forEach{ println("${it.name} - $${it.price}") }

    val totalPrice = cookies.fold(0.0){total,cookie -> total + cookie.price}
    println("\nTotal Price all Cookies: $$totalPrice")

}
class Cookie(
    val name: String,
    val softBaked: Boolean,
    val hasFilling: Boolean,
    val price: Double
)

val cookies = listOf(
    Cookie(
        name = "Chocolate chip",
        softBaked = false,
        hasFilling = false,
        price = 1.69
    ),
    Cookie(
        name = "Banana Walnut",
        softBaked = true,
        hasFilling = false,
        price = 1.49
    ),
    Cookie(
        name = "Vanilla Creme",
        softBaked = false,
        hasFilling = true,
        price = 1.59
    ),
    Cookie(
        name = "Chocolate Peanut Butter",
        softBaked = false,
        hasFilling = true,
        price = 1.49
    ),
    Cookie(
        name = "Snickerdoodle",
        softBaked = true,
        hasFilling = false,
        price = 1.39
    ),
    Cookie(
        name = "Blueberry Tart",
        softBaked = true,
        hasFilling = true,
        price = 1.79
    ),
    Cookie(
        name = "Sugar and Sprinkles",
        softBaked = false,
        hasFilling = false,
        price = 1.39
    )
)