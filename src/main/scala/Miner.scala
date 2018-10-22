import Miner.NextBlock
import akka.actor._

class Miner extends Actor{
  override def preStart(): Unit = {
    context.actorSelection("/MainActor/BlockChain") ! NextBlock
  }
  override def receive= {
    case None => sender() ! createGenesisBlock
    case BlockChain.NextBlock => sender() ! createNewBlock
  }

  def createNewBlock = {
    for(i <- 0 until 100){???}
  }
}
  def createGenesisBlock: Block = {
    val header = new Header(0,Array(0),1,0)
    val payLoad = new PayLoad(Seq(0))
    val genblock = new Block(header,payLoad)
    genblock
  }


object Miner {
  case class NextBlock(block: Option[Block])
}