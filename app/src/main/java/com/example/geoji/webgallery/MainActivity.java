package com.example.geoji.webgallery;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.geoji.webgallery.databinding.ActivityMainBinding;
import com.example.geoji.webgallery.model.MainRecyclerItem;
import com.example.geoji.webgallery.viewModel.ActivityMainViewModel;

public class MainActivity extends AppCompatActivity implements ActivityMainViewModel.DataListener{
    private ActivityMainViewModel viewModel;
    public ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ActivityMainViewModel(this, this);
        viewModel.setMainRecyclerViewAdapter(binding.mainRecyclerView);
        binding.setViewModel(viewModel);
    }

    private void hideSoftKeyboard(){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(binding.mainSearchEditText.getWindowToken(), 0);
    }

    @Override
    public void onRecyclerViewItemChanged() {
        hideSoftKeyboard();
    }
}
