package com.ibizabroker.lms.controller;

import com.ibizabroker.lms.entity.ClinicalTest;
import com.ibizabroker.lms.service.ClinicalTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EssaiCliniqueController {

    @Autowired
    private ClinicalTestService clinicalTestService;
    //@PreAuthorize("hasRole('Admin')")
    @PostMapping("/essais-cliniques")
    public ClinicalTest saveEssaiClinique(@RequestBody ClinicalTest clinicalTest) {
        return clinicalTestService.save(clinicalTest);
    }

    @GetMapping("/essais")
    public List<ClinicalTest> getAllEssais() {
        return clinicalTestService.getAllEssais();
    }
}
