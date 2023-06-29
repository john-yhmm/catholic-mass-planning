package com.jyhmm.cmp.hymnbook;

import com.jyhmm.cmp.common.constants.MsgConst;
import com.jyhmm.cmp.common.exception.InvalidEntityException;
import com.jyhmm.cmp.common.models.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.io.Serial;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO extends BaseDTO {

    @Serial
    private static final long serialVersionUID = 201821820599572885L;

    private String name;
    private Integer serialNo;

    public CategoryDTO(Category entity) {
        super(entity);
        setValuesFrom(entity);
    }

    public void setValuesFrom(Category entity) {
        this.name = entity.getName();
        this.serialNo = entity.getSerialNo();
    }

    public void validate() {
        if (!StringUtils.hasText(this.name)) throw new InvalidEntityException("Category name" + MsgConst.NOT_EMPTY);
    }
}
