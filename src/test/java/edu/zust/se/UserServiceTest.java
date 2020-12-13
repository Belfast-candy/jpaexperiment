package edu.zust.se;

import edu.zust.se.entity.PictureEntity;
import edu.zust.se.entity.UserEntity;
import edu.zust.se.service.UserService;
import edu.zust.se.service.UserServiceImpl;
import edu.zust.se.util.DateUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class UserServiceTest {
    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    UserService userService = applicationContext.getBean(UserService.class,"userService");
    //因为在applicationContext.xml的包扫描中没有扫描到test，所以得自己造entityManagerFactory
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    @Test
    public void testGetBigV(){
        List<UserEntity> userEntityList = userService.getBigV();
        List<UserEntity> concernerList;
        Query query = entityManager.createQuery("select concern.concernerUser from ConcernEntity concern where concern.concernedUser =:userEntity");
        for (UserEntity userEntity : userEntityList){
            query.setParameter("userEntity",userEntity);
            concernerList = query.getResultList();
            System.out.println("id: "+userEntity.getId()+" userName: "+userEntity.getUsername() +" concernerNum: "+concernerList.size());
//            System.out.println("id: "+userEntity.getId()+" userName: "+userEntity.getUsername() +" concernerNum: "+userEntity.getConcernerUsers().size());
            System.out.println();
        }
    }
    @Test
    public void testGetUserPictures(){
        List<PictureEntity> pictureEntityList = userService.getUserPictures(9,1,3);
        for (PictureEntity pictureEntity : pictureEntityList){
            System.out.println(pictureEntity.toString());
            System.out.println();
        }
    }
    @Test
    public void testGetUserPicturesBetweenTime(){
        Date startTime = DateUtil.getDate(2020,11,28,0,0,0);
        Date endTime = DateUtil.getDate(2020,11,29,0,0,0);
//        Date startTime = null;
//        Date endTime = null;
        List<PictureEntity> pictureEntityList = userService.getUserPictures(9,startTime,endTime,1,3);
        for (PictureEntity pictureEntity : pictureEntityList){
            System.out.println(pictureEntity.toString());
            System.out.println();
        }
    }
    @Test
    public void testGetFollows(){
        List<UserEntity> userEntityList = userService.getFollows(7);
        for (UserEntity userEntity : userEntityList){
            System.out.println(userEntity.toString());
            System.out.println();
        }
    }
    @Test
    public void testGetFollowPictures(){
        List<PictureEntity> pictureEntityList = userService.getFollowPictures(7,1,3);
        for(PictureEntity pictureEntity : pictureEntityList){
            System.out.println(pictureEntity.toString());
            System.out.println();
        }
    }
    @Test
    public void testGetFollowPicturesBetweenTime(){
        Date startTime = DateUtil.getDate(2020,11,27,0,0,0);
        Date endTime = DateUtil.getDate(2020,11,28,0,0,0);
//        Date startTime = DateUtil.getDate(2020,11,28,0,0,0);
//        Date endTime = null;
        List<PictureEntity> pictureEntityList = userService.getFollowPictures(7,startTime,endTime,1,2);
        for (PictureEntity pictureEntity : pictureEntityList){
            System.out.println(pictureEntity.toString());
            System.out.println();
        }
    }
    @Test
    public void testGetRecentPictures(){
        List<PictureEntity> pictureEntityList = userService.getRecentPictures(3,4);
        for (PictureEntity pictureEntity : pictureEntityList){
            System.out.println(pictureEntity.toString());
            System.out.println();
        }
    }
    @Test
    public void testFindUsersByName(){
        List<UserEntity> userEntityList = userService.findUsersByName("白");
        for (UserEntity userEntity : userEntityList){
            System.out.println(userEntity.toString());
            System.out.println();
        }
    }
    @Test
    public void testFindpictureByName(){
        List<PictureEntity> pictureEntityList = userService.findpictureByName("咪");
        for (PictureEntity pictureEntity : pictureEntityList){
            System.out.println(pictureEntity.toString());
            System.out.println();
        }
    }
    @Test
    public void testFindPictureByTag(){
        List<PictureEntity> pictureEntityList = userService.findPictureByTag("动物");
        for (PictureEntity pictureEntity : pictureEntityList){
            System.out.println(pictureEntity.toString());
            System.out.println();
        }
    }
}
