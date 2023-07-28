package com.jyhmm.cmp.hymn;

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
public class HymnDTO extends AuditableDTO {

    @Serial
    private static final long serialVersionUID = 2664764377488417751L;

    private Long id;
    private Integer hymnNo;
    private Integer pageNo;
    @NotBlank(message = "Please provide Hymn Name")
    private String name;
    private boolean isStartWithChorus;

    private Long hymnBookId;
    private String hymnBookTitle;
    private Long categoryId;
    private String categoryName;

    private List<HymnDetailDTO> hymnDetailList = new ArrayList<>();
    private Long[] deleteHymnDetailIds;

    public HymnDTO(Hymn entity) {
        super(entity);

        this.id = entity.getId();
        this.hymnNo = entity.getHymnNo();
        this.pageNo = entity.getPageNo();
        this.name = entity.getName();
        this.isStartWithChorus = entity.isStartWithChorus();

        if (entity.getHymnBook() != null) {
            this.hymnBookId = entity.getHymnBook().getId();
            this.hymnBookTitle = entity.getHymnBook().getTitle();
        }
        if (entity.getCategory() != null) {
            this.categoryId = entity.getCategory().getId();
            this.categoryName = entity.getCategory().getName();
        }
    }
}
