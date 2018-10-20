import akka.actor.Actor

class BlockChain extends Actor{
  val BlockChain: Map[Int,Block] = ???
  case class Block(header: Header, payLoad: PayLoad)
  case class Header(currentHeight: Int, latestHash: Array[Byte], nonce: Long, timestamp : Long){

  }
  case class PayLoad(Seq: Seq[Int]){

  }
  def addToBlockChain: Unit = {}

  def validate: Unit = {}
  override def receive = ???
}

