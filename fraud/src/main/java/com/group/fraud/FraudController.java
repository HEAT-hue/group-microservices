package com.group.fraud;

import com.group.clients.fraud.FraudCheckResponse;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
@RequiredArgsConstructor
@Slf4j
public class FraudController {

    private final FraudCheckService fraudCheckService;

    @Observed(
            name = "user.name",
            contextualName = "Check if customer fraudulent",
            lowCardinalityKeyValues = {"userType", "userType2"}
    )
    @GetMapping("{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId) {
        Boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
        log.info("Fraud check for customer: {}", customerId);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
