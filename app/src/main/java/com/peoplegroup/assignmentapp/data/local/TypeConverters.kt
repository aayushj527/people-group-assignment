package com.peoplegroup.assignmentapp.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.peoplegroup.assignmentapp.domain.model.Person
import com.peoplegroup.assignmentapp.domain.model.Status

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

class StatusTypeConverter {
    @TypeConverter
    fun statusToString(status: Status): String {
        return status.name
    }

    @TypeConverter
    fun stringToStatus(statusString: String): Status {
        return when (statusString) {
            Status.Accepted.name -> Status.Accepted
            else -> Status.Declined
        }
    }
}