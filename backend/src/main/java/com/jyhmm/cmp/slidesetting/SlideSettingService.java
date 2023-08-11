package com.jyhmm.cmp.slidesetting;

import com.jyhmm.cmp.common.exception.EntityNotFoundException;
import com.jyhmm.cmp.common.models.PageDTO;
import com.jyhmm.cmp.common.utils.ObjectValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SlideSettingService {

    private final ObjectValidator objectValidator;
    private final SlideSettingRepository slideSettingRepository;

    public PageDTO getAll(Pageable pageable) {
        Page<SlideSetting> page = slideSettingRepository.findAll(pageable);
        List<SlideSettingDTO> slideSettingDTOList = page.getContent()
                .stream()
                .map(SlideSettingDTO::new)
                .collect(Collectors.toList());
        return new PageDTO(slideSettingDTOList, page);
    }

    public SlideSettingDTO getById(Long id) {
        SlideSetting slideSetting = findSlideSettingById(id);
        return new SlideSettingDTO(slideSetting);
    }

    public SlideSetting register(SlideSettingDTO slideSettingDTO) {
        objectValidator.validate(slideSettingDTO);
        SlideSetting slideSetting = new SlideSetting();
        slideSetting.setValuesFrom(slideSettingDTO);
        return slideSettingRepository.save(slideSetting);
    }

    public void update(SlideSettingDTO slideSettingDTO) {
        objectValidator.validate(slideSettingDTO);
        SlideSetting slideSetting = findSlideSettingById(slideSettingDTO.getId());
        slideSetting.setValuesFrom(slideSettingDTO);
        slideSettingRepository.save(slideSetting);
    }

    public SlideSetting findSlideSettingById(Long id) {
        return slideSettingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Slide Setting with id (" + id + ") not found."));
    }
}
