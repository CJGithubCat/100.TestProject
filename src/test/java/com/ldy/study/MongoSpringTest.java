package com.ldy.study;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ldy.study.pojo.User;
import com.ldy.study.service.MongonDbService;

@ContextConfiguration({ "classpath:spring.xml" })
@RunWith(SpringJUnit4ClassRunner.class)

public class MongoSpringTest {

    @Autowired
    private MongonDbService<User> mongonDbServiceImpl;

    @Test
    public void testAdd() {
        try {
            User user = new User(2, "aaa", 20);
            mongonDbServiceImpl.add(user, "myTest");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            User user = new User(2, "aaa", 20);
            mongonDbServiceImpl.delete(user, "myTest");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        try {
            User user = new User(2, "bbb", 18);
            mongonDbServiceImpl.update(user, "myTest");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuery() {
        try {
            User user = new User(2, "aaa", 20);
            mongonDbServiceImpl.query(user, "myTest");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryAll() {
        try {
            mongonDbServiceImpl.queryAll(User.class, "myTest");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
