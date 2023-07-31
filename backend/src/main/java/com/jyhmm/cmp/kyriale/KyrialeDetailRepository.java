package com.jyhmm.cmp.kyriale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KyrialeDetailRepository extends JpaRepository<KyrialeDetail, Long> {
    Optional<List<KyrialeDetail>> findByIdIn(Long[] ids);
}
