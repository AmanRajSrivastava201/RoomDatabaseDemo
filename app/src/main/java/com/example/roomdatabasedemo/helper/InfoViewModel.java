package com.example.roomdatabasedemo.helper;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomdatabasedemo.database.InfoEntity;

import java.util.List;

public class InfoViewModel extends AndroidViewModel {
    private InfoRepository mRepository;


    public InfoViewModel(@NonNull Application application) {
        super(application);
        mRepository = new InfoRepository(application);
    }

    public void insert(InfoEntity myentity){
        mRepository.insert(myentity);
    }

    public void update(InfoEntity entity){
        mRepository.update(entity);
    }

    public int delete(int id){
        return  mRepository.delete(id);
    }

    public LiveData<List<InfoEntity>> getdata(){
        return mRepository.getdata();
    }

    public String getDataAsString(){
        LiveData<List<InfoEntity>> data = mRepository.getdata();
        List<InfoEntity> mylist =  data.getValue();
        StringBuilder stringBuilder = new StringBuilder();
        Log.i("print", "just print");
        for (InfoEntity i : mylist){
            stringBuilder.append(i.getId()).append("\n").append(i.getFirstname()).append("\n").append(i.getSurname()).append("\n\n");
        }
        return stringBuilder.toString();

    }


}
