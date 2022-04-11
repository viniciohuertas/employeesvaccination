package com.krugercorp.employeesvaccination.service.impl;

import com.krugercorp.employeesvaccination.entity.CatalogueDetail;
import com.krugercorp.employeesvaccination.repository.CatalogueDetailRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VaccinesServiceImplTest {

    private CatalogueDetailRepository catDetRepository;

    @Autowired
    public VaccinesServiceImplTest(CatalogueDetailRepository catDetRepository) {
        this.catDetRepository = catDetRepository;
    }

    @Test
    void validCatalogues() {
        List<CatalogueDetail> catalogueDetails = this.catDetRepository.findByAbbreviationDetail("vaccines");
        assertTrue(catalogueDetails.size() > 0);
    }


}