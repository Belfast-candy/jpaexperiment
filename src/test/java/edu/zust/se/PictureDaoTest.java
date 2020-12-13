package edu.zust.se;

import edu.zust.se.dao.PictureDao;
import edu.zust.se.dao.UserDao;
import edu.zust.se.entity.PictureEntity;
import edu.zust.se.entity.UserEntity;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PictureDaoTest {
    ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    PictureDao pictureDao = ac.getBean(PictureDao.class,"pictureDao");
    @Test
    public void testInsertPicture(){
        PictureEntity picture = new PictureEntity();
        picture.setName("水杯");
        picture.setIntro("这是一个水杯");
        picture.setTags("生活用品");
        pictureDao.insertPicture(picture);
    }
    @Test
    public void testGetPicture(){
        System.out.println(pictureDao.getPicture(1).toString());
    }
    @Test
    public void testUpdatePicture(){
        PictureEntity picture = pictureDao.getPicture(16);
        picture.setClickNum(1);
        UserDao userDao = ac.getBean(UserDao.class,"userDao");
        UserEntity user = userDao.getUser(1);
        picture.setUserEntity(user);
        pictureDao.updatePicture(picture.getId(),picture);
    }
    @Test
    public void testDeletePicture(){
        pictureDao.deletePicture(16);
    }
}
