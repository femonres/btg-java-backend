package com.btg_pactual.controllers;

import com.example.dto.FundDTO;
import com.example.dto.SubscriptionDTO;
import com.example.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/funds")
public class FundController {

    @Autowired
    private FundService fundService;

    @GetMapping
    public List<FundDTO> getFunds() {
        return fundService.getAllFunds();
    }

    @PostMapping("/{fund_id}/subscribe")
    public void subscribeToFund(@PathVariable("fund_id") int fundId, @RequestBody SubscriptionDTO subscriptionDTO) {
        fundService.subscribeToFund(fundId, subscriptionDTO);
    }

    @PostMapping("/{fund_id}/unsubscribe")
    public void unsubscribeFromFund(@PathVariable("fund_id") int fundId, @RequestBody SubscriptionDTO subscriptionDTO) {
        fundService.unsubscribeFromFund(fundId, subscriptionDTO);
    }
}
