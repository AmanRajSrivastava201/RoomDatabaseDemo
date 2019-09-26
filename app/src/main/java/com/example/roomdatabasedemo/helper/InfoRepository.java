package com.example.roomdatabasedemo.helper;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomdatabasedemo.database.InfoDao;
import com.example.roomdatabasedemo.database.InfoDatabase;
import com.example.roomdatabasedemo.database.InfoEntity;

import java.util.List;

public class InfoRepository {
    private InfoDao mInfoData;
    private LiveData<List<InfoEntity>> mInfoLivedata;

    public InfoRepository(Application application) {
        InfoDatabase mydatabase = InfoDatabase.getInstance(application);
        mInfoData = mydatabase.infodao();
        mInfoLivedata = mInfoData.getdata();

    }

    public void insert(InfoEntity myentity) {
        new InsertAsynctask(mInfoData).execute(myentity);

    }

    public void update(InfoEntity entity) {
        new UpdateAsyncTask(mInfoData).execute(entity);

    }


    public int delete(int id) {
        new DeleteAsynctask(mInfoData).execute(id);
        return 1;

    }

    public LiveData<List<InfoEntity>> getdata() {

        return mInfoLivedata;
    }


    public static class InsertAsynctask extends AsyncTask<InfoEntity, Void, Void> {

        private InfoDao mDao;

        private InsertAsynctask(InfoDao mydao) {
            this.mDao = mydao;
        }

        @Override
        protected Void doInBackground(InfoEntity... myentities) {
            mDao.insert(myentities[0]);
            return null;
        }
    }

    public static class DeleteAsynctask extends AsyncTask<Integer, Void, Integer> {

        private InfoDao mDao;

        private DeleteAsynctask(InfoDao mydao) {
            this.mDao = mydao;
        }


        @Override
        protected Integer doInBackground(Integer... id) {

            return mDao.deleteByID(id[0]);
        }

        @Override
        protected void onPostExecute(Integer status) {
            super.onPostExecute(status);

        }
    }

    public static class UpdateAsyncTask extends AsyncTask<InfoEntity, Void, Void> {

        private InfoDao mInfodao;

        private UpdateAsyncTask(InfoDao mInfodao) {
            this.mInfodao = mInfodao;
        }

        @Override
        protected Void doInBackground(InfoEntity... entities) {
            mInfodao.update(entities[0].getFirstname(), entities[0].getSurname(), entities[0].getId());
            return null;
        }
    }
}
