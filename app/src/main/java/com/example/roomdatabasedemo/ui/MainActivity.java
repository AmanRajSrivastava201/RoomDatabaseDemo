package com.example.roomdatabasedemo.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.roomdatabasedemo.R;
import com.example.roomdatabasedemo.database.InfoEntity;
import com.example.roomdatabasedemo.helper.InfoViewModel;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private EditText mEtName;
    private EditText mEtSirname;
    private EditText mETId;
    private Button mBtSubmit;
    private Button mBTView;
    private Button mBtDelete;
    private Button mBTUpdate;
    private InfoViewModel mInfoViewmodel;
    private String mResult;
    private InfoEntity minfoentity = new InfoEntity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInfoViewmodel = ViewModelProviders.of(this).get(InfoViewModel.class);
        mInfoViewmodel.getdata().observe(this, new Observer<List<InfoEntity>>() {
            @Override
            public void onChanged(List<InfoEntity> infoEntities) {
                mResult = mInfoViewmodel.getDataAsString();
            }
        });


        mEtName = findViewById(R.id.edttxt_firstname);
        mEtSirname = findViewById(R.id.edttxt_surname);
        mETId = findViewById(R.id.edtxtx_id);
        mBtSubmit = findViewById(R.id.btn_submit);
        mBTView = findViewById(R.id.btn_view);
        mBtDelete = findViewById(R.id.btn_del);
        mBTUpdate = findViewById(R.id.btn_update);

        mBtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(mEtName.getText()) && !TextUtils.isEmpty(mEtSirname.getText()) && TextUtils.isEmpty(mETId.getText())) {

                    InfoEntity entityobject = new InfoEntity(mEtName.getText().toString(), mEtSirname.getText().toString());
                    mInfoViewmodel.insert(entityobject);
                    Toast.makeText(MainActivity.this, R.string.inserted, Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, R.string.not_inserted, Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBTView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Info")
                        .setMessage(mResult);
                AlertDialog dialog = builder.create();

                dialog.show();


            }
        });

        mBtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matchString = mETId.getText().toString();

                if (!TextUtils.isEmpty(matchString)) {
                    deleteItemFromDb(matchString);
                } else {
                    Toast.makeText(MainActivity.this, "Enter id", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mBTUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minfoentity.setFirstname(mEtName.getText().toString());
                minfoentity.setSurname(mEtSirname.getText().toString());
                minfoentity.setId(Integer.parseInt(mETId.getText().toString()));

                if (!TextUtils.isEmpty(mETId.getText())) {
                    mInfoViewmodel.update(minfoentity);
                    Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Enter all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    /**
     * THis functionvdfgdfndkgndfk
     *
     * @param matchString
     */
    private void deleteItemFromDb(String matchString) {

        int result = mInfoViewmodel.delete(Integer.parseInt(matchString));
        if (result > 0) {
            Toast.makeText(MainActivity.this, "Deleted  " + matchString, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Id don't exist", Toast.LENGTH_SHORT).show();
        }

    }


}
