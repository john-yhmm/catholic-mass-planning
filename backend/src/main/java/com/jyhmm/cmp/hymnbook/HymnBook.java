package com.jyhmm.cmp.hymnbook;

import com.jyhmm.cmp.common.models.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
@Entity
@Table(name = "hymn_book")
public class HymnBook extends AuditableEntity {

    @Serial
    private static final long serialVersionUID = -8466728236079536136L;

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

    public void setValuesFrom(HymnBookDTO dto) {
        this.title = dto.getTitle();
        this.author = dto.getAuthor();
        this.edition = dto.getEdition();
        this.publishYear = dto.getPublishYear();
        this.description = dto.getDescription();
    }
}
