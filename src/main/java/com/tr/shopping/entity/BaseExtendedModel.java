package com.tr.shopping.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseExtendedModel extends BaseModel {


    private String createdBy;
    private String UpdatedBy;
    private String deletedBy;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date DeletedAt;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedAt;


}
