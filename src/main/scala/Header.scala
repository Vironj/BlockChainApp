import akka.util.ByteString

case class Header(currentHeight: Int, latestHash: Array[Byte], nonce: Long, timestamp : Long)