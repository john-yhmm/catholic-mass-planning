package com.jyhmm.cmp.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = -884875519797934783L;

    private Long id;

    public <Entity extends BaseEntity> BaseDTO(Entity entity) {
        this.id = entity.getId();
    }
}
