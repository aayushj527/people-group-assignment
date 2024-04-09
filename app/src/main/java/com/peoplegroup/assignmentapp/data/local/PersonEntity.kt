package com.peoplegroup.assignmentapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.peoplegroup.assignmentapp.domain.model.Person
import com.peoplegroup.assignmentapp.domain.model.Status

@Entity
data class PersonEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val person: Person,
    val status: Status? = null
)