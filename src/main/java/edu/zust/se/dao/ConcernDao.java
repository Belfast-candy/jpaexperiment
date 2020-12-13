package edu.zust.se.dao;

import edu.zust.se.entity.ConcernEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

@Repository
@Transactional
public class ConcernDao {
    @PersistenceContext
    EntityManager entityManager;
    public void insertConcern(ConcernEntity concern){
        if (concern.getConcernTime()==null ){
            concern.setConcernTime(new Date());
        }
        entityManager.persist(concern);
    }
    public ConcernEntity getConcern(Integer id){
        return entityManager.find(ConcernEntity.class,id);
    }
    public void updateConcern(Integer id,ConcernEntity concern){
//        ConcernEntity concernEntity = entityManager.find(ConcernEntity.class,id);
        ConcernEntity concernEntity = getConcern(id);
        concernEntity.setConcernerUser(concern.getConcernerUser());
        concernEntity.setConcernedUser(concern.getConcernedUser());
        concernEntity.setConcernTime(concern.getConcernTime());
    }
    public void deleteConcern(Integer id){
        ConcernEntity concernEntity = getConcern(id);
        entityManager.remove(concernEntity);
    }
}
