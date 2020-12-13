package edu.zust.se;

import edu.zust.se.entity.PictureEntity;
import edu.zust.se.util.ConcernUtil;
import edu.zust.se.util.DateUtil;
import edu.zust.se.util.PictureUtil;
import edu.zust.se.util.UserUtil;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class UtilTest {
    @Test
    public void testInsertTenUsers(){
        UserUtil.insertTenUsers();
    }
    @Test
    public void testConcernOtherUser(){
        ConcernUtil.concernOtherUser(9,10);
    }
    @Test
    public void testInsertSomePicturesForUser(){
        PictureUtil.insertSomePicturesForUser(9,10,"椅子","家具");
    }
    @Test
    public void testGetDate(){
        Date date = DateUtil.getDate(2010,10,10,22,20,0);
        System.out.println(date);
    }
}
