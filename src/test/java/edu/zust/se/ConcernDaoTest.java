package edu.zust.se;

import edu.zust.se.dao.ConcernDao;
import edu.zust.se.dao.UserDao;
import edu.zust.se.entity.ConcernEntity;
import edu.zust.se.entity.UserEntity;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class ConcernDaoTest {
    ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    ConcernDao concernDao = ac.getBean(ConcernDao.class,"concernDao");
    @Test
    public void testInsertConcern(){
        UserDao userDao = ac.getBean(UserDao.class,"userDao");
        UserEntity concernerUser = userDao.getUser(1);
        UserEntity concernedUser = userDao.getUser(10);
        ConcernEntity concernEntity = new ConcernEntity();
        concernEntity.setConcernerUser(concernerUser);
        concernEntity.setConcernedUser(concernedUser);
        concernEntity.setConcernTime(new Date());
        concernDao.insertConcern(concernEntity);
    }
    @Test
    public void testGetConcern(){
        ConcernEntity concern = concernDao.getConcern(9);
//        System.out.println("id: "+concern.getId());
//        System.out.println("concerner_id: "+concern.getConcernerUser().getId());
//        System.out.println("concerned_id: "+concern.getConcernedUser().getId());
//        System.out.println("concern_time: "+concern.getConcernTime());
        System.out.println(concern.toString());
    }
    @Test
    public void testUpdateConcern(){
        UserDao userDao = ac.getBean(UserDao.class,"userDao");
        ConcernEntity concern = concernDao.getConcern(96);
        UserEntity concernedUser = userDao.getUser(10);
        concern.setConcernedUser(concernedUser);
        concern.setConcernTime(new Date());
        concernDao.updateConcern(concern.getId(),concern);
    }
    @Test
    public void testDeleteConcern(){
        concernDao.deleteConcern(96);
    }
}
