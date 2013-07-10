package com.daggerfrog.front.api

import com.mongodb.casbah.Imports._
import akka.actor.{Props, Actor, ActorLogging}
import com.daggerfrog.common.ActorSystemComponent

case object ReadAll
case class Insert(str: String)

trait StorageComponent {
  this: ActorSystemComponent ⇒

  lazy val mongoStorage = actorSystem.actorOf(Props(MongoStorage), name = "mongoStorage")

  object MongoStorage extends Actor with ActorLogging {
    lazy val mongoClient = MongoClient("localhost", 27017)
    val db = mongoClient("test")
    val coll = db("test")

    def receive = {
      case ReadAll ⇒
        val f = coll.find()
        println(f.toString())
        println(f.map(db ⇒ db.toString))
        sender ! "[%s]".format(f.map(db ⇒ db.toString).mkString(","))
      case Insert(str: String) ⇒
        coll.insert(com.mongodb.util.JSON.parse(str).asInstanceOf[DBObject])
    }
  }

}
