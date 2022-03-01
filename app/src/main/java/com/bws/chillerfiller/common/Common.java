package com.bws.chillerfiller.common;

import android.util.Log;

import org.json.JSONObject;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

public class Common {

    public static String BASE_URL = "http://bigbazaar.uk/Service/BigBazaarService.svc/";
    public static String UserloginDetails = "UserloginDetails";

    public static String BASE_IMAGE_URL = "http://bigbazaar.uk/Content/images/";
    public static String PopulateDasboardProduct = "PopulateDasboardProduct";
    public static String PopulateProductDetais = "PopulateProductDetais";
    public static String InsertUpdateCartDetails = "InsertUpdateCartDetails";
    public static String GetAllCartDetails = "GetAllCartDetails";
    public static String PopulateProductDetailsByProductID = "PopulateProductDetailsByProductID";
    public static String RegistrationDetails = "RegistrationDetails";
    public static String InsertPleaceOrderDetails = "InsertPleaceOrderDetails";
    public static String GetAllRatingAginstProduct = "GetAllRatingAginstProduct";
    public static String InsertReviewDetails = "InsertReviewDetails";

    public static HttpEntity entity(JSONObject jsonObject) {
        HttpEntity entity = null;
        try {
            entity = new StringEntity(String.valueOf(jsonObject), "UTF-8");
        } catch (IllegalArgumentException e) {
            Log.d("HTTP", "StringEntity: IllegalArgumentException");
            return entity;
        }
        return entity;
    }
}
