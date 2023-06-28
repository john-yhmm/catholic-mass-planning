package com.jyhmm.cmp.hymnbook;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.util.StringUtils.hasText;

public class HymnBookSpecs {
    public static Specification<HymnBook> getFilters(HymnBookDTO params) {
        return (root, query, criteriaBuilder) -> {
            final Collection<Predicate> predicates = new ArrayList<>();

            if (hasText(params.getTitle()))
                predicates.add(criteriaBuilder.equal(root.get(HymnBook_.TITLE), params.getTitle()));

            if (hasText(params.getAuthor()))
                predicates.add(criteriaBuilder.equal(root.get(HymnBook_.AUTHOR), params.getAuthor()));

            if (hasText(params.getEdition()))
                predicates.add(criteriaBuilder.equal(root.get(HymnBook_.EDITION), params.getEdition()));

            if (hasText(params.getPublishYear()))
                predicates.add(criteriaBuilder.equal(root.get(HymnBook_.PUBLISH_YEAR), params.getPublishYear()));

            if (hasText(params.getDescription()))
                predicates.add(criteriaBuilder.equal(root.get(HymnBook_.DESCRIPTION), params.getDescription()));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
