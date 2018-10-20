import akka.actor._

class MainActor extends Actor {
  override def receive: Receive = ???
}

class BlockChain extends Actor{
  var block: Map[header,PreLoad] = ???
  case class header(){
    var currentHigh: Int = 0
    var latestHash = Array[Byte](10)
    val nonce: Long = 0
    val timestamp: Long = 0
  }
  case class PreLoad(){
    val EmptySeq = Seq[Int](1)
  }
  def AddToBlockChain: Unit = {}

  def Validate: Unit = {}
  override def receive = ???
}

class Miner extends Actor{
  override def receive = ???

  def CreateNewBlock: Unit = {}
}

val system = ActorSystem("MainActor")
val blockChain = system.actorOf(Props[BlockChain],"BlockChain")
val miner = system.actorOf(Props[Miner],"Miner")