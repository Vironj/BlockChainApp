import Miner.NextBlock
import akka.actor._

class Miner extends Actor{
  override def preStart(): Unit = {
    self ! NextBlock
  }
  override def receive= {
    case NextBlock => {
      BlockChain ! NextBlock
    }
    case
  }

  def createNewBlock: Unit = {}
}

object Miner {
  case class NextBlock(block: Option[Block])
}