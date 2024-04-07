package com.peoplegroup.assignmentapp

data class Person(
    var gender: String? = null,
    var name: Name? = Name(),
    var location: Location? = Location(),
    var email: String? = null,
    var login: Login? = Login(),
    var dob: Dob? = Dob(),
    var registered: Registered? = Registered(),
    var phone: String? = null,
    var cell: String? = null,
    var id: Id? = Id(),
    var picture: Picture? = Picture(),
    var nat: String? = null
)

data class Name(
    var title: String? = null,
    var first: String? = null,
    var last: String? = null
)

data class Street(
    var number: Int? = null,
    var name: String? = null
)

data class Coordinates(
    var latitude: String? = null,
    var longitude: String? = null
)

data class Timezone(
    var offset: String? = null,
    var description: String? = null
)

data class Location(
    var street: Street? = Street(),
    var city: String? = null,
    var state: String? = null,
    var country: String? = null,
    var postcode: Int? = null,
    var coordinates: Coordinates? = Coordinates(),
    var timezone: Timezone? = Timezone()
)

data class Login(
    var uuid: String? = null,
    var username: String? = null,
    var password: String? = null,
    var salt: String? = null,
    var md5: String? = null,
    var sha1: String? = null,
    var sha256: String? = null
)

data class Dob(
    var date: String? = null,
    var age: Int? = null
)

data class Registered(
    var date: String? = null,
    var age: Int? = null
)

data class Id(
    var name: String? = null,
    var value: String? = null
)

data class Picture(
    var large: String? = null,
    var medium: String? = null,
    var thumbnail: String? = null
)