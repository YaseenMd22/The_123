package com.aa.easyBag.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Baggage {
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Id
    private String barcode;
    private Long customerId;

    public boolean getScanned() {
        return isScanned;
    }

    public void setScanned(boolean scanned) {
        isScanned = scanned;
    }

    private boolean isScanned;

    public int getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(int bagNumber) {
        this.bagNumber = bagNumber;
    }

    private int bagNumber;

}
