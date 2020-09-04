package com.lambda.piggybank.controllers;


import com.lambda.piggybank.models.Coin;
import com.lambda.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController
{
    @Autowired
    CoinRepository coinrepos;

    //http://localhost:2019/total
    @GetMapping(value = "/total", produces = {"application/json"})
    public ResponseEntity<?> getTotal()
    {
        List<Coin> list = new ArrayList<>();
        coinrepos.findAll().iterator().forEachRemaining(list::add);

        list.forEach(c ->
        {
            if(c.getQuantity()>1)
            {
                System.out.println(c.getQuantity() + " " + c.getNameplural());
            }
            else
            {
                System.out.println(c.getQuantity() + " " + c.getName());
            }
        });
        double total = 0;
        for (Coin c : list)
        {
            total += (c.getQuantity()) * (c.getValue());
        }
        System.out.println("Total: " + total);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
