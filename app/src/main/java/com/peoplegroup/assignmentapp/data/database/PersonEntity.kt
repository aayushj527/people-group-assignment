package com.peoplegroup.assignmentapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.peoplegroup.assignmentapp.utilities.Person

@Entity
class PersonEntity(
    val person: Person
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}