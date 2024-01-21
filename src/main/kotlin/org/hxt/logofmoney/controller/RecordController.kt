package org.hxt.logofmoney.controller

import org.hxt.logofmoney.entity.model.impl.RecordModel
import org.hxt.logofmoney.entity.model.response.RecordResponse
import org.hxt.logofmoney.entity.repository.RecordRepository
import org.hxt.logofmoney.entity.repository.TypeRepository
import org.hxt.logofmoney.entity.table.Record
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RecordController {
    @Autowired
    lateinit var recordRepository: RecordRepository

    @Autowired
    lateinit var typeRepository: TypeRepository

    @GetMapping("/record/get-all")
    fun getAll(): List<RecordResponse> {
        val res = ArrayList<RecordResponse>()
        recordRepository.findAll().forEach {
            res.add(RecordModel(it, typeRepository).create())
        }
        return res
    }


    @PostMapping("/record/upsert")
    fun upsert(@RequestBody record: Record): ResponseEntity<String> {
        if (recordRepository.existsById(record.id)) {
            recordRepository.deleteById(record.id)
        } else {
            record.id = System.currentTimeMillis()
        }
        if (!typeRepository.existsById(record.idType)) {
            return ResponseEntity.badRequest().body("not found")
        } else {
            recordRepository.save(record)
            return ResponseEntity.ok(record.description)
        }
    }


    @PostMapping("/record/delete")
    fun delete(@RequestBody record: Record) = runCatching {
        recordRepository.deleteById(record.id)
        return@runCatching ResponseEntity.ok().body("ok")
    }.onFailure {
        it.printStackTrace()
    }

}