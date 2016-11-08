package com.selectfile.andt.myapplication;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Bundle bundle;
    TextView tvColor;

    private GradientView mTop;
    private GradientView mBottom;
    private TextView mTextView;
    private Drawable mIcon;
    private TextView tvOk,tvCancel;
    int mColor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSelectColor = (Button) findViewById(R.id.btnSelectColor);
        tvColor = (TextView) findViewById(R.id.tvColor);
        bundle = getIntent().getExtras();
        btnSelectColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        final View view = layoutInflater.inflate(R.layout.activity_main_color, null);
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setView(view);


        mIcon = getResources().getDrawable(R.mipmap.ic_launcher);
        mTextView = (TextView) view.findViewById(R.id.color);
        mTextView.setCompoundDrawablesWithIntrinsicBounds(mIcon, null, null, null);
        mTop = (GradientView) view.findViewById(R.id.top);
        mBottom = (GradientView) view.findViewById(R.id.bottom);
        tvOk = (TextView) view.findViewById(R.id.tvOk);
        tvCancel = (TextView) view.findViewById(R.id.tvCancel);
        mTop.setBrightnessGradientView(mBottom);
        mBottom.setOnColorChangedListener(new GradientView.OnColorChangedListener() {
            @Override
            public void onColorChanged(GradientView view, int color) {
                mTextView.setTextColor(color);
                mTextView.setText("#" + Integer.toHexString(color));
                mColor = color;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mIcon.setTint(color);
                }
            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvColor.setText("Mau la "+mTextView.getText().toString());
                dialog.dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        int color = 0xFF394572;
        mTop.setColor(color);
        dialog.show();
    }
}
