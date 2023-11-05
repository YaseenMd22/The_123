package com.aa.easyBag.service;

import com.aa.easyBag.myDTO.BaggageIsScannedResponse;

import java.util.List;

public interface BaggageService {

        List<BaggageIsScannedResponse> getBaggageIsScannedInfoByCustomerId(Long customerId);
        // ... other method declarations ...
}
