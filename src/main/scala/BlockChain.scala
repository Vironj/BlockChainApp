import akka.actor.Actor

class BlockChain extends Actor{
  val BlockChain: Map[Int,Block] = ???

  def addToBlockChain: Unit = {}

  def validate: Unit = {}

  override def receive = ???
}

