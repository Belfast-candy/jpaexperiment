package edu.zust.se.dao;

import edu.zust.se.entity.PictureEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Repository
@Transactional
public class PictureDao {
    @PersistenceContext
    EntityManager entityManager;
    public void insertPicture(PictureEntity picture){
        if (picture.getClickNum()==null){
            picture.setClickNum(0);
        }
        if (picture.getUploadTime()==null){
            picture.setUploadTime(new Date());
        }
        entityManager.persist(picture);
    }
    public PictureEntity getPicture(Integer id){
        return entityManager.find(PictureEntity.class,id);
    }
    public void updatePicture(Integer id,PictureEntity picture){
//        PictureEntity pictureEntity = entityManager.find(PictureEntity.class,id);
        PictureEntity pictureEntity = getPicture(id);
        pictureEntity.setName(picture.getName());
        pictureEntity.setFname(picture.getFname());
        pictureEntity.setUserEntity(picture.getUserEntity());
        pictureEntity.setIntro(picture.getIntro());
        pictureEntity.setTags(picture.getTags());
        pictureEntity.setUploadTime(picture.getUploadTime());
        pictureEntity.setClickNum(picture.getClickNum());
    }
    public void deletePicture(Integer id){
        PictureEntity picture = getPicture(id);
        entityManager.remove(picture);
    }
}
