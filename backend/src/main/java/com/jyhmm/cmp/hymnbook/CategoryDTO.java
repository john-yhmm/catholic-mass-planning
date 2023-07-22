package com.jyhmm.cmp.hymnbook;

import com.jyhmm.cmp.common.constants.MsgConst;
import com.jyhmm.cmp.common.exception.InvalidDTOException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 201821820599572885L;

    private Long id;
    private String name;
    private Integer serialNo;

    public CategoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.serialNo = entity.getSerialNo();
    }

    public void validate() {
        if (!StringUtils.hasText(this.name)) throw new InvalidDTOException("Category name" + MsgConst.NOT_EMPTY);
    }
}
