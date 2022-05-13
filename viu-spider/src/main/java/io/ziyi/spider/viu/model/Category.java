package io.ziyi.spider.viu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends BaseModel<Long> {

    private static final long serialVersionUID = 0x76bb3075d6432a23L;

    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
