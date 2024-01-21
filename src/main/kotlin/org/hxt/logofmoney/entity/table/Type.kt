package org.hxt.logofmoney.entity.table

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "Type")
class Type {

    constructor() {
        this.id = 0
        this.name = ""
    }
    constructor(id: Long, name: String) {
        this.id = id
        this.name = name
    }

    @Id
    @Column(name = "Id")
    var id: Long

    @Column(name = "Name")
    var name: String
}