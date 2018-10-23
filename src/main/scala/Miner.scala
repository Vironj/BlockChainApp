
import Miner.{BlockRequest, NextBlock}
import akka.actor.Actor
import akka.util.ByteString

class Miner extends Actor {
  override def preStart(): Unit = {
    context.actorSelection("/user/BlockChain") ! BlockRequest
  }

  override def receive = {
    case NextBlock(block) => block match {
      case Some(value) =>
       val newBlock: Block = createNewBlock(value)
        sender() ! NextBlock(Some(newBlock))
      case None => sender() ! createGenesisBlock
    }

  }

  def createNewBlock(block: Block): Block = {
    for (i <- 0 until 100) {
      ???
    }
    block
  }

  def createGenesisBlock: Block = Block(Header(0, ByteString.empty, 1, System.currentTimeMillis()), Payload(Seq()))

}

object Miner {

  case class NextBlock(block: Option[Block])

  case object BlockRequest

}