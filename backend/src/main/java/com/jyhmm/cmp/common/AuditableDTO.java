package com.jyhmm.cmp.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public abstract class AuditableDTO extends BaseDTO {
    @Serial
    private static final long serialVersionUID = 4547565494397472386L;

    private String createdBy;
    private LocalDateTime createdDate;
    private String updatedBy;
    private LocalDateTime updatedDate;

    public <Entity extends AuditableEntity> AuditableDTO(Entity entity) {
        super(entity);
        setAuditValuesFrom(entity);
    }

    public <Entity extends AuditableEntity> void setAuditValuesFrom(Entity entity) {
        this.createdBy = entity.getCreatedBy();
        this.createdDate = entity.getCreatedDate();
        this.updatedBy = entity.getUpdatedBy();
        this.updatedDate = entity.getUpdatedDate();
    }
}
