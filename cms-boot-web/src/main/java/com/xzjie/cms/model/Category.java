package com.xzjie.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Where(clause = "state = 1")
@Table(name = "cms_category")
@SQLDelete(sql = "update cms_category set state = 0 where id = ?")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Category extends BaseEntity<Category> {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "p_id")
    private Long pid;

    private String categoryName;

    private String image;

    private String url;

    private String target;

    private String keywords;

    private String description;

    private Integer sort;

    private Integer state;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Integer showState;

    @Transient
    private String template;

    public String getLabel() {
        return categoryName;
    }

    //    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "pId", insertable = false, updatable = false)
//    private Category parent;
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.EAGER)
    @OneToMany
    @JoinColumn(name = "p_id", insertable = false, updatable = false)
//    @Where(clause = "show_state = 1")
    @OrderBy("sort desc")
    private List<Category> children = new ArrayList<>();

    @Override
    public void copy(Category category) {
        BeanUtils.copyProperties(category, this, getIgnoreProperty(category));
    }
}
