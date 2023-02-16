package com.example.firstcomposeapp.kotlin_chanllenge

fun main() {
    Quiz().printProgressBar()
    //Giong như cu phap quiz = Quizz()..printQuiz() cua Flutter
    //Gọi các method của Instance lúc tạo biến
    val quiz = Quiz().apply {
        printQuiz()
    }
}
enum class Difficulty{ EASY,MEDIUM,HARD }
data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)


class Quiz : ProgressPrintable{
    val question1 = Question<String>("Quotes String", "true true",Difficulty.EASY)
    val question2 = Question<Boolean>("Quotes String", true,Difficulty.MEDIUM)
    val question3 = Question<Int>("Quotes String", 10,Difficulty.HARD)
    companion object StudentProgress {
        var total: Int = 10
        var answered: Int = 3
    }

    override val progressText: String
        get() = "$answered of $total answer"

    override fun printProgressBar() {
        repeat(Quiz.answered){print("▓")}
        repeat(Quiz.total){print("▒")}
        println()
        println(progressText)
    }

    fun printQuiz(){
        println(question1.questionText)
        println(question1.answer)
        println(question1.difficulty)
        println()
        println(question2.questionText)
        println(question2.answer)
        println(question2.difficulty)
        println()
        println(question3.questionText)
        println(question3.answer)
        println(question3.difficulty)
        println()
    }

}

interface ProgressPrintable{
    val progressText: String
    fun printProgressBar()
}