package com.example.manvish.retrofitexample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.manvish.retrofitexample.Api;
import com.example.manvish.retrofitexample.MainActivity;
import com.example.manvish.retrofitexample.R;
import com.example.manvish.retrofitexample.UserListResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SecondActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<UserListResponse> userListResponseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        getUserListData(); // call a method in which we have implement our GET type web API
    }

    private void getUserListData() {
        // display a progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(SecondActivity.this);
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        progressDialog.show(); // show progress dialog

        // Api is a class in which we define a method getClient() that returns the API Interface class object
        // getUsersList() is a method in API Interface class, in this method we define our API sub url
        Api.getClient().getUsersList(new Callback<List<UserListResponse>>() {
            @Override
            public void success(List<UserListResponse> userListResponses, Response response) {
                // in this method we will get the response from API
                progressDialog.dismiss(); //dismiss progress dialog
                userListResponseData = userListResponses;
                setDataInRecyclerView(); // call this method to set the data in adapter
            }

            @Override
            public void failure(RetrofitError error) {
                // if error occurs in network transaction then we can get the error in this method.
                Toast.makeText(SecondActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss(); //dismiss progress dialog

            }
        });
    }

    private void setDataInRecyclerView() {
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SecondActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        UsersAdapter usersAdapter = new UsersAdapter(SecondActivity.this, userListResponseData);
        recyclerView.setAdapter(usersAdapter); // set the Adapter to RecyclerView
    }
}