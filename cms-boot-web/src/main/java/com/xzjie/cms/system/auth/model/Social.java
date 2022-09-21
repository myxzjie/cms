package com.xzjie.cms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.zhyd.oauth.enums.AuthUserGender;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * @author Vito
 * @since 2022/6/13 1:01 上午
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sys_social")
public class Social extends BaseEntity<Social>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String uuid;
    private String username;
    private String nickname;
    private String avatar;
    private String blog;
    private String company;
    private String location;
    private String email;
    private String remark;
//    @Type(type = "char")
    private String gender;
    private String source;


    @Override
    public void copy(Social obj) {
        this.copyProperties(obj, this);
    }
}
