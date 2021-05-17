package com.ganga.product.deals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deals")
@CrossOrigin(origins = "*")
public class DealsController {

    private final DealsService dealsService;

    @Autowired
    public DealsController(DealsService dealsService) {
        this.dealsService = dealsService;
    }

    @GetMapping("/get_todays_deals")
    public List<DealsDto> getTodaysDeals(){
        return dealsService.getTodaysDeal();
    }

}
