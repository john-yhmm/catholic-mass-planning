package com.jyhmm.cmp.hymnbook;

import com.jyhmm.cmp.common.exception.EntityNotFoundException;
import com.jyhmm.cmp.common.models.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HymnBookService {
    private final HymnBookRepository hymnBookRepository;
    private final CategoryRepository categoryRepository;

    public PageDTO getAll(Pageable pageable) {
        Page<HymnBook> page = hymnBookRepository.findAll(pageable);
        List<HymnBookDTO> hymnBookDTOList = page.getContent()
                .stream()
                .map(HymnBookDTO::new)
                .collect(Collectors.toList());
        return new PageDTO(hymnBookDTOList, page);
    }

    public HymnBookDTO getById(Long id) {
        HymnBook hymnBook = findById(id);
        List<CategoryDTO> categoryDTOList = hymnBook.getCategories()
                .stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());

        HymnBookDTO hymnBookDTO = new HymnBookDTO(hymnBook);
        hymnBookDTO.setCategoryDTOList(categoryDTOList);
        return hymnBookDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public HymnBook register(HymnBookDTO dto) {
        HymnBook hymnBook = new HymnBook();
        hymnBook.setValuesFrom(dto);

        for (CategoryDTO categoryDTO : dto.getCategoryDTOList()) {
            categoryDTO.validate();

            Category category = new Category();
            category.setHymnBook(hymnBook);
            category.setValuesFrom(categoryDTO);

            hymnBook.getCategories().add(category);
        }

        return hymnBookRepository.save(hymnBook);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(HymnBookDTO dto) {
        HymnBook hymnBook = findById(dto.getId());
        hymnBook.setValuesFrom(dto);

        for (CategoryDTO categoryDTO : dto.getCategoryDTOList()) {
            categoryDTO.validate();

            Category category;
            if (categoryDTO.getId() == null) {
                category = new Category();
                category.setHymnBook(hymnBook);

                hymnBook.getCategories().add(category);
            } else {
                category = findCategoryById(categoryDTO.getId());
            }
            category.setValuesFrom(categoryDTO);
        }

        hymnBookRepository.save(hymnBook);

        if (dto.getDeleteCategoryIds() != null)
            categoryRepository.findByIdIn(dto.getDeleteCategoryIds()).ifPresent(categoryRepository::deleteAllInBatch);
    }

    public HymnBook findById(Long id) {
        return hymnBookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hymn Book with id (" + id + ") not found."));
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with id (" + id + ") not found."));
    }
}
