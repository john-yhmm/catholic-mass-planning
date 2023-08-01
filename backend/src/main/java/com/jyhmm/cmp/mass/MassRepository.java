package com.jyhmm.cmp.mass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MassRepository extends JpaRepository<Mass, Long> {
}
