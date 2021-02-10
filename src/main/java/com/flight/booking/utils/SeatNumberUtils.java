package com.flight.booking.utils;

import java.util.ArrayList;
import java.util.List;

public class SeatNumberUtils {

    private SeatNumberUtils() {

    }

    static final int ASCI_CODE_OF_A = 65;


    public static List<String> crateSeatNumbers() {

        List<String> seatCodeList = new ArrayList<>();
        int number = 1;
        int charInt = ASCI_CODE_OF_A;

        for (int i = 0; i < 150; i++) {

            StringBuilder seatCodeSb = new StringBuilder();

            if (i != 0 && i % 6 == 0) {
                charInt++;
                number = 1;

            }
            seatCodeSb.append((char) charInt);
            seatCodeSb.append(number);
            number++;
            seatCodeList.add(seatCodeSb.toString());

        }
        return seatCodeList;
    }

}
