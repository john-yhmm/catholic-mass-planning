package com.jyhmm.cmp.kyriale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KyrialeRepository extends JpaRepository<Kyriale, Long> {
}
