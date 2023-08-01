package com.jyhmm.cmp.mass;

import com.jyhmm.cmp.hymn.Hymn;
import com.jyhmm.cmp.kyriale.Kyriale;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "mass_detail")
public class MassDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = 7907163722567186685L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mass_id")
    private Mass mass;

    @Column(name = "type")
    private MassDetailType type;

    @Column(name = "serial_no")
    private Integer serialNo;

    @ManyToOne
    @JoinColumn(name = "hymn_id")
    private Hymn hymn;

    @ManyToOne
    @JoinColumn(name = "kyriale_id")
    private Kyriale kyriale;

    public void setValuesFrom(MassDetailDTO dto) {
        this.type = dto.getType();
        this.serialNo = dto.getSerialNo();
    }
}
