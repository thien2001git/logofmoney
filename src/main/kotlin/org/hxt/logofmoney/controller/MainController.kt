package org.hxt.logofmoney.controller

import org.hxt.logofmoney.entity.table.User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
class MainController {


    @GetMapping("/index")
    suspend fun root(): User {
        return User()
    }


    @GetMapping("/index1")
    suspend fun root1(): User {
        return User()
    }
}