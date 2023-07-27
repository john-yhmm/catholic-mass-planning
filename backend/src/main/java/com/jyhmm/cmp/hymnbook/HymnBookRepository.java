package com.jyhmm.cmp.hymnbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HymnBookRepository extends JpaRepository<HymnBook, Long> {
}
