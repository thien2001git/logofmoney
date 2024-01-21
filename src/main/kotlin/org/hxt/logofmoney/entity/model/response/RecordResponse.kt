package org.hxt.logofmoney.entity.model.response

import org.hxt.logofmoney.entity.table.Type

data class RecordResponse(
    val id: Long,
    val type: Type,
    val date: Long,
    val description: String
)
