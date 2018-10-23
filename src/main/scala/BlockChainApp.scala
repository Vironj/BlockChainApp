import akka.actor._

object BlockChainApp extends App {
  val system: ActorSystem = ActorSystem()
  val blockChain: ActorRef = system.actorOf(Props[BlockChain],"BlockChain")
  val miner = system.actorOf(Props[Miner],"Miner")
}
