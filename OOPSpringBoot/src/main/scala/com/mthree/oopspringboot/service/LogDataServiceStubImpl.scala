package com.mthree.oopspringboot.service

import com.mthree.oopspringboot.entity.Log

import java.util.UUID

class LogDataServiceStubImpl extends LogDataService {

  override def getLog(id: UUID): Log = {
    return null
  }

  override def createLog(log: Log): Log = {
    return null
  }
}
