package org.hxt.logofmoney.entity.table

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id


@Entity(name = "User")
class User {
    constructor() {
        id = 0
        name = ""
        password = ""
        authority = ""
    }

    constructor(id: Long, name: String, password: String, authority: String) {
        this.id = id
        this.name = name
        this.password = password
        this.authority = authority
    }

    @Id
    @Column(name = "id")
    var id: Long

    @Column(name = "name")
    var name: String

    @Column(name = "password")
    var password: String

    @Column(name = "authority")
    var authority: String
    override fun toString(): String {
        return "User(id=$id, name='$name', password='$password', authority='$authority')"
    }


}