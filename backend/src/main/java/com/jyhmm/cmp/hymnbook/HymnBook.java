package com.jyhmm.cmp.hymnbook;

import com.jyhmm.cmp.common.models.AuditableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "hymn_book")
public class HymnBook extends AuditableEntity {

    @Serial
    private static final long serialVersionUID = -3004529395195961569L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "edition")
    private String edition;

    @Column(name = "publish_year")
    private String publishYear;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "hymnBook", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Category> categories = new ArrayList<>();

    public void setValuesFrom(HymnBookDTO dto) {
        this.title = dto.getTitle();
        this.author = dto.getAuthor();
        this.edition = dto.getEdition();
        this.publishYear = dto.getPublishYear();
        this.description = dto.getDescription();
    }
}
