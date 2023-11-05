package com.aa.easyBag.repository;

import com.aa.easyBag.model.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BaggageRepository extends JpaRepository<Baggage, String> {
    Optional<Baggage> findByBarcode(String barcode);

    List<Baggage> findByCustomerId(Long customerId);


}
