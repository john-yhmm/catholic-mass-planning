package com.jyhmm.cmp.slidesetting;

import com.jyhmm.cmp.common.models.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.sl.usermodel.VerticalAlignment;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.Serial;

@Getter
@Setter
@Entity
@Table(name = "slide_setting")
public class SlideSetting extends AuditableEntity {

    @Serial
    private static final long serialVersionUID = 2297635528591196782L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "x")
    private int x;

    @Column(name = "y")
    private int y;

    @Column(name = "width")
    private int width;

    @Column(name = "height")
    private int height;

    @Column(name = "vertical_alignment")
    private VerticalAlignment verticalAlignment = VerticalAlignment.MIDDLE;

    @Column(name = "space_before")
    private double spaceBefore;

    @Column(name = "space_after")
    private double spaceAfter;

    @Column(name = "font_name", nullable = false)
    private String fontName;

    @Column(name = "font_size")
    private double fontSize;

    @Column(name = "text_color")
    private Color textColor = Color.BLACK;

    @Column(name = "is_bold")
    private boolean isBold;

    @Column(name = "is_italic")
    private boolean isItalic;

    @Transient
    private Rectangle offsetAndSize;

    @PostLoad
    public void postLoad() {
        offsetAndSize = new Rectangle(x, y, width, height);
    }

    public void setValuesFrom(SlideSettingDTO dto) {
        this.x = dto.getX();
        this.y = dto.getY();
        this.width = dto.getWidth();
        this.height = dto.getHeight();
        this.verticalAlignment = dto.getVerticalAlignment();
        this.spaceBefore = dto.getSpaceBefore();
        this.spaceAfter = dto.getSpaceAfter();
        this.fontName = dto.getFontName();
        this.fontSize = dto.getFontSize();
        this.textColor = dto.getTextColor();
        this.isBold = dto.isBold();
        this.isItalic = dto.isItalic();
    }
}
