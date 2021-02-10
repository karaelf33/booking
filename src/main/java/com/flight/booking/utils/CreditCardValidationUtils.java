package com.flight.booking.utils;

import com.flight.booking.exception.IsCreditCardValidException;

public class CreditCardValidationUtils {


    private CreditCardValidationUtils(){}

    private static final int VALID_CARD_NUMBER_LENGTH = 19;

    private static final char[] CARD_DELIMITER_ARRAY = {'.', '/', '-', ','};

    private static final int[] CARD_DELIMITER_INDEXES = {4, 9, 14};

    private static final String MASK_DELIMITER_SIGN = "******";


    public static String getMaskedCreditCardNumber(String creditCardNumber) throws IsCreditCardValidException {

        if (creditCardNumber.length() != VALID_CARD_NUMBER_LENGTH)
            throw IsCreditCardValidException.buildExceptionForLength();

        if (!isDelimitersPlacedCorrectly(creditCardNumber))
            throw IsCreditCardValidException.buildException();

        return removeDelimitersAndMaskCreditCardNumber(creditCardNumber);

    }


    private static boolean isDelimitersPlacedCorrectly(String creditCardNumber) {

        try {
            char firstDelimiter = creditCardNumber.charAt(CARD_DELIMITER_INDEXES[0]);

            boolean isDelimiterCharDetected = false;

            for (char c : CARD_DELIMITER_ARRAY) {

                if (c == creditCardNumber.charAt(CARD_DELIMITER_INDEXES[0])) {
                    isDelimiterCharDetected = true;
                    firstDelimiter = c;
                    break;
                }

            }

            if (
                    !isDelimiterCharDetected ||
                            !isOtherDelimitersSameWithFirstDelimiter(creditCardNumber, firstDelimiter) ||
                            isCharsBetweenDelimitersNumeric(creditCardNumber)
            )
                throw IsCreditCardValidException.buildException();

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;

        }
    }

    private static boolean isOtherDelimitersSameWithFirstDelimiter(String creditCardNumber, char firstDelimiter) {

        boolean isOtherDelimitersSame = true;

        for (int i : CARD_DELIMITER_INDEXES) {

            if (creditCardNumber.charAt(i) != firstDelimiter) {
                isOtherDelimitersSame = false;
                break;
            }
        }
        return isOtherDelimitersSame;

    }

    private static boolean isCharsBetweenDelimitersNumeric(String creditCardNumber) {

        boolean isChardBetweenDelimitersNumeric = true;

        for (int i = 0; i < 15; i = i + 5) {
            if (!isGivenStringNumeric(creditCardNumber.substring(i, i + 5))) {
                isChardBetweenDelimitersNumeric = false;
                break;
            }
        }
        return isChardBetweenDelimitersNumeric;
    }

    private static boolean isGivenStringNumeric(String numericString) {

        boolean numeric = true;
        try {
            Double.parseDouble(numericString);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        return numeric;

    }

    private static String removeDelimitersAndMaskCreditCardNumber(String creditCardNumber) {

        StringBuilder cardNumberSb = new StringBuilder(creditCardNumber);

        int minusValue = 0;

        for (int i : CARD_DELIMITER_INDEXES) {
            cardNumberSb.deleteCharAt(i - minusValue);
            minusValue++;
        }

        cardNumberSb.replace(6, 12, MASK_DELIMITER_SIGN);

        return cardNumberSb.toString();

    }

}
