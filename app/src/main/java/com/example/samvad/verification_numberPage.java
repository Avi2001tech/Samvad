package com.example.samvad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

import soup.neumorphism.NeumorphButton;
import soup.neumorphism.NeumorphFloatingActionButton;

public class verification_numberPage extends AppCompatActivity {
    CountryCodePicker ccp;
    EditText input_phn;
    LinearLayout OtpButton;
    NeumorphButton sendOTPbtn;
    NeumorphFloatingActionButton sendOTParr;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    String PhoneNumber;
    String OTPid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_number_page);

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        input_phn = (EditText) findViewById(R.id.input_phn);
        sendOTPbtn=findViewById(R.id.sendOTPbtn);
        sendOTParr=findViewById(R.id.sendOTParr);
        OtpButton = findViewById(R.id.button_layout);
        progressBar=findViewById(R.id.progressBar);

        mAuth= FirebaseAuth.getInstance();


        ccp.registerCarrierNumberEditText(input_phn);

        sendOTPbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(input_phn.getText().toString().trim().isEmpty()){
                    Toast.makeText(verification_numberPage.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                OtpButton.setVisibility(View.INVISIBLE);

                PhoneAuthOptions options;
                options = PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(ccp.getFullNumberWithPlus().trim().toString())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(verification_numberPage.this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                OtpButton.setVisibility(View.VISIBLE);
                                /*signInWithPhoneAuthCredential(phoneAuthCredential);*/
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                OtpButton.setVisibility(View.VISIBLE);
                                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                    // Invalid request
                                    Toast.makeText(verification_numberPage.this, "Invalid request", Toast.LENGTH_SHORT).show();
                                } /*else if (e instanceof FirebaseTooManyRequestsException) {
                                    // The SMS quota for the project has been exceeded
                                    Toast.makeText(verification_numberPage.this, "Too many SMS in a day , try later!", Toast.LENGTH_SHORT).show();
                                }*/
                                else{
                                    Toast.makeText(verification_numberPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                OtpButton.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(verification_numberPage.this,OTP_verify.class);
                                intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));
                                intent.putExtra("verificationID",s);
                                startActivity(intent);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
                PhoneAuthProvider.verifyPhoneNumber(options);

            }
        });

        sendOTParr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input_phn.getText().toString().trim().isEmpty()){
                    Toast.makeText(verification_numberPage.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                OtpButton.setVisibility(View.INVISIBLE);

                PhoneAuthOptions options;
                options = PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(ccp.getFullNumberWithPlus().trim().toString())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(verification_numberPage.this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                OtpButton.setVisibility(View.VISIBLE);
                                /*signInWithPhoneAuthCredential(phoneAuthCredential);*/
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                OtpButton.setVisibility(View.VISIBLE);
                                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                                    // Invalid request
                                    Toast.makeText(verification_numberPage.this, "Invalid request", Toast.LENGTH_SHORT).show();
                                } else if (e instanceof FirebaseTooManyRequestsException) {
                                    // The SMS quota for the project has been exceeded
                                    Toast.makeText(verification_numberPage.this, "The SMS quota for the project has been exceeded", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(verification_numberPage.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                OtpButton.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(verification_numberPage.this,OTP_verify.class);
                                intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));
                                intent.putExtra("verificationID",s);
                                startActivity(intent);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
                PhoneAuthProvider.verifyPhoneNumber(options);
            }
        });
    }


    /*private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(verification_numberPage.this,OTP_verify.class);
                            intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));
                            startActivity(intent);
                            finish();
                            // Sign in success, update UI with the signed-in user's information
                            // Update UI
                        } else {
                            Toast.makeText(verification_numberPage.this, "Sign In error", Toast.LENGTH_SHORT).show();
                            // Sign in failed, display a message and update the UI

                        }
                    }
                });
    }*/
}