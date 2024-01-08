package org.hxt.logofmoney.entity.table

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity(name = "Record")
data class Record(
    @Id
    @Column(name = "Id")
    var id:Long,

    @Column(name = "IdType")
    var idType:Long,

    @Column(name = "Date")
    var date:Long,

    @Column(name = "Description")
    var description: String
)
