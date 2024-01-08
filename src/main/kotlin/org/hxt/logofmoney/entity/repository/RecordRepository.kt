package org.hxt.logofmoney1.repository


import org.hxt.logofmoney.entity.table.Record
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "record")
interface RecordRepository: JpaRepository<Record, Long> {
}