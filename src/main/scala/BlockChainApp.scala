import akka.actor._

object BlockChainApp extends App {
  val system = ActorSystem("MainActor")
  val blockChain = system.actorOf(Props[BlockChain],"BlockChain")
  val miner = system.actorOf(Props[Miner],"Miner")
  system.terminate()
}
