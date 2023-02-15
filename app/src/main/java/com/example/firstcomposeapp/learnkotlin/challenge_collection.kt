/*
* https://developer.android.com/codelabs/basic-android-kotlin-compose-practice-classes-and-collections?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-compose-unit-3-pathway-1%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-compose-practice-classes-and-collections#0
* */
enum class Daypart{MORNING,AFTERNOON,EVENING}
class Event(val title: String, val description: String? = null, val daypart: Daypart, val durationInMinutes: Int)
val events = mutableListOf<Event>(
Event(title = "Wake up", description = "Time to get up", daypart = Daypart.MORNING, durationInMinutes = 0),
Event(title = "Eat breakfast", daypart = Daypart.MORNING, durationInMinutes = 15),
Event(title = "Learn about Kotlin", daypart = Daypart.AFTERNOON, durationInMinutes = 30),
Event(title = "Practice Compose", daypart = Daypart.AFTERNOON, durationInMinutes = 60),
Event(title = "Watch latest DevBytes video", daypart = Daypart.AFTERNOON, durationInMinutes = 10),
Event(title = "Check out latest Android Jetpack library", daypart = Daypart.EVENING, durationInMinutes = 45)

)
val Event.durationOfEvent: String
    get() = if(this.durationInMinutes < 60) "short" else "long"
fun main() {
    val shortEvent = events.filter { it.durationInMinutes < 60 }
    val groupEvent = events.groupBy { it.daypart }
    println("Group by: $groupEvent")
    groupEvent.forEach(){
        daypart, events -> println("$daypart: ${events.size} events")
    }
    println("the last event of the day is ${events.last().title}")
    println("Duration of first event of the day: ${events.first().durationOfEvent}")
}