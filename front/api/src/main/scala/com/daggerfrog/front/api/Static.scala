package com.daggerfrog.front.api


import akka.io.IO
import com.daggerfrog.common.ActorSystemComponent
import akka.actor._
import spray.http.HttpHeaders.RawHeader
import java.util.concurrent.TimeUnit

import scala.concurrent.duration._
import akka.actor.{Props, Actor}
import akka.pattern.ask
import spray.routing.{ExceptionHandler, HttpService, RequestContext}
import spray.can.server.Stats
import spray.can.Http
import spray.http._
import MediaTypes._
import akka.util.Timeout
import shapeless.option

class RssService extends Actor with HttpService {
  import context.dispatcher

  implicit val timeout = Timeout(20 seconds)

  def actorRefFactory = context

  val headers =
    List(RawHeader("Access-Control-Allow-Origin", "*"),
      RawHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept"))

//  lazy val newTask = HttpResponse(
//    entity = HttpEntity(MimeJson, """{ "name" : "asdf" , "site" : "http://localhost:8080/index_project.html#/new" , "description" : "asdf" , "_id" : { "$oid" : "51d1f046e4b06814f5bbcbb0"}}"""),
//    headers = headers)

  def receive = runRoute {
    get {
      path("task") {
        respondWithMediaType(MediaTypes.`application/json`) {
          complete { """[ { "_id" : { "$oid" : "51be51e4e4b02b5726c160fd"} , "name" : "aaron" , "site" : "http://www.aaronsw.com/weblog/productivity"} , { "_id" : { "$oid" : "51bee445e4b0ab0d91e74d10"} , "name" : "livejournal rss" , "site" : "http://livejournal.com"} ]""" }
        }
      } ~
        path("public" / "[A-z0-9-_]+".r) { case user: String ⇒
          respondWithMediaType(MediaTypes.`text/xml`) {
            complete { "s Prismatic activity" }
          }
        } ~
        path("stats") {
          complete {
            context.actorFor("/user/IO-HTTP/listener-0") ? Http.GetStats map {
              case stats: Stats ⇒
                s"""
              | Uptime                : ${Duration(stats.uptime.toHours, TimeUnit.HOURS)}
              | Total requests        : ${stats.totalRequests}
              | Open requests         : ${stats.openRequests}
              | Max open requests     : ${stats.maxOpenRequests}
              | Total connections     : ${stats.totalConnections}
              | Open connections      : ${stats.openConnections}
              | Max open connections  : ${stats.maxOpenConnections}
              | Requests timed out    : ${stats.requestTimeouts}
              """.trim.stripMargin
            }
          }
        }
    } ~
    options {
      path(".*".r) { case user: String ⇒
        respondWithMediaType(MediaTypes.`text/xml`) {
          complete { "s Prismatic activity" }
        }
      }
    }
  }
}

  //    def receive = {
  //      case _: Http.Connected => sender ! Http.Register(self)
  //
  //      case HttpRequest(GET, Uri.Path("/"), _, _, _) =>
  //        log.info("root request")
  //        sender ! index
  //
  //      case HttpRequest(GET, Uri.Path("/task"), _, _, _) =>
  //        log.info("root request 1 ")
  //        sender ! task
  //
  //      case HttpRequest(POST, Uri.Path("/task"), _, _, _) =>
  //        log.info("root request 2 ")
  //        sender ! newTask
  //
  //      case HttpRequest(PUT, uri, _, _, _) if uri.path.startsWith(Uri.Path("/task"))=>
  //        log.info("root request 3 ")
  //        println(uri.fragment)
  //        println(uri.path.toString())
  //        sender ! newTask
  //
  //      case HttpRequest(OPTIONS, _, _, _, _) =>
  //        log.info("root request 4")
  //        sender ! task
  //
  //      case res: HttpRequest =>
  //        log.info(s"Unkonwn resource: $res")
  //        sender ! HttpResponse(status = 404, entity = "Unknown resource!",  headers = headers)
  //    }
  //

trait RestApiComponent {
  this: ActorSystemComponent =>

  val MimeJson = `application/json`

