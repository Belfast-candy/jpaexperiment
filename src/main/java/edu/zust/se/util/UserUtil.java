package edu.zust.se.util;

import edu.zust.se.dao.UserDao;
import edu.zust.se.entity.UserEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class UserUtil {
    //插入10个新的用户
    public static void insertTenUsers(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserDao userDao = ac.getBean(UserDao.class,"userDao");
        UserEntity user = null;
        for (int i=0;i<10;i++){
            user = new UserEntity();
            user.setUsername("普通用户"+(i+1));
            user.setPassword("123456");
            user.setName("用户"+(i+1));
            user.setIntro("现在是学生");
            if (i%2==0){
                user.setGender("男");
            }else {
                user.setGender("女");
            }
            user.setProvince("AA省");
            user.setCity("bb市");
            user.setRegistTime(new Date());
            user.setType("学生");
            user.setEmail("yonghu"+(i+1)+"@qq.com");
            user.setMobile("123456789");
            user.setStatus("");
            userDao.insertUser(user);
        }
    }
}
