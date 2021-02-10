package com.flight.booking.exception;

public class IsCreditCardValidException extends BaseException{
    public IsCreditCardValidException(String message) {
        super(message);
    }

    public static IsCreditCardValidException buildException() {

        return new IsCreditCardValidException(ExceptionMessages.CREDIT_CARD_NOT_VALID.getMessage());
    }

    public static IsCreditCardValidException buildExceptionForLength(){
        return new IsCreditCardValidException(ExceptionMessages.CREDIT_CARD_LENGTH_NOT_VALID.getMessage());
    }
}
