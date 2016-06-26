package com.example.geoji.webgallery.viewModel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geoji.webgallery.model.MainRecyclerItem;
import com.google.common.primitives.UnsignedInteger;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Geoji on 2016-06-24.
 */
public class ActivityMainViewModel implements ViewModel {
    private Context context;
    private List<MainRecyclerItem> itemList;
    private String searchData;
    private int page;
    private DataListener dataListener;
    MainRecyclerAdapter mainRecyclerAdapter;

    @Override
    public void destroy() {

    }

    public String getSearchData() {
        return searchData;
    }

    public void setSearchData(String searchData) {
        this.searchData = searchData;
    }

    public ActivityMainViewModel(Context context, DataListener dataListener){
        this.context = context;
        this.itemList = new LinkedList<MainRecyclerItem>();
        this.dataListener = dataListener;
        page = 1;
    }

    public void setMainRecyclerViewAdapter(RecyclerView recyclerView){
        mainRecyclerAdapter = new MainRecyclerAdapter(this.itemList);
        recyclerView.setAdapter(mainRecyclerAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
    }

    public View.OnClickListener onPriviousButtonClickListener(){
        return new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(page != 1)
                    page -= 1;
                getData(searchData, page);
            }
        };
    }

    public View.OnClickListener onNextButtonClickListener(){
        return new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                page += 1;
                getData(searchData, page);
            }
        };
    }

    public View.OnClickListener onClickButtonClickListener(){
        return new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                page = 1;
                getData(searchData, page);
            }
        };
    }

    public void getData(String search, int page) {
        class getJsonData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params)
            {
                try
                {
                    String key = "AIzaSyDylu7Nx8fk-QrTlWmqUNnZv5_Ici6giKk";
                    String cx = "004576948669961966460:e-0rcykiylc";
                    String page = "1";
                    String uri = "https://www.googleapis.com/customsearch/v1?key="+key+"&cx="+cx+"&q="+ URLEncoder.encode(params[0],"UTF-8")+"&searchType=image&start="+URLEncoder.encode(params[1], "UTF-8");

                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = br.readLine()) != null)
                    {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result)
            {
                try {
                    JSONObject json = new JSONObject(result);
                    JSONArray imageUrlArray = json.getJSONArray("items");

                    itemList.clear();

                    for(int i=0; i<imageUrlArray.length(); i++)
                    {
                        JSONObject tempJsonObject = imageUrlArray.getJSONObject(i);
                        String imageUrl = tempJsonObject.getString("link");
                        MainRecyclerItem tempItem = new MainRecyclerItem(imageUrl, imageUrl);
                        itemList.add(tempItem);
                    }

                    mainRecyclerAdapter.changeItemList(itemList);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        getJsonData j = new getJsonData();
        int searchPage = (page -1) * 10 + 1;
        dataListener.onRecyclerViewItemChanged();
        j.execute(search, String.valueOf(searchPage));
    }

    public interface DataListener{
        void onRecyclerViewItemChanged();
    }
}
