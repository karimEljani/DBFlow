package com.ibizabroker.lms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="essais")
@Data
public class ClinicalTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String protocolNumber;
    private String promoter;
    private String versionName;
    private Date versionDate;
    private String design;
    private String frequence;
    private String fenetre;
    private String acte;
    private String ff;
    private String cp;
    private String aoCategory;
    private String aoDescription;
    private String aoPrice;

    // Constructeurs, getters et setters
}
