package com.jyhmm.cmp.kyriale;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class KyrialeDetailDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 2827625317864022185L;

    private Long id;
    @NotBlank(message = "Please provide text")
    private String text;
    private Integer serialNo;

    public KyrialeDetailDTO(KyrialeDetail entity) {
        this.id = entity.getId();
        this.text = entity.getText();
        this.serialNo = entity.getSerialNo();
    }
}
