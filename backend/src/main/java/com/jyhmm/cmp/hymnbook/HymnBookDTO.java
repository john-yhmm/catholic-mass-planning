package com.jyhmm.cmp.hymnbook;

import com.jyhmm.cmp.common.models.AuditableDTO;
import com.jyhmm.cmp.common.constants.MsgConst;
import com.jyhmm.cmp.common.exception.InvalidEntityException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.Serial;

@Getter
@Setter
@NoArgsConstructor
public class HymnBookDTO extends AuditableDTO {

    @Serial
    private static final long serialVersionUID = -3110490187237013705L;

    private String title;
    private String author;
    private String edition;
    private String publishYear;
    private String description;

    public HymnBookDTO(HymnBook entity) {
        super(entity);
        setValuesFrom(entity);
    }

    public void setValuesFrom(HymnBook entity) {
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.edition = entity.getEdition();
        this.publishYear = entity.getPublishYear();
        this.description = entity.getDescription();
    }

    public void validate() {
        if (!StringUtils.hasText(this.title)) throw new InvalidEntityException("Title" + MsgConst.NOT_EMPTY);
    }
}
