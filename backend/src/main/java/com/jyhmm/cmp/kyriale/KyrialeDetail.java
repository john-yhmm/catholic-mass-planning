package com.jyhmm.cmp.kyriale;

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
@Table(name = "kyriale_detail")
public class KyrialeDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = 5948778991718570542L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kyriale_id")
    private Kyriale kyriale;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "serial_no")
    private Integer serialNo;

    public void setValuesFrom(KyrialeDetailDTO dto) {
        this.text = dto.getText();
        this.serialNo = dto.getSerialNo();
    }
}
