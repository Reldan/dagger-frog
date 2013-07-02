package com.daggerfrog.front.api

import com.daggerfrog.common.{ConfigComponent, ActorSystemComponent}

trait ComponentRegistry
  extends ActorSystemComponent
  with ConfigComponent
  with RestApiComponent
