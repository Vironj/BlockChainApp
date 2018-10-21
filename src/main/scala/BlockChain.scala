import Miner.NextBlock
import akka.actor._

class BlockChain extends Actor{
  val BlockChain: Map[Int,Block] = Map.empty

  def addToBlockChain: Unit = {
  }

  def validate: Unit = {
    for(i <- 0 until 100){???}
    addToBlockChain
  }

  override def receive = {

    case NextBlock => if (BlockChain.isEmpty) sender() ! None
    else sender() ! BlockChain
    case createNewblock =>
      validate
      sender() ! BlockChain
  }
}

