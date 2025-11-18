package com.mthree.oopspringboot.service

import com.mthree.oopspringboot.entity.Log
import org.springframework.stereotype.Component

import java.util.UUID

@Component
class LogDataServiceStubImpl extends LogDataService {

  override def getLog(id: UUID): Log = {
    return null
  }

  override def createLog(log: Log): Log = {
    return null
  }
}
