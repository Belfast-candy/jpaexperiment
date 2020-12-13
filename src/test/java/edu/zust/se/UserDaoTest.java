package edu.zust.se;

import edu.zust.se.dao.PictureDao;
import edu.zust.se.dao.UserDao;
import edu.zust.se.entity.PictureEntity;
import edu.zust.se.entity.UserEntity;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.*;
import java.util.*;

public class UserDaoTest {
    ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    UserDao userDao = ac.getBean(UserDao.class,"userDao");
    @Test
    public void testInsertUser(){
        UserEntity user = new UserEntity();
        user.setUsername("普通用户11");
        user.setPassword("123456");
        user.setName("用户11");
        user.setIntro("现在已经工作了");
        user.setGender("男");
        user.setProvince("AA省");
        user.setCity("bb市");
        user.setRegistTime(new Date());
        user.setType("员工");
        user.setEmail("yonghu11@qq.com");
        user.setMobile("1231231213");
        user.setQq("123456789");
        userDao.insertUser(user);
    }
    @Test
    public void testUpdateUser(){
        UserEntity user = userDao.getUser(20);
        user.setPassword("654321");
        user.setQq("123456");
        userDao.updateUser(user.getId(),user);
    }
    @Test
    public void testGetUser(){
        UserEntity user = userDao.getUser(1);
        System.out.println(user.toString());
    }
    @Test
    public void testDeleteUser(){
//        UserEntity user = userDao.getUser(8);
        userDao.deleteUser(20);
    }
    @Test
    public void sayHelloWorld(){
        System.out.println("Hello World!");
    }
    @Test
    public void sayDog(){
        System.out.println("Dog");
    }
    @Test
    public void sayCat(){
        System.out.println("Cat");
    }
}
