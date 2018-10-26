import Miner.{BlockRequest, NextBlock}
import akka.actor._
import BlockChain.sha256


class Miner extends Actor {
  override def preStart(): Unit = {
    context.actorSelection("/user/BlockChain") ! BlockRequest
  }

  override def receive: PartialFunction[Any, Unit] = {
    case NextBlock(block) => block match {
      case Some(value) =>
        val newBlock: Block = createNewBlock(value)
        sender() ! NextBlock(Some(newBlock))
      case None => sender() ! createGenesisBlock
    }
  }

  def createNewBlock(block: Block): Block = {
    val newBlock: Block =
      Block(Header(block.header.currentHeight + 1, sha256(block), 1, System.currentTimeMillis()), Payload(Seq()))
    context.actorSelection("/user/BlockChain") ! newBlock
    newBlock
  }

  def createGenesisBlock: Block = Block(Header(0, Array(0), 1, System.currentTimeMillis()), Payload(Seq()))

}

object Miner {

  case class NextBlock(block: Option[Block])

  case object BlockRequest

}