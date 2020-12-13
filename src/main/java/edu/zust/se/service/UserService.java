package edu.zust.se.service;

import edu.zust.se.entity.PictureEntity;
import edu.zust.se.entity.UserEntity;

import java.util.Date;
import java.util.List;

public interface UserService {
    /**
     * 1、大v排行榜，粉丝最多的10个人
     * @return 用户实体类对象的集合
     */
    List<UserEntity> getBigV();

    /**
     * 2、分页获取指定用户的照片
     * @param userId 用户id
     * @param pageNo 页号
     * @param pageSize 每页的条数
     * @return 图片实体类对象的集合
     */
    List<PictureEntity> getUserPictures(int userId,int pageNo,int pageSize);

    /**
     * 3、分页获取指定用户指定时间段内发布的照片
     * @param userId 用户id
     * @param start_time 起始时间，start_time为空时表示开始时间不限
     * @param end_time 结束时间，end_time为空表示到当前时间
     * @param pageNo 页号
     * @param pageSize 每页显示的行数
     * @return 图片实体类对象的集合
     */
    List<PictureEntity> getUserPictures(int userId, Date start_time,Date end_time,int pageNo,int pageSize);

    /**
     * 4、获取指定用户关注的用户
     * @param userId 关注者用户的id
     * @return 被关注的用户实体类对象集合
     */
    List<UserEntity> getFollows(int userId);

    /**
     * 5、分页获取指定用户关注的用户的照片
     * @param userId 用户id
     * @param pageNo 页号
     * @param pageSize 每页显式的行数
     * @return 用户关注的照片实体类对象的集合
     */
    List<PictureEntity> getFollowPictures(int userId,int pageNo,int pageSize);

    /**
     * 6、分页获取指定用户关注的用户在指定时间段的照片
     * @param userId 关注者用户的id
     * @param start_time 开始时间
     * @param end_time 结束时间
     * @param pageNo 页号
     * @param pageSize 每页显示的行数
     * @return 被关注者指定时间内的照片实体类对象的集合
     */
    List<PictureEntity> getFollowPictures(int userId,Date start_time,Date end_time,int pageNo,int pageSize);

    /**
     * 7、分页获取系统中最新上传的照片
     * @param pageNo 页号
     * @param pageSize 每页显示的行数
     * @return 最新上传的图片实体类对象的集合
     */
    List<PictureEntity> getRecentPictures(int pageNo,int pageSize);

    /**
     * 8、根据用户名模糊查询用户
     * @param userName 用户名
     * @return 用户实体类对象的集合
     */
    List<UserEntity> findUsersByName(String userName);

    /**
     * 9、根据照片名模糊查询照片信息
     * @param pictureName 照片名
     * @return 照片实体类对象的集合
     */
    List<PictureEntity> findpictureByName(String pictureName);

    /**
     * 10、根据照片的标签信息查询照片
     * @param tagName 标签名
     * @return 图片实体类对象的集合
     */
    List<PictureEntity> findPictureByTag(String tagName);
}
