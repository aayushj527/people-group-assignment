package com.peoplegroup.assignmentapp.data.mapper

import com.peoplegroup.assignmentapp.data.local.PersonEntity
import com.peoplegroup.assignmentapp.domain.model.Person
import com.peoplegroup.assignmentapp.domain.model.PersonInfo
import com.peoplegroup.assignmentapp.utilities.addressBuilder
import com.peoplegroup.assignmentapp.utilities.nameBuilder

fun PersonEntity.toPersonInfo(): PersonInfo {
    return PersonInfo(
        id = id,
        name = person.name?.let { nameBuilder(name = it) },
        imageUrl = person.picture?.large,
        address = person.location?.let { addressBuilder(it) },
        status = status
    )
}

fun Person.toPersonEntity(): PersonEntity {
    return PersonEntity(person = this)
}