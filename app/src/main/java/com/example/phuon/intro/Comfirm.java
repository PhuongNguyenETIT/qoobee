package com.example.phuon.intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Comfirm extends AppCompatActivity {

    Button buttonConfirm;
    EditText edtPhone;
    SharedPreferences preferencesConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comfirm);

        buttonConfirm = (Button) findViewById(R.id.btConfirm);
        edtPhone      = (EditText) findViewById(R.id.comfirmPhone);

        preferencesConfirm = getSharedPreferences("comfirmOK", MODE_PRIVATE);
        Boolean Okcomfirm = preferencesConfirm.getBoolean("comfirm", false);
        if(Okcomfirm){
            startActivity(new Intent(Comfirm.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
        }

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getNumber = edtPhone.getText().toString();
                if((getNumber.indexOf("0987624280") >-1) || (getNumber.indexOf("280") > -1)){
                    SharedPreferences.Editor  editor = preferencesConfirm.edit();
                    editor.putBoolean("comfirm", true);
                    editor.commit();
                    startActivity(new Intent(Comfirm.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }
            }
        });

    }
}
