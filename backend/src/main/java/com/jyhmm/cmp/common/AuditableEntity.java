package com.jyhmm.cmp.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@MappedSuperclass
public abstract class AuditableEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 8184096293775242201L;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    public <DTO extends AuditableDTO> AuditableEntity(DTO dto) {
        super(dto);
    }

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
