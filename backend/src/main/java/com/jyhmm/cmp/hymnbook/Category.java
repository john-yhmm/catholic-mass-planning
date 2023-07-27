package com.jyhmm.cmp.hymnbook;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = -4482338004923430505L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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
