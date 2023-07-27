package com.jyhmm.cmp.hymnbook;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -3402951380272167131L;

    private Long id;
    @NotBlank(message = "Please provide Category Name")
    private String name;
    private Integer serialNo;

    public CategoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.serialNo = entity.getSerialNo();
    }
}
