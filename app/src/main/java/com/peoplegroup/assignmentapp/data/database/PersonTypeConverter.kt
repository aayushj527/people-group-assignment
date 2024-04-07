package com.peoplegroup.assignmentapp.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.peoplegroup.assignmentapp.Person

class PersonTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun personToString(person: Person): String {
        return gson.toJson(person)
    }

    @TypeConverter
    fun stringToPerson(personString: String): Person {
        return gson.fromJson(personString, Person::class.java)
    }
}