package com.daggerfrog.front.api

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import akka.event.slf4j.SLF4JLogging

object ApiApp extends App with ComponentRegistry with SLF4JLogging {

  log.info("Starting workers")
  lazy val actorSystem = ActorSystem("ImhoSystem",  ConfigFactory.load())
  def ff(str1: String, str2: String, ind: Int = 0): List[Int] = {
    val index = str1.indexOf(str2)
    if (index > -1) index :: f(str1, str2, index)
    else
      List.empty
  }
}
