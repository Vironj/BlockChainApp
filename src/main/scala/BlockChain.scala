import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import BlockChain.sha256
import Miner.{BlockRequest, NextBlock}
import akka.actor._

class BlockChain extends Actor {
  var blockChain: Map[Int, Block] = Map.empty

  def addToBlockChain(newBlock: Block): Unit = {
    //newBlock.header.latestHash = blockChain(newBlock.header.currentHeight-1).header.latestHash
    blockChain += newBlock.header.currentHeight -> newBlock
    println(blockChain(newBlock.header.currentHeight))
  }

  def validate(newBlock: Block): Unit = {
    if (blockChain.isEmpty) {
      addToBlockChain(newBlock); sender() ! NextBlock(Some(newBlock))
    } else {
      if (sha256(newBlock) sameElements newBlock.header.latestHash) {
        //newBlock.header.latestHash = blockChain(newBlock.header.currentHeight-1).header.latestHash
        addToBlockChain(newBlock); sender() ! NextBlock(Some(newBlock))
      }
      else  sender() ! NextBlock(Some(blockChain(newBlock.header.currentHeight-1)))
    }
  }

  override def receive: PartialFunction[Any, Unit] = {

    case BlockRequest =>
      if (blockChain.isEmpty) sender() ! NextBlock(None) else sender() ! NextBlock(Some(blockChain(1)))

    case block: Block => validate(block)
  }
}

object BlockChain {
  def sha256(block: Block): Array[Byte] = {
    val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
    var hash: Array[Byte] =
      digest.digest(block.header.nonce.toString.getBytes(StandardCharsets.UTF_8))
    for (i <- 0 until 99) {
      hash = digest.digest(hash)
    }
    hash
  }
}

