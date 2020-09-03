package com.turnkeyafrica.turnkeybankassurance.utils;

import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.turnkeyafrica.turnkeybankassurance.data.model.api.ApiError;
import com.turnkeyafrica.turnkeybankassurance.data.model.others.LocalError;

import java.io.EOFException;
import java.io.IOException;
import java.net.SocketTimeoutException;

public class ErrorBase {

    /*Local Error*/
    private static final LocalError ServerUnreachable =  new LocalError(1002,"Your request could not be processed at this moment. \nPlease try again later.");

    private static final LocalError ErrorM = new LocalError(1003,"Your request could not be processed at this moment. \nPlease try again later.");

    /*Server Error Unknown*/
    //todo change code when service is ready to properly format error according to  api format
    private static final LocalError ErrorMS = new LocalError(1000,"Your request could not be processed at this moment. \nPlease try again later.");

    private static final LocalError ErrorSessionExpired = new LocalError(401,"Session Expired");

    public static LocalError Error(Throwable throwable) {

        if (throwable instanceof ANError) {

            ANError error = (ANError) throwable;

            if(error.getErrorCode() != 401){

            if (error.getCause() instanceof EOFException) {

                return handleEOFException();

            } else if (error.getCause() instanceof SocketTimeoutException) {

                return handleSocketTimeoutException();

            } else if (error.getCause() instanceof IOException) {
                return handleIOException();
            } else if (error.getCause() instanceof NullPointerException) {

                return handleNullPointerException();
            } else {

                JsonParser parser = new JsonParser();
                if (!CommonUtils.StringIsEmpty(error.getErrorBody())) {
                    try{
                        JsonElement e = parser.parse(error.getErrorBody());
                        Gson gson = new Gson();
                        ApiError apiError = gson.fromJson(e, ApiError.class);
                        if (!CommonUtils.StringIsEmpty(apiError.getMessage())) {
                            return new LocalError(0, apiError.getMessage());
                        } else {
                            return ErrorMS;
                        }

                    }catch (Exception e){
                        return ErrorMS;
                    }
                }
            }
         }else {

                return ErrorSessionExpired;
            }
        }
        return ErrorMS;
    }


    private static LocalError handleEOFException(){
//todo solve this error
       // return ServerUnreachable;
        return new LocalError(0,"");
    }

    private static LocalError handleIOException(){

        return ErrorM;
    }

    private static LocalError handleSocketTimeoutException(){

        return ServerUnreachable;
    }

    private static LocalError handleNullPointerException(){

        return ErrorM ;
    }
}
