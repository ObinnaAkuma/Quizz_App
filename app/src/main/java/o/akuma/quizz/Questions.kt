package o.akuma.quizz

data class Questions(
    val id: Int,
    val question: String,
    //Images in android can be created as Ints
    val image: Int,
    val optionOne: String,
    val optionTwo: String,
    val optionThree: String,
    val optionFour: String,
    val correctAnswer: Int

)
