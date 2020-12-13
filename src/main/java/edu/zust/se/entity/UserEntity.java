package edu.zust.se.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String intro;
    @Column
    private String gender;
    @Column
    private String province;
    @Column
    private String city;
    @Column(name = "regist_time")
    private Date registTime;
    @Column
    private String type;
    @Column
    private String email;
    @Column
    private String mobile;
    @Column(name = "QQ")
    private String qq;
    @Column
    private String status;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "userEntity")
    private Set<PictureEntity> pictureEntitySet;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "concernerUser")
    private Set<ConcernEntity> concernedUsers;//现在是关注者，有多个被关注者
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "concernedUser")
    private Set<ConcernEntity> concernerUsers;//现在是被关注者，有多个关注者


    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", gender='" + gender + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", registTime=" + registTime +
                ", type='" + type + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", qq='" + qq + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
