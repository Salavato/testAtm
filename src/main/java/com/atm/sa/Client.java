package com.atm.sa;

public class Client {
    public int cardNumber;
    public int pin;
    public boolean cardExist;

    public Client(int cardNumber, int pin, boolean cardExist) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.cardExist = cardExist;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public boolean isCardExist() {
        return cardExist;
    }

    public void setCardExist(boolean cardExist) {
        this.cardExist = cardExist;
    }
}
