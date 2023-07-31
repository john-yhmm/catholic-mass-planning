package com.jyhmm.cmp.kyriale;

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
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "kyriale")
public class Kyriale extends AuditableEntity {

    @Serial
    private static final long serialVersionUID = -7260021602531866650L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private KyrialeType type;

    @OneToMany(mappedBy = "kyriale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KyrialeDetail> kyrialeDetails = new ArrayList<>();

    public void setValuesFrom(KyrialeDTO dto) {
        this.name = dto.getName();
        this.type = dto.getType();
    }
}
