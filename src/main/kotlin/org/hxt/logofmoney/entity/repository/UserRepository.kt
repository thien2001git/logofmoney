package org.hxt.logofmoney.entity.repository

import org.hxt.logofmoney.entity.table.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
}