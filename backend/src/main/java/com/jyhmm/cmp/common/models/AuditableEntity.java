package com.jyhmm.cmp.common.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class AuditableEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -441388499895472247L;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @PrePersist
    public void prePersist() {
        this.createdBy = "username";
        this.createdDate = LocalDateTime.now();
        this.updatedBy = "username";
        this.updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedBy = "username";
        this.updatedDate = LocalDateTime.now();
    }
}
