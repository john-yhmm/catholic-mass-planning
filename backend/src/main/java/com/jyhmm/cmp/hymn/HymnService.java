package com.jyhmm.cmp.hymn;

import com.jyhmm.cmp.common.exception.EntityNotFoundException;
import com.jyhmm.cmp.common.models.PageDTO;
import com.jyhmm.cmp.common.utils.ObjectValidator;
import com.jyhmm.cmp.hymnbook.Category;
import com.jyhmm.cmp.hymnbook.HymnBook;
import com.jyhmm.cmp.hymnbook.HymnBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HymnService {

    private final ObjectValidator objectValidator;
    private final HymnRepository hymnRepository;
    private final HymnDetailRepository hymnDetailRepository;
    private final HymnBookService hymnBookService;

    public PageDTO getAll(Pageable pageable) {
        Page<Hymn> page = hymnRepository.findAll(pageable);
        List<HymnDTO> hymnDTOList = page.getContent().stream().map(HymnDTO::new).collect(Collectors.toList());
        return new PageDTO(hymnDTOList, page);
    }

    public HymnDTO getById(Long id) {
        Hymn hymn = findHymnById(id);
        List<HymnDetailDTO> hymnDetailDTOList = hymn.getHymnDetails()
                .stream()
                .map(HymnDetailDTO::new)
                .collect(Collectors.toList());
        HymnDTO hymnDTO = new HymnDTO(hymn);
        hymnDTO.setHymnDetailList(hymnDetailDTOList);
        return hymnDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public Hymn register(HymnDTO hymnDTO) {
        objectValidator.validate(hymnDTO);
        HymnBook hymnBook = hymnBookService.findHymnBookById(hymnDTO.getHymnBookId());
        Category category = hymnBookService.findCategoryById(hymnDTO.getCategoryId());

        Hymn hymn = new Hymn();
        hymn.setValuesFrom(hymnDTO);
        hymn.setHymnBook(hymnBook);
        hymn.setCategory(category);

        for (HymnDetailDTO hymnDetailDTO : hymnDTO.getHymnDetailList()) {
            HymnDetail hymnDetail = createNewHymnDetail(hymn, hymnDetailDTO);
            hymn.getHymnDetails().add(hymnDetail);
        }
        return hymnRepository.save(hymn);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(HymnDTO hymnDTO) {
        objectValidator.validate(hymnDTO);
        HymnBook hymnBook = hymnBookService.findHymnBookById(hymnDTO.getHymnBookId());
        Category category = hymnBookService.findCategoryById(hymnDTO.getCategoryId());

        Hymn hymn = findHymnById(hymnDTO.getId());
        hymn.setValuesFrom(hymnDTO);
        hymn.setHymnBook(hymnBook);
        hymn.setCategory(category);

        for (HymnDetailDTO hymnDetailDTO : hymnDTO.getHymnDetailList()) {
            if (hymnDetailDTO.getId() == null) {
                HymnDetail hymnDetail = createNewHymnDetail(hymn, hymnDetailDTO);
                hymn.getHymnDetails().add(hymnDetail);
            } else {
                HymnDetail hymnDetail = findHymnDetailById(hymnDetailDTO.getId());
                hymnDetail.setValuesFrom(hymnDetailDTO);
            }
        }
        hymnRepository.save(hymn);

        if (hymnDTO.getDeleteHymnDetailIds() != null) {
            hymnDetailRepository.findByIdIn(hymnDTO.getDeleteHymnDetailIds())
                    .ifPresent(hymnDetailRepository::deleteAllInBatch);
        }
    }

    private HymnDetail createNewHymnDetail(Hymn hymn, HymnDetailDTO hymnDetailDTO) {
        objectValidator.validate(hymnDetailDTO);
        HymnDetail hymnDetail = new HymnDetail();
        hymnDetail.setHymn(hymn);
        hymnDetail.setValuesFrom(hymnDetailDTO);
        return hymnDetail;
    }

    public Hymn findHymnById(Long id) {
        return hymnRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hymn with id (" + id + ") not found."));
    }

    public HymnDetail findHymnDetailById(Long id) {
        return hymnDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hymn Detail with id (" + id + ") not found."));
    }
}
