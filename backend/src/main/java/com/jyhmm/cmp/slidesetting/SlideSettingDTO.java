package com.jyhmm.cmp.slidesetting;

import com.jyhmm.cmp.common.models.AuditableDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.sl.usermodel.VerticalAlignment;

import java.awt.Color;
import java.io.Serial;

@Getter
@Setter
@NoArgsConstructor
public class SlideSettingDTO extends AuditableDTO {

    @Serial
    private static final long serialVersionUID = 4408090216704902186L;

    private Long id;
    private int x;
    private int y;
    private int width;
    private int height;

    private VerticalAlignment verticalAlignment = VerticalAlignment.MIDDLE;
    private double spaceBefore;
    private double spaceAfter;

    @NotBlank(message = "Please provide Font Name")
    private String fontName;
    private double fontSize;
    private Color textColor = Color.BLACK;
    private boolean isBold;
    private boolean isItalic;

    public SlideSettingDTO(SlideSetting entity) {
        super(entity);

        this.id = entity.getId();
        this.x = entity.getX();
        this.y = entity.getY();
        this.width = entity.getWidth();
        this.height = entity.getHeight();
        this.verticalAlignment = entity.getVerticalAlignment();
        this.spaceBefore = entity.getSpaceBefore();
        this.spaceAfter = entity.getSpaceAfter();
        this.fontName = entity.getFontName();
        this.fontSize = entity.getFontSize();
        this.textColor = entity.getTextColor();
        this.isBold = entity.isBold();
        this.isItalic = entity.isItalic();
    }
}
