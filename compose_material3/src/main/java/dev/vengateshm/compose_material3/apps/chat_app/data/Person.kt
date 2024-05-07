package dev.vengateshm.compose_material3.apps.chat_app.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import dev.vengateshm.compose_material3.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val id: Int = 0,
    val name: String = "",
    @DrawableRes val icon: Int = R.drawable.cmaterial3_chat_app_person_1
) : Parcelable

val personList = listOf(
    Person(
        1,
        "Pranav",
        R.drawable.cmaterial3_chat_app_person_1
    ),
    Person(
        2,
        "Ayesha",
        R.drawable.cmaterial3_chat_app_person_2
    ),
    Person(
        3,
        "Roshini",
        R.drawable.cmaterial3_chat_app_person_3
    ),
    Person(
        4,
        "Kaushik",
        R.drawable.cmaterial3_chat_app_person_4
    ),
    Person(
        5,
        "Dad",
        R.drawable.cmaterial3_chat_app_person_5
    ),
    Person(
        6,
        "Pranav",
        R.drawable.cmaterial3_chat_app_person_1
    ),
    Person(
        7,
        "Ayesha",
        R.drawable.cmaterial3_chat_app_person_2
    ),
    Person(
        8,
        "Roshini",
        R.drawable.cmaterial3_chat_app_person_3
    ),
    Person(
        9,
        "Kaushik",
        R.drawable.cmaterial3_chat_app_person_4
    ),
    Person(
        10,
        "Dad",
        R.drawable.cmaterial3_chat_app_person_5
    ),
)
