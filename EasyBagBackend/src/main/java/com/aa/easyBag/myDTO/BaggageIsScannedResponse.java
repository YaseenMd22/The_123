package com.aa.easyBag.myDTO;

public class BaggageIsScannedResponse {

    public int getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(int bagNumber) {
        this.bagNumber = bagNumber;
    }
    private int bagNumber;

    public int getBeltNumber() {
        return beltNumber;
    }

    public void setBeltNumber(int beltNumber) {
        this.beltNumber = beltNumber;
    }

    private int beltNumber;


    public BaggageIsScannedResponse( int bagNumber, int beltNumber) {
        this.bagNumber = bagNumber;
        this.beltNumber = beltNumber;
    }

}
