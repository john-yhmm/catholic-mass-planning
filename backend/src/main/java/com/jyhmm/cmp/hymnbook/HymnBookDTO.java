package com.jyhmm.cmp.hymnbook;

import com.jyhmm.cmp.common.models.AuditableDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HymnBookDTO extends AuditableDTO {

    @Serial
    private static final long serialVersionUID = 7300988000083808866L;

    private Long id;
    @NotBlank(message = "Please provide Hymn Book Title")
    private String title;
    private String author;
    private String edition;
    private String publishYear;
    private String description;

    private List<CategoryDTO> categoryList = new ArrayList<>();
    private Long[] deleteCategoryIds;

    public HymnBookDTO(HymnBook entity) {
        super(entity);

        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.edition = entity.getEdition();
        this.publishYear = entity.getPublishYear();
        this.description = entity.getDescription();
    }
}
