package com.jyhmm.cmp.kyriale;

import com.jyhmm.cmp.common.models.AuditableDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class KyrialeDTO extends AuditableDTO {

    @Serial
    private static final long serialVersionUID = -5877875008605828724L;

    private Long id;
    @NotBlank(message = "Please provide Name")
    private String name;
    @NotNull(message = "Please provide Type")
    private KyrialeType type;

    private List<KyrialeDetailDTO> kyrialeDetailList = new ArrayList<>();
    private Long[] deleteKyrialeDetailIds;

    public KyrialeDTO(Kyriale entity) {
        super(entity);

        this.id = entity.getId();
        this.name = entity.getName();
        this.type = entity.getType();
    }
}
