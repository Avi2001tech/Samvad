package com.example.samvad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import soup.neumorphism.NeumorphButton;

public class OTP_verify extends AppCompatActivity {

    private EditText i1,i2,i3,i4,i5,i6;
    String OTPid;
    String phoneNumber;
    String verificationID;
    NeumorphButton verifyBtn;
    LinearLayout btnLay2;
    ProgressBar progressBar2;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);

        mAuth=FirebaseAuth.getInstance();
        phoneNumber = getIntent().getStringExtra("mobile").toString();

        i1 = findViewById(R.id.inputCode1);
        i2 = findViewById(R.id.inputCode2);
        i3 = findViewById(R.id.inputCode3);
        i4 = findViewById(R.id.inputCode4);
        i5 = findViewById(R.id.inputCode5);
        i6 = findViewById(R.id.inputCode6);

        TextView textNumber = findViewById(R.id.textNumber);
        textNumber.setText(phoneNumber);

        setupOTPInputs();
        verifyBtn = findViewById(R.id.verifyBtn);
        progressBar2=findViewById(R.id.progressBar2);
        btnLay2=findViewById(R.id.button_layout2);
        verificationID = getIntent().getStringExtra("verificationID");

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i1.getText().toString().trim().isEmpty()
                     || i2.getText().toString().trim().isEmpty()
                || i3.getText().toString().trim().isEmpty()
                || i4.getText().toString().trim().isEmpty()
                || i5.getText().toString().trim().isEmpty()
                || i6.getText().toString().trim().isEmpty()){
                    Toast.makeText(OTP_verify.this, "Please enter valid code", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = i1.getText().toString() + i2.getText().toString() + i3.getText().toString() + i4.getText().toString()
                        + i5.getText().toString() + i6.getText().toString();

                if(verificationID!=null){
                    progressBar2.setVisibility(View.VISIBLE);
                    btnLay2.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, code);
                    mAuth.signInWithCredential(credential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar2.setVisibility(View.GONE);
                                    btnLay2.setVisibility(View.VISIBLE);
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(OTP_verify.this,MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(OTP_verify.this, "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });

    }

    

    private void setupOTPInputs(){
        i1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    i2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        i2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    i3.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        i3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    i4.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        i4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    i5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        i5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    i6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}