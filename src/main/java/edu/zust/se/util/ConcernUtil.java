package edu.zust.se.util;

import edu.zust.se.dao.ConcernDao;
import edu.zust.se.dao.UserDao;
import edu.zust.se.entity.ConcernEntity;
import edu.zust.se.entity.UserEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class ConcernUtil {
    /**
     * 在concern表内插入多个关注的关系，id=id的用户关注 id=id+1，id=id+2的用户，如果是最后的几个用户，通过取余的方式，让最后的几个用户关注最前面的几个用户
     * @param id 开始的id号
     * @param num 进行关注操作的总人数
     */
    public static void concernOtherUser(Integer id,Integer num){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        UserDao userDao = applicationContext.getBean(UserDao.class,"userDao");
        ConcernDao concernDao = applicationContext.getBean(ConcernDao.class,"concernDao");
        UserEntity concernerUser;
        UserEntity concernedUser;
        ConcernEntity concern;
        int concernerId;//关注的人的id
        int concernedId;//被关注的人的id
        for (int i=0;i<num;i++){
            concernerId = id+i;
            concernedId = -1;
            concernerUser = userDao.getUser(concernerId);
            for (int j=1;j<=2;j++){ //j的最大范围num-1
                concern = new ConcernEntity();
                concernedId = concernerId + j;
                if (concernedId>(id+num-1)){
                    int offset = concernedId%(id+num-1);
                    concernedId = id+offset-1;
                }
                concernedUser = userDao.getUser(concernedId);
                concern.setConcernerUser(concernerUser);
                concern.setConcernedUser(concernedUser);
                concern.setConcernTime(new Date());
                concernDao.insertConcern(concern);
            }
        }
    }
}
