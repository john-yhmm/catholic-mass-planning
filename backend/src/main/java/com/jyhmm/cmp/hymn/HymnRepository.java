package com.jyhmm.cmp.hymn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HymnRepository extends JpaRepository<Hymn, Long> {
}
