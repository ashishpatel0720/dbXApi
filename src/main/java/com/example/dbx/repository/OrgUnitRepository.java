package com.example.dbx.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dbx.model.OrgUnit;

@Repository
public interface OrgUnitRepository extends JpaRepository<OrgUnit, Long> {
    // Optional<OrgUnit> findByName(String name);
    Page<OrgUnit> findAll(Pageable page);

    OrgUnit findByName(String name);

    Boolean existsByName(String name);
}