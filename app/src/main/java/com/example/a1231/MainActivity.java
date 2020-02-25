package com.example.a1231;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox mCheckboxsx;
    private CheckBox mCheckboxxm;
    private CheckBox mCheckboxpg;
    private CheckBox mCheckboxhw;
    private Set<Integer> mCheckboxs = new HashSet<>();
    private Set<Phone> mPhone = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
    }

    private void initViews() {
        mCheckboxsx = findViewById(R.id.check_sx);
        mCheckboxxm = findViewById(R.id.check_xm);
        mCheckboxpg = findViewById(R.id.check_pg);
        mCheckboxhw = findViewById(R.id.check_hw);

        //boolean isChecke=mCheckboxsx.isChecked();
        //mCheckboxhw.setChecked(true);
        mCheckboxsx.setOnCheckedChangeListener(this);
        mCheckboxhw.setOnCheckedChangeListener(this);
        mCheckboxpg.setOnCheckedChangeListener(this);
        mCheckboxxm.setOnCheckedChangeListener(this);
        Button searchButton = findViewById(R.id.search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set<Phone> result = getmPhone();
                for (Phone phone : result) {
                    System.out.println("Phone:" + phone.getBrand() + "" + phone.getModel());
                    Log.d("ZJ", "Phone:" + phone.getBrand() + "" + phone.getModel());
                }
            }
        });
    }

    private Set<Phone> getmPhone() {
        Set<Phone> result = new HashSet<>();
        if (0 != mCheckboxs.size()) {
            for (Phone phone : mPhone) {
                String brand = phone.getBrand();
                int resId = -1;
                switch (brand) {
                    case "苹果":
                        resId = R.id.check_pg;
                        break;
                    case "小米":
                        resId = R.id.check_xm;
                        break;
                    case "华为":
                        resId = R.id.check_hw;
                        break;
                    case "三星":
                        resId = R.id.check_sx;
                        break;
                    default:
                        resId = -1;
                        break;
                }
                if (mCheckboxs.contains(resId)) {
                    result.add(phone);
                }
            }
        }
        return result;
    }

    private void initData() {
        mPhone.add(new Phone("苹果", "iPhone4"));
        mPhone.add(new Phone("苹果", "iPhone5"));
        mPhone.add(new Phone("苹果", "iPhone6"));
        mPhone.add(new Phone("苹果", "iPhone7"));

        mPhone.add(new Phone("三星", "Galaxy8"));
        mPhone.add(new Phone("三星", "Galaxy6"));
        mPhone.add(new Phone("三星", "Galaxy2"));
        mPhone.add(new Phone("三星", "Galaxy fef"));

        mPhone.add(new Phone("小米", "红米not"));
        mPhone.add(new Phone("小米", "5s"));
        mPhone.add(new Phone("小米", "67t"));
        mPhone.add(new Phone("小米", "9pro"));

        mPhone.add(new Phone("华为", "pro"));
        mPhone.add(new Phone("华为", "not"));
        mPhone.add(new Phone("华为", "8"));
        mPhone.add(new Phone("华为", "mate10"));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("ZJ:", "" + isChecked + buttonView.getId());
        if (isChecked) {
            mCheckboxs.add(buttonView.getId());
        } else
            mCheckboxs.remove(buttonView.getId());
    }

    public class Phone {
        private String brand;
        private String model;

        public Phone(String brand, String model) {
            this.brand = brand;
            this.model = model;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }
    }
}
