package com.lxisoft.repository;

import com.lxisoft.domain.QstnOption;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the QstnOption entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QstnOptionRepository extends JpaRepository<QstnOption, Long> {
}
