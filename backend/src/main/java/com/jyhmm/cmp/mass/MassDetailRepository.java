package com.jyhmm.cmp.mass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MassDetailRepository extends JpaRepository<MassDetail, Long> {
    Optional<List<MassDetail>> findByIdIn(Long[] ids);
}
