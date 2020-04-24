package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class FingerPrintHandler extends FingerprintManager.AuthenticationCallback {
    private  Context context;
    public FingerPrintHandler(Context context){
        this.context = context;

    }
    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){
        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject,cancellationSignal,0,this,null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        this.update(errString,false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("Fa",false);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update(helpString,false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("Hu",true);
    }

    private void update(CharSequence errString, boolean b) {
        TextView paraLabel = (TextView)((Activity)context).findViewById(R.id.paraLabel);
        paraLabel.setText(errString);
        if(b == false){
            paraLabel.setTextColor(ContextCompat.getColor(context,R.color.colorAccent));
        }
        else{
            paraLabel.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
        }
    }
}
