package com.jyhmm.cmp.hymnbook;

import com.jyhmm.cmp.common.models.PageDTO;
import com.jyhmm.cmp.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HymnBookService {
    private final HymnBookRepository hymnBookRepository;

    public PageDTO getAllByFilter(HymnBookDTO params, Pageable pageable) {
        Specification<HymnBook> specification = HymnBookSpecs.getFilters(params);
        Page<HymnBook> page = hymnBookRepository.findAll(specification, pageable);
        List<HymnBook> hymnBooks = page.getContent();
        List<HymnBookDTO> hymnBookDTOList = hymnBooks.stream().map(HymnBookDTO::new).collect(Collectors.toList());
        return new PageDTO(hymnBookDTOList, page);
    }

    @Transactional(rollbackFor = Exception.class)
    public void register(HymnBookDTO dto) {
        HymnBook hymnBook = new HymnBook();
        hymnBook.setValuesFrom(dto);
        hymnBookRepository.save(hymnBook);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(HymnBookDTO dto) {
        HymnBook hymnBook = findByIdOrThrow(dto.getId());
        hymnBook.setValuesFrom(dto);
        hymnBookRepository.save(hymnBook);
    }

    public HymnBookDTO getById(Long id) {
        HymnBook entity = findByIdOrThrow(id);
        return new HymnBookDTO(entity);
    }

    public HymnBook findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(NotFoundException::new);
    }

    public Optional<HymnBook> findById(Long id) {
        return hymnBookRepository.findById(id);
    }
}
