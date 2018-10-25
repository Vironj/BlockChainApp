import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import Miner.{BlockRequest, NextBlock}
import akka.actor._

class BlockChain extends Actor {
  var BlockChain: Map[Int, Block] = Map.empty

  def addToBlockChain(block: Block): Unit = {
    BlockChain += block.header.currentHeight -> block
  }

  def validate(newBlock: Block): Unit = {
    if (BlockChain.isEmpty) {
      addToBlockChain(newBlock); sender() ! NextBlock(Some(newBlock))
    //println(BlockChain)
    } else {
      val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
      var hash: Array[Byte] =
        digest.digest(BlockChain(newBlock.header.currentHeight - 1).header.nonce.toString.getBytes(StandardCharsets.UTF_8))
      for (i <- 0 until 99) {
        hash = digest.digest(hash)
      }
      if (hash sameElements BlockChain(newBlock.header.currentHeight).header.latestHash) {
        addToBlockChain(newBlock); sender() ! NextBlock(Some(newBlock))
      }
      else  sender() ! NextBlock(Some(BlockChain(newBlock.header.currentHeight-1)))
    }
  }

  override def receive: PartialFunction[Any, Unit] = {

    case BlockRequest =>
      if (BlockChain.isEmpty) sender() ! NextBlock(None) else sender() ! NextBlock(Some(BlockChain(1)))

    case block: Block => validate(block)
  }
}

object BlockChain {


}

