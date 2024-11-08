package com.opzero.user.util;

import java.util.Random;

public class OtpUtil {
    public static String getOtp() {
        // Generate a random 6-digit number
        int otp = new Random().nextInt(900000) + 100000;
        return String.valueOf(otp);
    }
}
