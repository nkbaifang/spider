package io.ziyi.spider.viu.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseModel<K extends Serializable> implements Serializable {

    abstract public K getId();
    abstract public void setId(K id);

    @Column(name = "create_time", columnDefinition = "timestamp(3) not null default current_timestamp(3)", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @Column(name = "update_time", columnDefinition = "timestamp(3) not null default current_timestamp(3) on update current_timestamp(3)", insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    protected BaseModel() {
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object other) {
        if ( other == null ) {
            return false;
        }
        if ( this == other ) {
            return true;
        }
        if ( other.getClass() != this.getClass() ) {
            return false;
        }
        @SuppressWarnings("unchecked")
        BaseModel<K> o = (BaseModel<K>) other;
        return Objects.equals(o.getId(), getId());
    }

    @Override
    public int hashCode() {
        int hash = getClass().getName().hashCode();
        K id = getId();
        if ( id != null ) {
            hash = hash * 13 + id.hashCode();
        }
        return hash;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public String toKeyString() {
        return getClass().getName() + "-" + getId();
    }
}
