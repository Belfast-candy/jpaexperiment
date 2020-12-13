package edu.zust.se.util;

import edu.zust.se.dao.PictureDao;
import edu.zust.se.dao.UserDao;
import edu.zust.se.entity.PictureEntity;
import edu.zust.se.entity.UserEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class PictureUtil {
    /**
     * 给用户插入一组类型相似的图片
     * @param userId 用户id
     * @param num 插入图片的数量
     * @param name 图片的名字
     * @param tag 图片的标签
     */
    public static void insertSomePicturesForUser(Integer userId,Integer num,String name,String tag){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserDao userDao = applicationContext.getBean(UserDao.class,"userDao");
        PictureDao pictureDao = applicationContext.getBean(PictureDao.class,"pictureDao");
        UserEntity user = userDao.getUser(userId);
        PictureEntity picture;
        for (int i=0;i<num;i++){
            picture = new PictureEntity();
            picture.setName(name+(i+1));
            picture.setUserEntity(user);
            picture.setIntro("这是"+name+(i+1));
            picture.setTags(tag);
            picture.setUploadTime(new Date());
            picture.setClickNum(0);
            pictureDao.insertPicture(picture);
        }
    }
}
