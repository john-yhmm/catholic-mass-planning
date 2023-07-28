package com.jyhmm.cmp.hymn;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class HymnDetailDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 5345307170526513565L;

    private Long id;
    @NotBlank(message = "Please provide Hymn Text")
    private String text;
    private Integer verseNo;
    private Integer serialNo;
    private boolean isChorus;

    public HymnDetailDTO(HymnDetail entity) {
        this.id = entity.getId();
        this.text = entity.getText();
        this.verseNo = entity.getVerseNo();
        this.serialNo = entity.getSerialNo();
        this.isChorus = entity.isChorus();
    }
}
