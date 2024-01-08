package org.hxt.logofmoney.entity.table

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import lombok.AllArgsConstructor

@Entity(name = "Type")
data class Type(
    @Id
    @Column(name = "Id")
    var id: Long,

    @Column(name = "Name")
    var name: String
) {
}
