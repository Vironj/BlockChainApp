import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import BlockChain.sha256
import Miner.{GenBlockRequest, NextBlock}
import akka.actor._

class BlockChain extends Actor {
  var blockChain: Map[Int, Block] = Map.empty


  def validate(newBlock: Block): Unit = {
    if (blockChain.isEmpty) {
      blockChain = blockChain.updated(newBlock.header.currentHeight, newBlock)
      sender() ! NextBlock(Some(newBlock))
    } else {
      if (sha256(blockChain(newBlock.header.currentHeight - 1)) sameElements newBlock.header.latestHash) {
        blockChain = blockChain.updated(newBlock.header.currentHeight, newBlock)
        println(blockChain(newBlock.header.currentHeight))
        sender() ! NextBlock(Some(newBlock))
      }
      else sender() ! NextBlock(Some(blockChain.last._2))
    }
  }

  override def receive: Receive = {

    case GenBlockRequest =>
      if (blockChain.isEmpty) sender() ! NextBlock(None) else sender() ! NextBlock(Some(blockChain.last._2))

    case block: Block => validate(block)
  }
}

object BlockChain {
  def sha256(block: Block): Array[Byte] = {
    val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
    var hash: Array[Byte] = digest.digest(block.header.nonce.toString.getBytes(StandardCharsets.UTF_8))
    for (i <- 0 until 99) hash = digest.digest(hash)
    println(block.header.latestHash)
    hash
  }
}