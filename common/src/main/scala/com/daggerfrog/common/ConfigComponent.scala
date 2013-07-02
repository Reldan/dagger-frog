package com.daggerfrog.common

import com.typesafe.config.ConfigFactory

trait ConfigComponent {
  lazy val config = ConfigFactory.load()
}
