package com.spring.mongo;

import com.mongodb.MongoClient;
import com.spring.mongo.entity.Member;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

/**
 * @author Spring 花开不合阳春暮
 * @since 2020/7/7
 */
public class MorphiaTest {

    public static void main(String[] args) {
        Morphia morphia = new Morphia();
        Datastore ds = morphia.createDatastore(new MongoClient("127.0.0.1", 27017), "spring-demo");

        /*Member member = new Member();
        member.setName("晓晓");
        member.setAge(18);
        Key<Member> key = ds.save(member);
        System.out.println(key.toString());*/

        Query<Member> query = ds.createQuery(Member.class);

        for (Member m : query) {
            System.out.println(m);
        }
    }

}
