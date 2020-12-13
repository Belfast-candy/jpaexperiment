package edu.zust.se.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "picture")
public class PictureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private String fname;
    @Column
    private String intro;
    @Column
    private String tags;
    @Column(name = "upload_time")
    private Date uploadTime;
    @Column(name = "click_num")
    private Integer clickNum;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",insertable = true,updatable = true)
    private UserEntity userEntity;

    @Override
    public String toString() {
        return "PictureEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fname='" + fname + '\'' +
                ", intro='" + intro + '\'' +
                ", tags='" + tags + '\'' +
                ", uploadTime=" + uploadTime +
                ", clickNum=" + clickNum +
                '}';
    }
}
