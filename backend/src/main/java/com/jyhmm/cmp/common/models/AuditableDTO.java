package com.jyhmm.cmp.common.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public abstract class AuditableDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5270834670961414430L;

    private String createdBy;
    private LocalDateTime createdDate;
    private String updatedBy;
    private LocalDateTime updatedDate;

    public <T extends AuditableEntity> AuditableDTO(T entity) {
        this.createdBy = entity.getCreatedBy();
        this.createdDate = entity.getCreatedDate();
        this.updatedBy = entity.getUpdatedBy();
        this.updatedDate = entity.getUpdatedDate();
    }
}
