package com.jyhmm.cmp.mass;

import com.jyhmm.cmp.common.models.AuditableEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "mass")
public class Mass extends AuditableEntity {

    @Serial
    private static final long serialVersionUID = 3842702975932418668L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "sub_title")
    private String subTitle;

    @Column(name = "mass_date")
    private LocalDate massDate;

    @Column(name = "mass_time")
    private LocalTime massTime;

    @OneToMany(mappedBy = "mass", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MassDetail> massDetails = new ArrayList<>();

    public void setValuesFrom(MassDTO dto) {
        this.title = dto.getTitle();
        this.subTitle = dto.getSubTitle();
        this.massDate = dto.getMassDate();
        this.massTime = dto.getMassTime();
    }
}
