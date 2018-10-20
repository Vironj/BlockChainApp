import akka.actor._

class MainActor extends Actor {
  override def receive = ???
}

val system = ActorSystem("MainActor")
val blockChain = system.actorOf(Props[BlockChain],"BlockChain")
val miner = system.actorOf(Props[Miner],"Miner")