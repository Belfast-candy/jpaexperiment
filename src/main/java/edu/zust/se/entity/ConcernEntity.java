package edu.zust.se.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "concern")
public class ConcernEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(name = "concern_time")
    private Date concernTime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "concerner_id",insertable = true,updatable = true)
    private UserEntity concernerUser;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "concerned_id",insertable = true,updatable = true)
    private UserEntity concernedUser;

    @Override
    public String toString() {
        return "ConcernEntity{" +
                "id=" + id +
                ", concernTime=" + concernTime +
                ", concerner_id=" + concernerUser.getId() +
                ", concerned_id=" + concernedUser.getId() +
                '}';
    }

//    @Override
//    public String toString() {
//        return "ConcernEntity{" +
//                "id=" + id +
//                ", concernTime=" + concernTime +
//                ", concernerUser=" + concernerUser +
//                ", concernedUser=" + concernedUser +
//                '}';
//    }
}
