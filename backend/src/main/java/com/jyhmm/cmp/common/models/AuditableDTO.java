package com.jyhmm.cmp.common.models;

import com.jyhmm.cmp.common.utils.DateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public abstract class AuditableDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 4547565494397472386L;

    private String createdBy;
    private String createdDate;
    private String updatedBy;
    private String updatedDate;

    public <E extends AuditableEntity> AuditableDTO(E entity) {
        this.createdBy = entity.getCreatedBy();
        this.createdDate = DateUtils.formatDateTime(entity.getCreatedDate());
        this.updatedBy = entity.getUpdatedBy();
        this.updatedDate = DateUtils.formatDateTime(entity.getUpdatedDate());
    }
}
