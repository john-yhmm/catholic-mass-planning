package com.jyhmm.cmp.hymn;

import com.jyhmm.cmp.common.models.AuditableEntity;
import com.jyhmm.cmp.hymnbook.Category;
import com.jyhmm.cmp.hymnbook.HymnBook;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "hymn")
public class Hymn extends AuditableEntity {

    @Serial
    private static final long serialVersionUID = -5594537909688310186L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hymn_no")
    private Integer hymnNo;

    @Column(name = "page_no")
    private Integer pageNo;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_start_with_chorus")
    private boolean isStartWithChorus;

    @ManyToOne
    @JoinColumn(name = "hymn_book_id")
    private HymnBook hymnBook;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "hymn", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HymnDetail> hymnDetails = new ArrayList<>();

    public void setValuesFrom(HymnDTO dto) {
        this.hymnNo = dto.getHymnNo();
        this.pageNo = dto.getPageNo();
        this.name = dto.getName();
        this.isStartWithChorus = dto.isStartWithChorus();
    }
}
