import akka.util.ByteString

case class Header(currentHeight: Int, latestHash: ByteString, nonce: Long, timestamp : Long)