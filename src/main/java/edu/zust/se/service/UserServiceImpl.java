package edu.zust.se.service;

import edu.zust.se.entity.ConcernEntity;
import edu.zust.se.entity.PictureEntity;
import edu.zust.se.entity.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<UserEntity> getBigV() {
//        Query query = entityManager.createNativeQuery("select user.* from user,concern  where user.id=concerned_id group by concerned_id order by count(concerner_id) desc,user.id asc",UserEntity.class);
        Query query = entityManager.createQuery("select concern.concernedUser from ConcernEntity concern group by concern.concernedUser order by count(concern.concernerUser) desc,concern.concernedUser.id asc");
        List<UserEntity> userEntityList = query.getResultList();
        if (userEntityList.size()>10){
            return userEntityList.subList(0,10);//subList(开始的索引，结束的索引加1)
        }
        return userEntityList;
    }

    @Override
    public List<PictureEntity> getUserPictures(int userId, int pageNo, int pageSize) {
        Query query = entityManager.createQuery("from PictureEntity picture where picture.userEntity.id=:userId");
        query.setParameter("userId",userId);
        int startPosition = (pageNo-1)*pageSize;
        query.setFirstResult(startPosition);
        query.setMaxResults(pageSize);
        List<PictureEntity> pictureEntityList = query.getResultList();
        return pictureEntityList;
    }

    @Override
    public List<PictureEntity> getUserPictures(int userId, Date start_time, Date end_time, int pageNo, int pageSize) {
        int startPosition = (pageNo-1)*pageSize;
        Query query;
        if (start_time!=null && end_time!=null){
            query = entityManager.createQuery("from PictureEntity picture where picture.userEntity.id=:userId and picture.uploadTime between :startTime and :endTime");
            query.setParameter("startTime",start_time);
            query.setParameter("endTime",end_time);
        }else if (start_time == null && end_time!=null){
            query = entityManager.createQuery("from PictureEntity picture where picture.userEntity.id=:userId and picture.uploadTime <= :endTime");
            query.setParameter("endTime",end_time);
        }else if (start_time != null && end_time==null){
            end_time = new Date();
            query = entityManager.createQuery("from PictureEntity picture where picture.userEntity.id=:userId and picture.uploadTime between :startTime and :endTime");
            query.setParameter("startTime",start_time);
            query.setParameter("endTime",end_time);
        }else {
            end_time = new Date();
            query = entityManager.createQuery("from PictureEntity picture where picture.userEntity.id=:userId and picture.uploadTime <= :endTime");
            query.setParameter("endTime",end_time);
        }
        query.setParameter("userId",userId);
        query.setFirstResult(startPosition);
        query.setMaxResults(pageSize);
        List<PictureEntity> pictureEntityList = query.getResultList();
        return pictureEntityList;
    }

    @Override
    public List<UserEntity> getFollows(int userId) {
        Query query = entityManager.createQuery("from ConcernEntity concern where concern.concernerUser.id=:userId");
        query.setParameter("userId",userId);
        List<ConcernEntity> concernEntityList = query.getResultList();
        List<UserEntity> userEntityList = new LinkedList<>();
        for (ConcernEntity concernEntity : concernEntityList){
            userEntityList.add(concernEntity.getConcernedUser());
        }
        return userEntityList;
    }

    @Override
    public List<PictureEntity> getFollowPictures(int userId, int pageNo, int pageSize) {
        int startPosition = (pageNo-1)*pageSize;
        //返回要用PictureEntity的别名picture否则会报错空指针异常
        Query query = entityManager.createQuery("select picture from PictureEntity picture,ConcernEntity concern where picture.userEntity.id = concern.concernedUser.id and concern.concernerUser.id=:userId");
        query.setParameter("userId",userId);
        query.setFirstResult(startPosition);
        query.setMaxResults(pageSize);
        List<PictureEntity> pictureEntityList = query.getResultList();
        return pictureEntityList;
    }

    @Override
    public List<PictureEntity> getFollowPictures(int userId, Date start_time, Date end_time, int pageNo, int pageSize) {
        int startPosition = (pageNo-1)*pageSize;
        Query query;
        if (start_time!=null && end_time!=null){
            query = entityManager.createQuery("select picture from PictureEntity picture,ConcernEntity concern where picture.userEntity.id = concern.concernedUser.id and concern.concernerUser.id=:userId and picture.uploadTime between :startTime and :endTime");
            query.setParameter("startTime",start_time);
            query.setParameter("endTime",end_time);
        }else if (start_time==null && end_time!=null){
            query = entityManager.createQuery("select picture from PictureEntity picture,ConcernEntity concern where picture.userEntity.id = concern.concernedUser.id and concern.concernerUser.id=:userId and picture.uploadTime <= :endTime");
            query.setParameter("endTime",end_time);
        }else if (start_time!=null && end_time==null){
            end_time = new Date();
            query = entityManager.createQuery("select picture from PictureEntity picture,ConcernEntity concern where picture.userEntity.id = concern.concernedUser.id and concern.concernerUser.id=:userId and picture.uploadTime between :startTime and :endTime");
            query.setParameter("startTime",start_time);
            query.setParameter("endTime",end_time);
        }else {
            end_time = new Date();
            query = entityManager.createQuery("select picture from PictureEntity picture,ConcernEntity concern where picture.userEntity.id = concern.concernedUser.id and concern.concernerUser.id=:userId and picture.uploadTime <= :endTime");
            query.setParameter("endTime",end_time);
        }
        query.setParameter("userId",userId);
        query.setFirstResult(startPosition);
        query.setMaxResults(pageSize);
        List<PictureEntity> pictureEntityList = query.getResultList();
        return pictureEntityList;
    }

    @Override
    public List<PictureEntity> getRecentPictures(int pageNo, int pageSize) {
        int startPosition = (pageNo-1)*pageSize;
        Query query = entityManager.createQuery("from PictureEntity picture order by picture.uploadTime desc,picture.id desc ");
        query.setFirstResult(startPosition);
        query.setMaxResults(pageSize);
        List<PictureEntity> pictureEntityList = query.getResultList();
        return pictureEntityList;
    }

    @Override
    public List<UserEntity> findUsersByName(String userName) {
        Query query = entityManager.createQuery("from UserEntity user where user.username like :userName");
        query.setParameter("userName",'%'+userName+'%');
        List<UserEntity> userEntityList = query.getResultList();
        return userEntityList;
    }

    @Override
    public List<PictureEntity> findpictureByName(String pictureName) {
        Query query = entityManager.createQuery("from PictureEntity picture where picture.name like :pictureName");
        query.setParameter("pictureName",'%'+pictureName+'%');
        List<PictureEntity> pictureEntityList = query.getResultList();
        return pictureEntityList;
    }

    @Override
    public List<PictureEntity> findPictureByTag(String tagName) {
        Query query = entityManager.createQuery("from PictureEntity picture where picture.tags = :tagName");
        query.setParameter("tagName",tagName);
        List<PictureEntity> pictureEntityList = query.getResultList();
        return pictureEntityList;
    }
}
