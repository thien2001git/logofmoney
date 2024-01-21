package org.hxt.logofmoney.entity.model.base

import org.hxt.logofmoney.entity.table.Type
import java.time.LocalDateTime

interface IRecord {
    fun getType(): Type
    fun getId(): Long
    fun getDate(): LocalDateTime
    fun getDescription(): String
}