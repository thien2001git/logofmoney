package org.hxt.logofmoney.entity.repository

import org.hxt.logofmoney.entity.table.Type
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource/*(path = "type")*/
interface TypeRepository : JpaRepository<Type, Long> {
}