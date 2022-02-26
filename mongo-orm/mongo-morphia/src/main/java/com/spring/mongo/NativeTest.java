package com.spring.mongo;

import com.mongodb.*;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/7
 */
public class NativeTest {

    public static void main(String[] args) {
        // 配置连接信息
        MongoClient mongo = new MongoClient("127.0.0.1", 27017);
        // 建立连接
        DB db = new DB(mongo, "spring-demo");
        //System.out.println(db.getCollectionNames());

        DBCollection collection = db.getCollection("t_member");
        // 游标 (ResultSet    hasNext())
        DBCursor cursor = collection.find();
        for (DBObject object : cursor) {
            System.out.println(object);
        }
    }

}
