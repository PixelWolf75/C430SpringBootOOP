package com.mthree.oopspringboot.service

import com.mthree.oopspringboot.entity.Log
import org.springframework.stereotype.Component

import java.time.LocalDateTime
import java.util.UUID
import scala.collection.mutable

@Component
class LogDataServiceStubImpl extends LogDataService {

  private val logs = mutable.Map[UUID, Log]()

  override def createLog(log: Log): Log = {
    val id = UUID.randomUUID()
    log.setId(id)

    if(log.getCreatedAt == null){
      log.setCreatedAt(LocalDateTime.now())
    }

    logs.put(id, log)
    log
  }

  override def getLog(id: UUID): Log = {
    logs.getOrElse(id, null)

  }
}
