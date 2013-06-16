package com.daggerfrog.front.api

import com.tinkerpop.blueprints.impls.neo4j.Neo4jGraph
import scala.collection.JavaConversions._

object API extends App {

  val categories = List("Java", "Algorithms", "Time Management", "Machine Learning")


  def createCategory(name: String)(implicit graph: Neo4jGraph) {
    println(s"create category for $name")
    val category = graph.addVertex()
    category.setProperty("type",  "category")
    category.setProperty("title", name)
  }

  def createCategories()(implicit graph: Neo4jGraph) {
    val categories = List("Java", "Algorithms", "Time Management", "Machine Learning")

    println(categories)
    categories.foreach(createCategory)
    graph.commit()
  }

  def createTask(link: String, categoryName: String)(implicit graph: Neo4jGraph) = {
    val category = graph
      .getVertices
      .filter(v => v.getProperty("type").toString == "category"
        && v.getProperty("title").toString == categoryName)
      .head
    val task = graph.addVertex()
    task.setProperty("type", "task")
    task.setProperty("link", link)
    graph.addEdge(None, task, category, "hasCategory")
    graph.commit()
  }

  override def main(args: Array[String]) = {
    try {
      implicit val graph = new Neo4jGraph("./neo-db")
      graph.getVertices().foreach(graph.removeVertex)
      createCategories()
      graph.getVertices("type", "category").foreach(println)
      println("ddd")
      graph
        .getVertices
        .filter{v => v.getProperty("type").toString == "category"}.foreach(println)
      createTask("http://www.aaronsw.com/weblog/productivity", "Machine Learning")
      println("fff")
      graph.getVertices.foreach(v => println(v.getId))
      println("ok")
    }
    finally {
      System.exit(0)
    }
  }
}
