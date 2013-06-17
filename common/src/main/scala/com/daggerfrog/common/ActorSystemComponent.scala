package com.daggerfrog.common

import akka.actor.ActorSystem


trait ActorSystemComponent {
  implicit val actorSystem: ActorSystem
}
