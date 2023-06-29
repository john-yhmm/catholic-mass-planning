package com.jyhmm.cmp.hymnbook;

import com.jyhmm.cmp.common.models.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 115468282187437277L;

    @ManyToOne
    @JoinColumn(name = "hymn_book_id")
    private HymnBook hymnBook;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "serial_no")
    private Integer serialNo;

    public void setValuesFrom(CategoryDTO dto) {
        this.name = dto.getName();
        this.serialNo = dto.getSerialNo();
    }
}
