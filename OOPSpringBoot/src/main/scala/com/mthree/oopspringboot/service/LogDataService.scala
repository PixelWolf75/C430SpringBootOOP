package com.mthree.oopspringboot.service

import com.mthree.oopspringboot.entity.Log

trait LogDataService {

    def getLog(id: java.util.UUID): Log

    def createLog(log: Log): Log
  
}
