package com.jyhmm.cmp.hymnbook;

import com.jyhmm.cmp.common.exception.NotFoundException;
import com.jyhmm.cmp.common.models.PageDTO;
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
    private final CategoryRepository categoryRepository;

    public PageDTO getAllByFilter(HymnBookDTO params, Pageable pageable) {
        Specification<HymnBook> specification = HymnBookSpecs.getFilters(params);
        Page<HymnBook> page = hymnBookRepository.findAll(specification, pageable);
        List<HymnBook> hymnBooks = page.getContent();
        List<HymnBookDTO> hymnBookDTOList = convertToDTOList(hymnBooks);
        return new PageDTO(hymnBookDTOList, page);
    }

    private List<HymnBookDTO> convertToDTOList(List<HymnBook> hymnBooks) {
        return hymnBooks.stream().map(HymnBookDTO::new).collect(Collectors.toList());
    }

    public HymnBookDTO getById(Long id) {
        HymnBook hymnBook = findById(id);
        HymnBookDTO hymnBookDTO = new HymnBookDTO(hymnBook);
        hymnBookDTO.setCategoryDTOList(convertToCategoryDTOList(hymnBook.getCategories()));
        return hymnBookDTO;
    }

    public HymnBook findById(Long id) {
        return hymnBookRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public List<CategoryDTO> convertToCategoryDTOList(List<Category> categories) {
        return categories.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public HymnBook register(HymnBookDTO dto) {
        HymnBook hymnBook = new HymnBook();
        hymnBook.setValuesFrom(dto);

        List<Category> categories = prepareCategoryList(dto.getCategoryDTOList(), hymnBook);
        hymnBook.getCategories().addAll(categories);

        return hymnBookRepository.save(hymnBook);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(HymnBookDTO dto) {
        HymnBook hymnBook = findById(dto.getId());
        hymnBook.setValuesFrom(dto);

        List<Category> categories = prepareCategoryList(dto.getCategoryDTOList(), hymnBook);
        for (Category c : categories) if (c.getId() == null) hymnBook.getCategories().add(c);

        hymnBookRepository.save(hymnBook);
        deleteCategories(dto.getDeleteCategoryIds());
    }

    private List<Category> prepareCategoryList(List<CategoryDTO> categoryDTOList, HymnBook hymnBook) {
        return categoryDTOList.stream().map(cDTO -> prepareCategory(cDTO, hymnBook)).collect(Collectors.toList());
    }

    private Category prepareCategory(CategoryDTO categoryDTO, HymnBook hymnBook) {
        categoryDTO.validate();

        Category category;
        if (categoryDTO.getId() == null) category = new Category();
        else category = findCategoryById(categoryDTO.getId());

        category.setHymnBook(hymnBook);
        category.setValuesFrom(categoryDTO);
        return category;
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    private void deleteCategories(Long[] deleteCategoryIds) {
        if (deleteCategoryIds == null) return;

        Optional<List<Category>> categoryListOpt = categoryRepository.findByIdIn(deleteCategoryIds);
        categoryListOpt.ifPresent(categoryRepository::deleteAllInBatch);
    }
}
