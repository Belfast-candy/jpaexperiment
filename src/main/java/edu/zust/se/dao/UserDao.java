package edu.zust.se.dao;

import edu.zust.se.entity.PictureEntity;
import edu.zust.se.entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

@Repository
@Transactional
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    public void insertUser(UserEntity user){
        entityManager.persist(user);
    }
    public UserEntity getUser(Integer id){
        return entityManager.find(UserEntity.class,id);
    }
    public void updateUser(Integer id,UserEntity user){
        UserEntity userEntity = entityManager.find(UserEntity.class,id);
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());
        userEntity.setName(user.getName());
        userEntity.setIntro(user.getIntro());
        userEntity.setGender(user.getGender());
        userEntity.setProvince(user.getProvince());
        userEntity.setCity(user.getCity());
        userEntity.setRegistTime(user.getRegistTime());
        userEntity.setType(user.getType());
        userEntity.setEmail(user.getEmail());
        userEntity.setMobile(user.getMobile());
        userEntity.setQq(user.getQq());
        userEntity.setStatus(user.getStatus());
    }
    public void deleteUser(Integer id){
        UserEntity user = entityManager.find(UserEntity.class,id);
        entityManager.remove(user);
    }
}
