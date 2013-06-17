package com.daggerfrog.front.api


import scala.concurrent.duration._
import akka.actor._
import spray.http._
import HttpMethods._
import MediaTypes._
import spray.util.SprayActorLogging
import akka.io
import spray.can.Http
import com.daggerfrog.common.ActorSystemComponent


trait KeyStoreRestComponent {
  this: ActorSystemComponent =>

  lazy val index = HttpResponse(
    entity = HttpEntity(`text/html`,
      """<html>
          <body>
            <p>Defined resources:</p>
            <ul>
              <li><a href="/keys">/keys</a></li>
            </ul>
          </body>
        </html>""".getBytes
    )
  )

  lazy val actor = new Actor with SprayActorLogging {
    implicit val timeout: akka.util.Timeout = 1.second // for the actor 'asks' we use below

    val keys: Uri = "/keys"

    def receive = {

      case HttpRequest(GET, Uri./, _, _, _) =>
        sender ! index

      case HttpRequest(GET, keys, _, _, _) =>
        val originalSender = sender

      case _: HttpRequest => sender ! HttpResponse(status = 404, entity = "Unknown resource!")
    }


  }

  lazy val handler = actorSystem.actorOf(Props(actor))

  io.IO(Http) ! Http.Bind(handler, "localhost", 8080)
//  newHttpServer(handler) ! Bind(interface = "0.0.0.0", port = 80)
}

