package com.zhuandian.paysuccessview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSuccess;
    private Button btnError;
    private PayErrorView viewError;
    private PaySuccessView viewSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSuccess = (Button) findViewById(R.id.success);
        btnError = (Button) findViewById(R.id.error);
        viewError = (PayErrorView)findViewById(R.id.view_error);
        viewSuccess = (PaySuccessView)findViewById(R.id.view_success);


        btnError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewError.animatorStart();
            }
        });

        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewSuccess.animatorStart();
            }
        });
    }
}
