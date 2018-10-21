import Miner.NextBlock
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
    case block: Block => sender() ! createNewBlock
  }

  def createNewBlock = {
    for(i <- 0 until 100){???}
  }
}
  def createGenisisBlock = {
    val header = new Header(0,Array(0),1,0)
    val payLoad = new PayLoad(Seq(0))
    val genblock = new Block(header,payLoad)
  }


object Miner {
  case class NextBlock(block: Option[Block])
}