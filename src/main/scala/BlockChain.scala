import BlockChain.{NextBlock}
import akka.actor._

class BlockChain extends Actor{
  val BlockChain: Map[Int,Block] = Map.empty

  def addToBlockChain: Unit = {
  }

  def validate = {
    for(i <- 0 until 100){???}
    addToBlockChain
  }

  override def receive = {

    case NextBlock => if (BlockChain.isEmpty) sender() ! None
    else sender() ! NextBlock(BlockChain(1))
    case createGenesisBlock => {
      validate
      sender() ! NextBlock(BlockChain(1))
    }
  }
}

object BlockChain {
  case class NextBlock(block: Block)
}

