import Miner.{BlockRequest, NextBlock}
import akka.actor._

import scala.collection.immutable
import scala.util.Try

class BlockChain extends Actor {
  val BlockChain: Map[Int, Block] = Map.empty

  def addToBlockChain: Unit = {
  }

  def validate(block: Block) = {
    for (i <- 0 until 100) {
      ???
    }
    addToBlockChain
    //if ( validate false ) last block from BlockChain else
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

