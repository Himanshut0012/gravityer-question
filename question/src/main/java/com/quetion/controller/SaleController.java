package com.quetion.controller;

import com.quetion.dto.ProductPricesDTO;
import com.quetion.model.UserOrderDetails;
import com.quetion.service.SaleService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PostExchange;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping("/")
    public String check(){
        return "hello";
    }



    @PostMapping("/offer")
    public ResponseEntity<UserOrderDetails> purchageProductWithOffer2(@RequestBody ProductPricesDTO productPricesDTO){
        UserOrderDetails userOrderDetails = this.saleService.purchageProductWithOffer2(productPricesDTO);
        return ResponseEntity.ok(userOrderDetails);

    }



}
