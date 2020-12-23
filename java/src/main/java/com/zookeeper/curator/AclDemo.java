package com.zookeeper.curator;

import org.apache.curator.framework.AuthInfo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 春阳
 * @date 2020-12-23 12:13
 */
public class AclDemo {
    private static String CONNECTION_STR="192.168.25.128:2181,192.168.25.129:2181,192.168.25.130:2181";

    public static void main(String[] args) throws Exception {
        demo2();
    }

    private static void demo1() throws Exception {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().
                connectString(CONNECTION_STR).sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
        curatorFramework.start();
        List<ACL> acls = new ArrayList<>();
        Id id1 = new Id("digest", DigestAuthenticationProvider.generateDigest("u1:us"));
        Id id2 = new Id("digest", DigestAuthenticationProvider.generateDigest("u2:us"));
        //针对ul，有 read 权限， 针对 u2 有读和删除权限
        acls.add(new ACL(ZooDefs.Perms.READ,id1));
        acls.add(new ACL(ZooDefs.Perms.DELETE | ZooDefs.Perms.READ,id2));
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).withACL(acls,false).forPath("/auth","sc".getBytes());

        // 修改已经存在节点的权限
        //curatorFramework.setACL().withACL(ZooDefs.Ids.CREATOR_ALL_ACL).forPath("/auth");
    }

    private static void demo2() throws Exception {
        AuthInfo authInfo=new AuthInfo("digest","u1:us".getBytes());
        List<AuthInfo> authInfos=new ArrayList<>();
        authInfos.add(authInfo);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder().
                connectString(CONNECTION_STR).sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,3)).
                authorization(authInfos).build();
        curatorFramework.start();

        curatorFramework.setData().forPath("/auth","up".getBytes());
    }
}
