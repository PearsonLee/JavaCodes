package zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by PearsonLee on 2017/12/17.
 */
public class ZkService {

    private static final String CONNECTSTRING = "192.168.182.144";
    private static final int SESSIONTIMEOUT = 5000;
    private static ZooKeeper zooKeeper;
    private static final String rootPath = "/zookeeper";

    public static void main(String[] args) throws Exception {

        /**
         * 1. 连接zookeeper
         *
         * 2. 注册信息
         *
         * 3. handle something
         */
        ZkService zk = new ZkService();
        zk.getConnect();

        String hostname = args[0];
        registerServer(hostname);

        handleBusiness(hostname);

    }

    private static void handleBusiness(String hostname) throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
        System.out.println(hostname + "starts working...");
    }

    private static void registerServer(String hostname) throws KeeperException, InterruptedException, UnsupportedEncodingException {
        String newSubNode = zooKeeper.create(rootPath + "/server", hostname.getBytes("utf-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname + "is online..."+ newSubNode);
    }

    private void getConnect() throws IOException {

        zooKeeper = new ZooKeeper(CONNECTSTRING, SESSIONTIMEOUT, new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getType() + "---" + watchedEvent.getPath());
                try {
                    zooKeeper.getChildren(rootPath, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
