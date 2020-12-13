package edu.zust.se;

import edu.zust.se.entity.ConcernEntity;
import edu.zust.se.entity.PictureEntity;
import edu.zust.se.entity.UserEntity;
import org.junit.Test;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JpaTest {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    @Test
    public void testAddUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("小李");
        userEntity.setPassword("123456");
        userEntity.setName("李华");
        userEntity.setIntro("现在是学生");
        userEntity.setGender("男");
        userEntity.setProvince("杭州");
        userEntity.setCity("西湖区");
        userEntity.setRegistTime(new Date());
        userEntity.setType("学生");
        userEntity.setEmail("xiaohua@qq.com");
        userEntity.setMobile("123123123");
        userEntity.setQq("222222");
        userEntity.setStatus("");
        transaction.begin();
        entityManager.persist(userEntity);
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
    @Test
    public void testUpdateUser(){
        UserEntity user = entityManager.find(UserEntity.class,19);
        transaction.begin();
        user.setPassword("654321");
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
    @Test
    public void testDeleteUser(){
        UserEntity user = entityManager.find(UserEntity.class,19);
        transaction.begin();
        entityManager.remove(user);
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
    @Test
    public void testListAllUsers(){
        Query query = entityManager.createQuery("from UserEntity");
        List<UserEntity> users = query.getResultList();
        for (UserEntity user : users){
            System.out.println(user.toString());
            System.out.println();
        }
        entityManager.close();
        entityManagerFactory.close();
    }
    @Test
    //测试给picture表添加数据
    public void testAddPicture(){
        PictureEntity picture = new PictureEntity();
        picture.setName("猫咪");
        picture.setIntro("这是一只猫咪");
        picture.setTags("动物");
        picture.setUploadTime(new Date());
        picture.setClickNum(0);
        transaction.begin();
        entityManager.persist(picture);
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
    @Test
    //测试给user添加图片
    //一对多插入属性应该在多端那边加入一端
    public void testAddUserPicture(){
        UserEntity user = entityManager.find(UserEntity.class,1);
        PictureEntity picture = entityManager.find(PictureEntity.class,1);
//        Set<PictureEntity> pictures = new HashSet<>();
//        pictures.add(picture);
        transaction.begin();
//        user.setPictureEntitySet(pictures);
        picture.setUserEntity(user);
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
    @Test
    //列出user的图片
    public void testListUserPictures(){
       UserEntity user = entityManager.find(UserEntity.class,1);
       Set<PictureEntity> pictures = user.getPictureEntitySet();
       for (PictureEntity picture : pictures){
           System.out.println(picture.toString());
           System.out.println();
       }
       entityManager.close();
       entityManagerFactory.close();
    }
    @Test
    //关注某个用户
    //要在多端添加一端的值
    public void testConcernUser(){
        ConcernEntity concern = new ConcernEntity();
        UserEntity concernerUser = entityManager.find(UserEntity.class, 1);
        UserEntity concernedUser = entityManager.find(UserEntity.class, 9);
        concern.setConcernerUser(concernerUser);
        concern.setConcernedUser(concernedUser);
        concern.setConcernTime(new Date());
        transaction.begin();
        entityManager.persist(concern);
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
    @Test
    //取消关注某个用户
    //如果一端的一对多fetch设置为eager执行能通过，但是不能删除表中的数据
    public void testCancelConcern(){
        UserEntity concernerUser = entityManager.find(UserEntity.class,1);
        UserEntity concernedUser = entityManager.find(UserEntity.class,10);
        Query query = entityManager.createQuery("from ConcernEntity concernEntity where concernEntity.concernerUser=:concernerUser and " +
                "concernEntity.concernedUser=:concernedUser");
        query.setParameter("concernerUser",concernerUser);
        query.setParameter("concernedUser",concernedUser);
        List<ConcernEntity> concernEntityList = query.getResultList();
        transaction.begin();
        for (ConcernEntity concernEntity : concernEntityList){
//            concernerUser.getConcernedUsers().remove(concernEntity); //设置为EAGER 在一端的哪那里获取多端的集合把要删除的多端的数据先从集合里删除再删除多段数据 但是没有用 还是不能删除
            entityManager.remove(concernEntity);
        }
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
    @Test
    //列出用户的粉丝
    public void testListConcernerUser(){
        UserEntity concernedUser = entityManager.find(UserEntity.class,3);
        Query query = entityManager.createQuery("select concernEntity.concernerUser from ConcernEntity concernEntity where concernEntity.concernedUser=:concernedUser");
        query.setParameter("concernedUser",concernedUser);
        List<UserEntity> concernerUsers = query.getResultList();
        for(UserEntity concerner : concernerUsers){
            System.out.println(concerner.toString());
            System.out.println();
        }
        entityManager.close();
        entityManagerFactory.close();
    }
    @Test
    //列出用户的关注对象
    public void testListConcernedUser(){
        UserEntity concernerUser = entityManager.find(UserEntity.class,6);
        Query query = entityManager.createQuery("select concernEntity.concernedUser from ConcernEntity concernEntity where concernEntity.concernerUser=:concernerUser");
        query.setParameter("concernerUser",concernerUser);
        List<UserEntity> concernedUsers = query.getResultList();
        System.out.println(concernerUser.getName()+"关注的人:");
        for (UserEntity concernedUser : concernedUsers){
            System.out.println(concernedUser.toString());
            System.out.println();
        }
        entityManager.close();
        entityManagerFactory.close();
    }
}
