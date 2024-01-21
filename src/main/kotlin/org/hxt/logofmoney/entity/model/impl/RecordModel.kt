package org.hxt.logofmoney.entity.model.impl

import org.hxt.logofmoney.entity.model.base.IRecord
import org.hxt.logofmoney.entity.repository.TypeRepository
import org.hxt.logofmoney.entity.table.Record
import org.hxt.logofmoney.entity.table.Type
import org.hxt.logofmoney.utils.Util
import java.time.LocalDateTime


class RecordModel(val record: Record, val typeRepository: TypeRepository) : IRecord {

    override fun getType(): Type = typeRepository.getReferenceById(record.idType)

    override fun getId(): Long = record.id

    override fun getDate(): LocalDateTime = Util.convertLongToLocalDateTime(record.date)

    override fun getDescription(): String = record.description
}