  val headers =
//    Access-Control-Allow-Methods: POST, GET, OPTIONS
  List(RawHeader("Access-Control-Allow-Origin", "*"),
  RawHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept"))
  lazy val index = HttpResponse(entity = HttpEntity(MimeJson,
    """{"api": {"version": "0.1", "list": ["task"]}}"""),  headers = headers)

  lazy val task = HttpResponse(
    entity = HttpEntity(MimeJson, """[ { "_id" : { "$oid" : "51be51e4e4b02b5726c160fd"} , "name" : "aaron" , "site" : "http://www.aaronsw.com/weblog/productivity"} , { "_id" : { "$oid" : "51bee445e4b0ab0d91e74d10"} , "name" : "livejournal rss" , "site" : "http://livejournal.com"} ]"""),
    headers = headers)

  lazy val newTask = HttpResponse(
    entity = HttpEntity(MimeJson, """{ "name" : "asdf" , "site" : "http://localhost:8080/index_project.html#/new" , "description" : "asdf" , "_id" : { "$oid" : "51d1f046e4b06814f5bbcbb0"}}"""),
    headers = headers)

//  class API extends Actor with HttpService with ActorLogging {
//    import context.dispatcher
//    implicit val hander = ExceptionHandler
//
//    implicit def actorRefFactory: ActorRefFactory = context
//
//    implicit val timeout: akka.util.Timeout = 10.second // for the actor 'asks' we use below
//
//    def receive = runRoute {
//      get {
//        path("feed") {
//          respondWithMediaType(MediaTypes.`text/xml`) {
//            complete {  "Prismatic RSS feed" }
//          }
//        } ~
//          path("public" / "[A-z0-9-_]+".r) { case user: String ⇒
//            respondWithMediaType(MediaTypes.`text/xml`) {
//              complete { "'s Prismatic activity" }
//            }
//          } ~
//          path("stats") {
//            complete {
//              context.actorFor("/user/IO-HTTP/listener-0") ? Http.GetStats map {
//                case stats: Stats ⇒
//                  s"""
//              | Uptime                : ${Duration(stats.uptime.toHours, TimeUnit.HOURS)}
//              | Total requests        : ${stats.totalRequests}
//              | Open requests         : ${stats.openRequests}
//              | Max open requests     : ${stats.maxOpenRequests}
//              | Total connections     : ${stats.totalConnections}
//              | Open connections      : ${stats.openConnections}
//              | Max open connections  : ${stats.maxOpenConnections}
//              | Requests timed out    : ${stats.requestTimeouts}
//              """.trim.stripMargin
//              }
//            }
//          }
//      }
//    }

//    def receive = {
//      case _: Http.Connected => sender ! Http.Register(self)
//
//      case HttpRequest(GET, Uri.Path("/"), _, _, _) =>
//        log.info("root request")
//        sender ! index
//
//      case HttpRequest(GET, Uri.Path("/task"), _, _, _) =>
//        log.info("root request 1 ")
//        sender ! task
//
//      case HttpRequest(POST, Uri.Path("/task"), _, _, _) =>
//        log.info("root request 2 ")
//        sender ! newTask
//
//      case HttpRequest(PUT, uri, _, _, _) if uri.path.startsWith(Uri.Path("/task"))=>
//        log.info("root request 3 ")
//        println(uri.fragment)
//        println(uri.path.toString())
//        sender ! newTask
//
//      case HttpRequest(OPTIONS, _, _, _, _) =>
//        log.info("root request 4")
//        sender ! task
//
//      case res: HttpRequest =>
//        log.info(s"Unkonwn resource: $res")
//        sender ! HttpResponse(status = 404, entity = "Unknown resource!",  headers = headers)
//    }
//
//  }

  lazy val handler = actorSystem.actorOf(Props(new RssService), name = "handler")

//  io.IO(Http) ! Http.Bind(handler, "localhost", 8080)
  IO(Http) ! Http.Bind(handler, interface = "0.0.0.0", port = 8081)

  def f(str1: String, str2: String, ind: Int = 0): List[Int] = { val index = str1.indexOf(str2); if (index > -1) index :: f(str1, str2, index) else List.empty }
}

