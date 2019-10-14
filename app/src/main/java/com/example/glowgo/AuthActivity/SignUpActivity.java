package com.example.glowgo.AuthActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.glowgo.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity  extends AppCompatActivity {
    EditText email, passwordet, PasswordConfirmet, tv_userName, ed_detaildAddress, ed_phoneNumber;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        passwordet = (EditText) findViewById(R.id.password);
        PasswordConfirmet = (EditText) findViewById(R.id.PasswordConfirm);
        email = (EditText) findViewById(R.id.usMail);
        tv_userName = (EditText) findViewById(R.id.userName);
        ed_detaildAddress = findViewById(R.id.ed_detailedAddress);

        ed_phoneNumber = findViewById(R.id.ed_phone);




    }

    public void meth(View view) {

        String emailStr = email.getText().toString();

        //check mail valid
        if (isEmailValid(emailStr) != true) {
            AlertDialog alertDialog = new AlertDialog.Builder(SignUpActivity.this).create();
            alertDialog.setTitle("Email is not-valid");
            alertDialog.setMessage("Email is not-valid");
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();


        }
        //check password
        else if (passwordet.getText().toString().equals(PasswordConfirmet.getText().toString()) == false) {

            AlertDialog alertDialog = new AlertDialog.Builder(SignUpActivity.this).create();
            alertDialog.setTitle("Password is not valid");
            alertDialog.setMessage("Passwords doesn't match");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } else {

            savedata();
        }
    }

    public void savedata() {
        try {
            final ProgressDialog pd = ProgressDialog.show(SignUpActivity.this, "", "Signing Up, Please wait..", true);



            final ParseUser USER = new ParseUser();

            USER.put("username", String.valueOf(email.getText().toString()));

            USER.put("email", String.valueOf(email.getText().toString()));

            USER.put("name", String.valueOf(tv_userName.getText().toString()));

            USER.put("password", String.valueOf(passwordet.getText().toString()));

            USER.put("phone", String.valueOf(ed_phoneNumber.getText().toString()));

            USER.put("address", String.valueOf(ed_detaildAddress.getText().toString()));



            USER.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {

                        pd.dismiss();

                        finish();


                    } else {

                        pd.dismiss();
                        Toast.makeText(SignUpActivity.this, "onFail " + e.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    }

                }
            });


        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }

    }//savedata


    public boolean isEmailValid(String eemail) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = eemail;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }




}
