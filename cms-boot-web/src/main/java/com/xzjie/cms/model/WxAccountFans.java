package com.xzjie.cms.model;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Where(clause = "state = 1")
@Table(name = "wx_account_fans")
@SQLDelete(sql = "update wx_account_fans set state = 0 where id = ?")
public class WxAccountFans extends BaseEntity<WxAccountFans> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String openId;

    private Integer subscribe;

    private String nickName;

    private Integer sex;

    private String city;

    private String country;

    private String province;

    private String language;

    private String avatar;

    private Integer subscribeTime;

    private String unionId;

    private String remark;

    private String groupId;

    @Column(name = "tag_ids")
    private String tagIds;

    private String subscribeScene;

    private String qrScene;

    private String qrSceneStr;

    private Long userId;

    private LocalDateTime createDate;

    private Integer state;

    @OneToMany
    @JoinColumn(name = "fans_id", updatable = false, insertable = false)
    private List<WxFansTag> fansTags;

    @Override
    public void copy(WxAccountFans obj) {
        copyProperties(obj, this);
    }
}
