package com.ldy.study;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoCommonTest {

    /**
     * 无密码的连接方式
     */
    @Test
    public void test1() {
        try {
            MongoClient monClient = new MongoClient("10.91.19.103", 24000);
            MongoDatabase mongoDatabase = monClient.getDatabase("vehiclemg");
            System.out.println(mongoDatabase.getName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 有密码的连接方式
     */
    @Test
    public void test2() {
        try {
            ServerAddress serverAddress = new ServerAddress("10.91.19.103", 24000);
            List<ServerAddress> addrs = new ArrayList<>();
            addrs.add(serverAddress);
            // 三个参数分别为 用户名 数据库名称 密码
            MongoCredential mongoCredential = MongoCredential.createCredential("vehicleopr", "vehiclemg", "vehicleopr1234".toCharArray());

            List<MongoCredential> credentials = new ArrayList<>();
            credentials.add(mongoCredential);

            // 通过连接认证获取MongoDB连接
            MongoClient mongoClient = new MongoClient(addrs, credentials);
            MongoDatabase mongoDb = mongoClient.getDatabase("vehiclemg");

            MongoCollection<Document> collection = mongoDb.getCollection("vmCarState");
            System.out.println("Collection Count:" + collection.count());

            // 检索所有文档
            /**
             * 1. 获取迭代器FindIterable<Document>
             * 2. 获取游标MongoCursor<Document>
             * 3. 通过游标遍历检索出的文档集合
             */
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while (mongoCursor.hasNext()) {
                System.out.println(mongoCursor.next());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        try {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
            MongoTemplate mongoTemplate = (MongoTemplate) applicationContext.getBean("mongoTemplate");
            Query query = new Query(Criteria.where("_id").is("20170117000007"));
            List<Object> findL = mongoTemplate.find(query, Object.class);
            System.out.println(findL);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
