import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import Miner.{BlockRequest, NextBlock}
import akka.actor.Actor


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
    val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
    var hash: Array[Byte] = digest.digest(block.header.nonce.toString.getBytes(StandardCharsets.UTF_8))
    for (i <- 0 until 99) {
      hash = digest.digest(hash)
    }
    val newBlock: Block =
      Block(Header(block.header.currentHeight + 1, Array.empty, 1, System.currentTimeMillis()), Payload(Seq()))
    newBlock
  }

  def createGenesisBlock: Block = Block(Header(0, Array.empty, 1, System.currentTimeMillis()), Payload(Seq()))

}

object Miner {

  case class NextBlock(block: Option[Block])

  case object BlockRequest

}