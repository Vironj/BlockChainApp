import Miner.NextBlock
import akka.Main
import akka.actor._

class Miner extends Actor{
  override def preStart(): Unit = {
    self ! NextBlock
  }
  override def receive= {
    case NextBlock => {
     context.actorSelection("/MainActor/BlockChain") ! NextBlock
    }
    case None => sender() ! createGenisisBlock
    case Block => sender() ! createNewBlock
  }

  def createNewBlock = {}
  def createGenisisBlock ={}
}

object Miner {
  case class NextBlock(block: Option[Block])
}