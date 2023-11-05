package com.aa.easyBag.service;

import com.aa.easyBag.model.Baggage;
import com.aa.easyBag.myDTO.BaggageIsScannedResponse;
import com.aa.easyBag.repository.BaggageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BaggageServiceImpl implements BaggageService {


    private final BaggageRepository baggageRepository;

    @Autowired
    public BaggageServiceImpl(BaggageRepository baggageRepository) {
        this.baggageRepository = baggageRepository;
    }

    @Override
    public List<BaggageIsScannedResponse> getBaggageIsScannedInfoByCustomerId(Long customerId) {
        List<Baggage> baggageList = baggageRepository.findByCustomerId(customerId);

        getScannedBaggage(baggageList);

        return baggageList.stream().map(baggage ->
                new BaggageIsScannedResponse(
                        baggage.getBagNumber(), 13)
        ).collect(Collectors.toList());
    }


    public void getScannedBaggage(List<Baggage> baggageList) {
        baggageList.stream()                      // Convert list to stream
                .filter(Baggage::getScanned)              // Filter for only scanned items
                .collect(Collectors.toList());
    }

}
