package com.jyhmm.cmp.mass;

import com.jyhmm.cmp.common.models.AuditableDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MassDTO extends AuditableDTO {

    @Serial
    private static final long serialVersionUID = 4699572503651555914L;

    private Long id;
    @NotBlank(message = "Please provide Title")
    private String title;
    private String subTitle;
    private LocalDate massDate;
    private LocalTime massTime;

    private List<MassDetailDTO> massDetailList = new ArrayList<>();
    private Long[] deleteMassDetailIds;

    public MassDTO(Mass entity) {
        super(entity);

        this.id = entity.getId();
        this.title = entity.getTitle();
        this.subTitle = entity.getSubTitle();
        this.massDate = entity.getMassDate();
        this.massTime = entity.getMassTime();
    }
}
