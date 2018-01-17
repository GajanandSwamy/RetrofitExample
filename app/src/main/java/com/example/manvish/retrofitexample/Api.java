package com.example.manvish.retrofitexample;

import retrofit.RestAdapter;

/**
 * Created by manvish on 1/17/18.
 */

public class Api {

    public static ApiInterface getClient() {

        // change your base URL
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://mobileappdatabase.in/") //Set the Root URL
                .build(); //Finally building the adapter

        //Creating object for our interface
        ApiInterface api = adapter.create(ApiInterface.class);
        return api; // return the APIInterface object
    }

}
