package org.hxt.logofmoney.controller

import org.hxt.logofmoney.entity.table.Type
import org.hxt.logofmoney.entity.repository.TypeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TypeController {
    @Autowired
    lateinit var typeRepository: TypeRepository

    @GetMapping("/type/get-all")
    fun getAll() = typeRepository.findAll()

    @PostMapping("/type/upsert")
    fun upsert(@RequestBody type: Type):  ResponseEntity<String> {
        if (typeRepository.existsById(type.id)) {
            typeRepository.deleteById(type.id)
        } else {
            type.id = System.currentTimeMillis()
        }
        typeRepository.save(type)
        return ResponseEntity.ok("ok")
    }

    @PostMapping("/type/delete")
    fun delete(@RequestBody type: Type) = runCatching {
        typeRepository.deleteById(type.id)
        return@runCatching ResponseEntity.ok().body("ok")
    }.onFailure {
        it.printStackTrace()
    }
}