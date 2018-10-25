import java.security.MessageDigest

import Miner.{BlockRequest, NextBlock}
import akka.actor._
import sun.nio.cs.UTF_8

class BlockChain extends Actor {
  val BlockChain: Map[Int, Block] = Map.empty

  def addToBlockChain: Unit = {
  }

  def validate(block: Block) = {
    val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
    var hash: Array[Byte] = digest.digest(block.header.nonce.toString.getBytes(UTF_8))
    for (i <- 0 until 99) {
      hash = digest.digest(hash)
    }
    addToBlockChain
    //if ( validate == false ) last block from BlockChain else
    sender() ! NextBlock(Some(block))
  }

  override def receive = {

    case BlockRequest =>
      if (BlockChain.isEmpty) sender() ! NextBlock(None) else sender() ! NextBlock(Some(BlockChain(1)))

    case block: Block => validate(block)
  }
}

object BlockChain {



}

