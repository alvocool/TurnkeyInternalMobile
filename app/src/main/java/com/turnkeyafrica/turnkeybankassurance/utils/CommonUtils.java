package com.turnkeyafrica.turnkeybankassurance.utils;

import android.annotation.SuppressLint;
import android.util.Patterns;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ComparisonRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommonUtils {


    private static final String numberPlateRegex =
            "^K[A-Z]{2} [0-9]{3}[A-Z]|" +
            "G[A-Z]{2} [0-9]{3}[A-Z]$|" +
            "^[A-Z]{3} [0-9][A-Z][0-9]{3}$|" +
            "^[0-9]{2}[A-Z]{2} [0-9]{2}[A-Z]$|" +
            "^[A-Z]{2} [0-9]{3} [A-Z]{2}$";


    private static final String kraPinRegex = "^A[0-9]{9}[A-Z]$";

    private static final String cardNameRegex = "^[A-Z]+ [A-Z]{2,}$";

    private CommonUtils() {

    }

    public static ComparisonRequest.ComparisonRequestQuote setDefaultValue(ComparisonRequest.ComparisonRequestQuote comparisonRequestQuote) {

        comparisonRequestQuote.setAgnCode(BigDecimal.valueOf(0));

        comparisonRequestQuote.setAgnShtDesc("DIRECT");

        comparisonRequestQuote.setCurCode(BigDecimal.valueOf(1));

        comparisonRequestQuote.setCurSymbol("KES");

        comparisonRequestQuote.setSource("MOBILE");

        comparisonRequestQuote.setCoinLeaderCombined("YES");

        comparisonRequestQuote.setFreqOfPayment("ANNUALLY");

        return comparisonRequestQuote;
    }

    public static String generateEndDate(String startDate) throws ParseException {

        Date date = DateUtils.parseDate(startDate,
                "yyyy-MM-dd HH:mm:ss", "dd/MM-yyyy","dd-MM-yyyy",
                "yyyy-MM-dd");

        date = DateUtils.addDays(date,-1);

        date = DateUtils.addYears(date,1);

        return DateFormatUtils.format(date,"dd-MM-yyyy");

    }

    public static boolean isEmailValid(String email) {
        return !Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean StringIsEmpty(String string){

        return StringUtils.isBlank(string);
    }

    public static boolean isValidLimit(BigDecimal amount, BigDecimal limit) {
        return amount.compareTo(limit) < 0;
    }

    public static boolean verifyCardName(String cardName){

        Pattern p = Pattern.compile(cardNameRegex);
        Matcher m = p.matcher(cardName);

        return !m.matches();

    }

    public static boolean verifyKRAPin(String kraNumber){

        Pattern p = Pattern.compile(kraPinRegex);
        Matcher m = p.matcher(kraNumber);

        return !m.matches();


    }

    public static boolean StringIsEqual(String val1,String val2){

        return StringUtils.equals(val1,val2);
    }

    public static boolean ObjectIsNotNull(Object object){

        return object != null;
    }

    public static String formatSingleDate(String singledate) {

        if(singledate != null) {

            if(singledate.length() == 1){

                singledate = "0" + singledate;
            }

        }
        return singledate;

    }


    @SuppressLint("DefaultLocale")
    public static String StringToCurrency(String amount){
        if(!amount.equalsIgnoreCase("0")) {
            return String.format("%,.2f", Double.valueOf(amount));
        }
        return amount;
    }

    public static boolean isValidRegistrationNumber(String propertyId) {

        Pattern p = Pattern.compile(numberPlateRegex);
        Matcher m = p.matcher(propertyId);

        return !m.matches();
    }

    public static String phaseNumber(String number) {
        StringBuilder phasedNumber = new StringBuilder();

       if(number.length() >= 5){
           for(int i = 0; i <= number.length() -3; i++){
               phasedNumber.append("*");
           }
           phasedNumber.append(number.substring(number.length()-3));
       }else {
           phasedNumber = new StringBuilder(number);
       }
        return phasedNumber.toString();
    }
}
