package com.example.firstcomposeapp.kotlin_chanllenge

fun main() {

   val treatFunc = trickOrTreat(false) { "$it treat" }
    val trickFunc = trickOrTreat(true,null)

    repeat(4){
        print("$it time \n")
        treatFunc()
    }
    trickFunc()
}
fun trickOrTreat(isTrick: Boolean, extraTreat: ((Int) -> String)?) : ()-> Unit{
    return if(isTrick){
        trick
    } else {
        if(extraTreat!=null) println(extraTreat(5))
        treat
    }
}
val trick = {
    println("No tricks")
}

val treat:() -> Unit = {
    println("Have a treat!")
}