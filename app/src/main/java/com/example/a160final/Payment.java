package com.example.a160final;

/**
 * Created by badiparvaneh on 4/17/18.
 */

public class Payment {
    private String sixteenDigNum;
    private String securityCode;
    private String cardHolder;
    private String expDate;
    private String cardType;

    public Payment(String sixteenDigNum, String securityCode, String cardHolder, String expDate, String cardType) {
        this.sixteenDigNum = sixteenDigNum;
        this.securityCode = securityCode;
        this.cardHolder = cardHolder;
        this.expDate = expDate;
        this.cardType = cardType;
    }

    public String getSixteenDigNum() {
        return sixteenDigNum;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getExpDate() {
        return expDate;
    }

    public String getCardType() {
        return cardType;
    }

}
