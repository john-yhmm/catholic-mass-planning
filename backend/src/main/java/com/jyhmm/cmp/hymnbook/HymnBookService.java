package com.jyhmm.cmp.hymnbook;

import com.jyhmm.cmp.common.exception.EntityNotFoundException;
import com.jyhmm.cmp.common.models.PageDTO;
import com.jyhmm.cmp.common.utils.ObjectValidator;
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

    private final ObjectValidator objectValidator;
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
        HymnBook hymnBook = findHymnBookById(id);
        List<CategoryDTO> categoryDTOList = hymnBook.getCategories()
                .stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toList());
        HymnBookDTO hymnBookDTO = new HymnBookDTO(hymnBook);
        hymnBookDTO.setCategoryList(categoryDTOList);
        return hymnBookDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public HymnBook register(HymnBookDTO hymnBookDTO) {
        objectValidator.validate(hymnBookDTO);
        HymnBook hymnBook = new HymnBook();
        hymnBook.setValuesFrom(hymnBookDTO);

        for (CategoryDTO categoryDTO : hymnBookDTO.getCategoryList()) {
            Category category = createNewCategory(hymnBook, categoryDTO);
            hymnBook.getCategories().add(category);
        }
        return hymnBookRepository.save(hymnBook);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(HymnBookDTO hymnBookDTO) {
        objectValidator.validate(hymnBookDTO);
        HymnBook hymnBook = findHymnBookById(hymnBookDTO.getId());
        hymnBook.setValuesFrom(hymnBookDTO);

        for (CategoryDTO categoryDTO : hymnBookDTO.getCategoryList()) {
            if (categoryDTO.getId() == null) {
                Category category = createNewCategory(hymnBook, categoryDTO);
                hymnBook.getCategories().add(category);
            } else {
                Category category = findCategoryById(categoryDTO.getId());
                category.setValuesFrom(categoryDTO);
            }
        }
        hymnBookRepository.save(hymnBook);

        if (hymnBookDTO.getDeleteCategoryIds() != null) {
            categoryRepository.findByIdIn(hymnBookDTO.getDeleteCategoryIds())
                    .ifPresent(categoryRepository::deleteAllInBatch);
        }
    }

    private Category createNewCategory(HymnBook hymnBook, CategoryDTO categoryDTO) {
        objectValidator.validate(categoryDTO);
        Category category = new Category();
        category.setHymnBook(hymnBook);
        category.setValuesFrom(categoryDTO);
        return category;
    }

    public HymnBook findHymnBookById(Long id) {
        return hymnBookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Hymn Book with id (" + id + ") not found."));
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category with id (" + id + ") not found."));
    }
}
