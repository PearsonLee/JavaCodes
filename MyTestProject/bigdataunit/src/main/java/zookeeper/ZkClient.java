package zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by PearsonLee on 2017/12/17.
 */
public class ZkClient {

  private static ZooKeeper zooKeeper;
  private static final String CONNECTSTRING = "192.168.182.144";
  private static final int SESSIONTIMEOUT = 5000;
  private static final String rootPath = "/zookeeper";
  private static List<String> serverList = new ArrayList<String>();

  public static void main(String[] args) throws Exception {

    /**
     * 客户端动态感知线上系统服务器是否ok
     * 仅需要监控获取zookeeper节点信息即可
     */

    /**
     * 1. 创建zk连接
     *
     * 2. 获取并跟新节点信息
     */
    ZkClient zkClient = new ZkClient();
    zkClient.getConnect();

//        Thread.sleep(1000);
    //TODO(SLi) handle something
    zkClient.handleBusiniss();
  }

  private static void getConnect() throws IOException {
    zooKeeper = new ZooKeeper(CONNECTSTRING, SESSIONTIMEOUT, new Watcher() {
      public void process(WatchedEvent watchedEvent) {
        System.out.println("changed...");

        try {
          getServerList();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  private static void getServerList() throws KeeperException, InterruptedException {
    List<String> childrens = zooKeeper.getChildren(rootPath, true);
    List<String> servers = new ArrayList<String>();
    for (String node : childrens) {
      byte[] nodeInfo = zooKeeper.getData(rootPath + "/" + node, false, null);
      servers.add(new String(nodeInfo));
    }
    serverList = servers;

    //打印服务器列表
    System.out.println(serverList);
  }

  private static void handleBusiniss() throws InterruptedException {
    System.out.println("Client starts working...");
    Thread.sleep(Long.MAX_VALUE);
  }

}
