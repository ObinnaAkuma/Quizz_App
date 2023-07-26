package o.akuma.quizz

object Constants {

    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Questions>{

        val questionsList = ArrayList<Questions>()



        val que1 = Questions(
            1, "What country has this Flag?",
            R.drawable.nigeria, "United Arab Emirate", "Algeria",
            "Tunisia", "Nigeria", 4
        )
        questionsList.add(que1)


        val que2 = Questions(
            1, "What country has this Flag?",
            R.drawable.argentina, "Argentina", "Kuwait",
            "United States", "Sudan", 1
        )
        questionsList.add(que2)



        val que3 = Questions(
            1, "What country has this Flag?",
            R.drawable.austria, "Austria", "Algeria",
            "Tunisia", "Pakistan", 1
        )
        questionsList.add(que3)



        val que4 = Questions(
            1, "What country has this Flag?",
            R.drawable.brazil, "United Arab Emirate", "Algeria",
            "Tunisia", "Brazil", 4
        )
        questionsList.add(que4)



        val que5 = Questions(
            1, "What country has this Flag?",
            R.drawable.canada, "United Arab Emirate", "Algeria",
            "Tunisia", "Canada", 4
        )

        questionsList.add(que5)



        val que6 = Questions(
            1, "What country has this Flag?",
            R.drawable.guantamela, "United Arab Emirate", "Guantamela",
            "Tunisia", "Pakistan", 2
        )
        questionsList.add(que6)


        val que7 = Questions(
            1, "What country has this Flag?",
            R.drawable.italy, "Italy", "Algeria",
            "Tunisia", "Pakistan", 1
        )
        questionsList.add(que7)



        val que8 = Questions(
            1, "What country has this Flag?",
            R.drawable.malaysia, "Algeria", "Malaysia",
            "Tunisia", "Pakistan", 2
        )
        questionsList.add(que8)



        val que9 = Questions(
            1, "What country has this Flag?",
            R.drawable.moldova, "United Arab Emirate", "Algeria",
            "Moldova", "Mexico", 3
        )
        questionsList.add(que9)



        val que10 = Questions(
            1, "What country has this Flag?",
            R.drawable.algeria, "United Arab Emirate", "Algeria",
            "Tunisia", "Pakistan", 2
        )
        questionsList.add(que10)

        return questionsList
    }
}