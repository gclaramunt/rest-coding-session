package services

import spray.routing.{HttpService, SimpleRoutingApp}
import akka.actor._
import spray.json._
import spray.httpx.SprayJsonSupport._


object Main extends App with SimpleRoutingApp with Routes {
  implicit val system = ActorSystem("webservices-actors")

  startServer(interface = "localhost", port = 8000) {
    println(routes)
    routes
  }
}

case class User( id: Int, name: String)
object User extends DefaultJsonProtocol  {
  implicit val UserFormats = jsonFormat2(User.apply)
}



trait Routes extends HttpService {

  val database = scala.collection.mutable.ListBuffer[User]()

  val ping = path("ping"){ 
    get { complete("pong1\n"); complete("no pong\n") } ~ 
    post { complete("NO!\n") }
  } 

  val users = path("users"){
      get { complete(database.toList) } ~
      post { entity(as[User]){ user  => database += user; complete(s"OK") } } 
    } ~ 
    path( "users" /  Segment ) { userId => get { complete(database.find(_.id == userId.toInt)) } }

  val parameterspath = path("parameters") { get { parameters('param, 'param2 ?) { (p1,p2) => complete(s" param1=$p1 and param2=$p2 \n")}  }}
  
  val echo = path("echo") { entity(as[String]){ msg => complete(msg) }} 

  val routes = ping ~ echo ~ users ~ parameterspath
}
