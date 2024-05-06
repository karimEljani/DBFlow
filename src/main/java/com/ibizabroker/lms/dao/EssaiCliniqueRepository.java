package com.ibizabroker.lms.dao;

import com.ibizabroker.lms.entity.ClinicalTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EssaiCliniqueRepository extends JpaRepository<ClinicalTest, Long> {
    // Ajoutez des méthodes personnalisées si nécessaire
}
