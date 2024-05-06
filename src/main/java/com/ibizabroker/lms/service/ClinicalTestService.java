package com.ibizabroker.lms.service;

import com.ibizabroker.lms.dao.EssaiCliniqueRepository;
import com.ibizabroker.lms.entity.ClinicalTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicalTestService {

    @Autowired
    private EssaiCliniqueRepository essaiCliniqueRepository;

    public ClinicalTest save(ClinicalTest clinicalTest) {
        return essaiCliniqueRepository.save(clinicalTest);
    }

    public List<ClinicalTest> getAllEssais() {
        return essaiCliniqueRepository.findAll();
    }

    // Ajoutez d'autres méthodes de service si nécessaire
}
