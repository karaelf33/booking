package com.flight.booking.utils;

import com.flight.booking.dto.GenericDTO;

public class OperationUtils {

    private OperationUtils() {

    }

    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MESSAGE = "SUCCESS";
    public static final int FAIL_CODE = 500;


    public static GenericDTO returnMessageHandling(Object object, int resultCode, boolean resultFlag, String resultMessage) {

        GenericDTO genericDTO = new GenericDTO();

        genericDTO.setResultData(object);
        genericDTO.setResultCode(resultCode);
        genericDTO.setResultFlag(resultFlag);
        genericDTO.setResultMessage(resultMessage);

        return genericDTO;
    }

    public static int[] getStepPoints(double totalTicketCount) {

        int[] stepPoints = new int[9];

        final int stepPointInterval = (int)((totalTicketCount / 100.0) * 10.0);

        for (int i = 0; i < 9; i++) {
            if (i == 0)
                stepPoints[i] = stepPointInterval;

            else {
                stepPoints[i] = stepPoints[i - 1] + stepPointInterval;
            }

        }

        return stepPoints;
    }
}
