package com.ldy.study.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.ldy.study.pojo.User;
import com.ldy.study.service.MongonDbService;

@Service("mongoDBService")
public class MongoDbServiceImpl implements MongonDbService<User> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void add(User obj, String collName) {
        mongoTemplate.insert(obj, collName);
    }

    @Override
    public void delete(User obj, String collName) {
        // TODO Auto-generated method stub
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(obj.getId()).and("name").is(obj.getName()).and("_class").is(obj.getClass()));
        mongoTemplate.remove(query, obj.getClass(), collName);
    }

    @Override
    public void update(User obj, String collName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(obj.getId()));

        Update update = new Update();
        update.set("name", obj.getName());
        mongoTemplate.updateFirst(query, update, obj.getClass(), collName);
    }

    @Override
    public void query(User obj, String collName) {
        System.out.println("AA:" + User.class + "  BB:" + obj.getClass());
        Query query = new Query();
        query.addCriteria(new Criteria().where("age").is(obj.getAge()));
        System.out.println(mongoTemplate.find(query, obj.getClass(), collName));
    }

    @Override
    public void createCollection(Class cls, String collName) {
        mongoTemplate.createCollection(cls);
    }

    @Override
    public void queryAll(Class<User> cls, String collName) throws Exception {
        List<User> list = mongoTemplate.findAll(cls, "myTest");
        for (User user : list) {
            System.out.println(user);
        }
    }

}
