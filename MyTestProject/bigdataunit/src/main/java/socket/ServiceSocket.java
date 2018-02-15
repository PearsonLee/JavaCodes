package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;

/**
 * Created by PearsonLee on 2018/1/1.
 */
public class ServiceSocket {

  public static void main(String[] args) throws IOException {

    // 创建serversocket对象，绑定本机8899端口
    ServerSocket serverSocket = new ServerSocket();
    serverSocket.bind(new InetSocketAddress("localhost",8899));

    //监听客户端


  }
}
