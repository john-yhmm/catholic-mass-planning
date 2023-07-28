package com.jyhmm.cmp.hymn;

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
@Table(name = "hymn_detail")
public class HymnDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = -8463363086021974874L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hymn_id")
    private Hymn hymn;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "verse_no")
    private Integer verseNo;

    @Column(name = "serial_no")
    private Integer serialNo;

    @Column(name = "is_chorus")
    private boolean isChorus;

    public void setValuesFrom(HymnDetailDTO dto) {
        this.text = dto.getText();
        this.verseNo = dto.getVerseNo();
        this.serialNo = dto.getSerialNo();
        this.isChorus = dto.isChorus();
    }
}
