package com.Ahmad.Caterer.unit;

import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.Ahmad.Caterer.CatererApplication;
import com.Ahmad.Caterer.Exception.GlobalExceptionHandler;
import com.Ahmad.Caterer.Pojo.Caterer;
import com.Ahmad.Caterer.Pojo.Location;
import com.Ahmad.Caterer.Service.CatererService;
import com.Ahmad.Caterer.Service.ProducerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CatererApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-unit.yml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T1ServiceUTest {
    @Autowired
    @Qualifier("catereservice")
    CatererService service;

    @Autowired
    ProducerService producer;

    @Test
    public void contextLoads() {

    }

    @Test
    public void testA1SaveCaterer() {
        Caterer caterer = new Caterer("Ahmad", 2, 5,
                new Location("isl", "99", 754142, "issa"), "9114764472", "9040337039",
                "pks1234@gmail.com");
        try {
            caterer = service.addCaterer(caterer);
            this.producer.sendMessage(caterer.toString());
        } catch (ConstraintViolationException e) {
            System.out.println(e.getMessage());
        } catch (GlobalExceptionHandler e) {
            System.out.println(e.getMessage());
        }
        assertNotNull(caterer);
    }

    @Test
    public void testA2KeywordCaterer() {
        Pageable pageable = PageRequest.of(0, 8);
        Page<Caterer> pageabl = service.getKeywordCaterer(pageable, "Ahmad");
        assertNotNull(pageabl);
    }

    @Test
    public void testA3findAllCaterer() {
        Pageable pageable = PageRequest.of(0, 8);
        Page<Caterer> pageabl = service.findAllCaterer(pageable);
        assertNotNull(pageabl);
    }


}
