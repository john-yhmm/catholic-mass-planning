package com.jyhmm.cmp.hymn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HymnDetailRepository extends JpaRepository<HymnDetail, Long> {
    Optional<List<HymnDetail>> findByIdIn(Long[] ids);
}
