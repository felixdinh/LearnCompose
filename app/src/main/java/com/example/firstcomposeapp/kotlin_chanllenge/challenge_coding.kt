package com.example.firstcomposeapp.kotlin_chanllenge

/*
===1===
Typically, your phone provides you with a summary of notifications.

In the initial code provided in the following code snippet, write a program that prints the summary message based on the number of notifications that you received. The message should include:

The exact number of notifications when there are less than 100 notifications.
99+ as the number of notifications when there are 100 notifications or more.
===2===
Movie tickets are typically priced differently based on the age of moviegoers.

In the initial code provided in the following code snippet, write a program that calculates these age-based ticket prices:

A children's ticket price of $15 for people 12 years old or younger.
A standard ticket price of $30 for people between 13 and 60 years old. On Mondays, discount the standard ticket price to $25 for this same age group.
A senior ticket price of $20 for people 61 years old and older. Assume that the maximum age of a moviegoer is 100 years old.
A -1 value to indicate that the price is invalid when a user inputs an age outside the age specifications.
===3===
There are three main temperature scales used in the world: Celsius, Fahrenheit, and Kelvin.

In the initial code provided in the following code snippet, write a program that converts a temperature from one scale to another with these formulas:

Celsius to Fahrenheit: ° F = 9/5 (° C) + 32
Kelvin to Celsius: ° C = K - 273.15
Fahrenheit to Kelvin: K = 5/9 (° F - 32) + 273.15
Note that the String.format("%.2f", /* measurement */ ) method is used to convert a number into a String type with 2 decimal places.
*//*Imagine that you need to create a music-player app.

Create a class that can represent the structure of a song. The Song class must include these code elements:

Properties for the title, artist, year published, and play count
A property that indicates whether the song is popular. If the play count is less than 1,000, consider it unpopular.
A method that prints a song description in this format:
"[Title], performed by [artist], was released in [year published]." */

class Song(
    private val title: String, private val artist: String, private val publishYear: Int, private val playCount: Int
) {
    val isSongPopular: Boolean
        get() = playCount >= 1000

    fun printDescription() = println("$title, performed by $artist, was released in $publishYear")
}

fun printNotificationSummary(notiNumber: Int) {
    if (notiNumber < 100) {
        println("You have $notiNumber notifications.")
    } else {
        println("Your phone is blowing up! You have 99+ notification")
    }
}

fun ticketPrice(age: Int, isMonday: Boolean): Int {
    return when (age) {
        in 0..12 -> 15
        in 13..60 -> if (isMonday) 25 else 30
        in 61..100 -> 20
        else -> -1
    }
}

fun printFinalTemperature(
    initialMeasurement: Double, initialUnit: String, finalUnit: String, conversionFormula: (Double) -> Double
) {
    val finalMeasurement = String.format("%.2f", conversionFormula(initialMeasurement)) // two decimal places
    println("$initialMeasurement degrees $initialUnit is $finalMeasurement degrees $finalUnit.")
}

class Person(val name: String, val age: Int, val hobby: String?, val referrer: Person?) {
    fun showProfile() {
        println("\n\nName: $name")
        println("Age: $age")
        if (hobby != null) {
            print("Like to $hobby. ")
        }
        if (referrer != null) {
            print("Has referrer named ${referrer.name}, ")
            if (referrer.hobby != null) {
                print("who likes ${referrer.hobby}.")
            } else {
                print(".")
            }
        } else print("Doesn't have a referrer")

    }
}

open class Phone(private var isScreenLightOn: Boolean = false) {
    var phoneState: String = "off"

    open fun switchOn() {
        isScreenLightOn = true
    }

    open fun switchOff() {
        isScreenLightOn = false
    }

    fun checkPhoneScreenLight() {
        val phoneScreenLight = if (isScreenLightOn) "on" else "off"
        println("The phone screen's light is $phoneScreenLight")
    }

}

class FoldablePhone(private var isFolded:Boolean = true) : Phone() {

    override fun switchOn() {
        if (!isFolded) {
            super.switchOn()
        }
    }

    fun fold() {
        isFolded = true
    }

    fun unfold() {
        isFolded = false
    }
}
class Bid(val amount: Int, val bidder:String)
fun auctionPrice(bid: Bid?, minimumPrice:Int):Int = bid?.amount ?: minimumPrice

fun main() {
///===main3 implement===
    /*    val convertCelsiusToFahrenheit: (Double) -> Double = { (9.0 / 5.0) * it + 32 }
        val convertKelvinToFCelsius: (Double) -> Double = { it - 273.15 }
        val convertFahrenheitToKelvin: (Double) -> Double = { (5.0 / 9.0) * (it - 32) + 273.15 }
        printFinalTemperature(
            27.0,
            "C",
            finalUnit = "F",
            conversionFormula = convertCelsiusToFahrenheit
        )
        printFinalTemperature(
            350.0,
            "K",
            finalUnit = "C",
            conversionFormula = convertKelvinToFCelsius
        )
        printFinalTemperature(
            10.0,
            "F",
            finalUnit = "K",
            conversionFormula = convertFahrenheitToKelvin
        )*/
    ///Main 4
//    val brunoSong = Song("We Don't talk About Bruno","Encanto Cast",2022,1_000_000)
//    brunoSong.printDescription()
//    println(brunoSong.isSongPopular)
    ///Main 5
//    val amanda = Person("Amanda", 33, "play tennis", null)
//    val atiqah = Person("Atiqah", 28, "climb", amanda)
//
//    amanda.showProfile()
//    atiqah.showProfile()
    ///Main 5
//    val newFoldablePhone = FoldablePhone()
//
//    newFoldablePhone.switchOn()
//    newFoldablePhone.checkPhoneScreenLight()
//    newFoldablePhone.unfold()
//    newFoldablePhone.switchOn()
//    newFoldablePhone.checkPhoneScreenLight()
    val winningBid = Bid(5000, "Private Collector")

    println("Item A is sold at ${auctionPrice(winningBid, 2000)}.")
    println("Item B is sold at ${auctionPrice(null, 3000)}.")
}

