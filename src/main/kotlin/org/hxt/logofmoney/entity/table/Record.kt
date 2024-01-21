package org.hxt.logofmoney.entity.table

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ForeignKey
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity(name = "Record")
class Record {
    constructor() {
        id = 0
        idType = 0
        date = 0
        description = ""
    }

    constructor(id: Long, idType: Long, date: Long, description: String) {
        this.id = id
        this.idType = idType
        this.date = date
        this.description = description
    }


    @Id
    @Column(name = "Id")
    var id: Long

//    @ManyToOne(targetEntity = Type::class)
    @Column(name = "IdType")
    var idType: Long

    @Column(name = "Date")
    var date: Long

    @Column(name = "Description")
    var description: String
}
