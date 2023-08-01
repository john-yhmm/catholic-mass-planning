package com.jyhmm.cmp.mass;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class MassDetailDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 179580958645904952L;

    private Long id;
    private MassDetailType type;
    private Integer serialNo;

    private Long hymnId;
    private String hymnName;
    private Long kyrialeId;
    private String kyrialeName;

    public MassDetailDTO(MassDetail entity) {
        this.id = entity.getId();
        this.type = entity.getType();
        this.serialNo = entity.getSerialNo();

        if (entity.getHymn() != null) {
            this.hymnId = entity.getHymn().getId();
            this.hymnName = entity.getHymn().getName();
        }
        if (entity.getKyriale() != null) {
            this.kyrialeId = entity.getKyriale().getId();
            this.kyrialeName = entity.getKyriale().getName();
        }
    }
}
