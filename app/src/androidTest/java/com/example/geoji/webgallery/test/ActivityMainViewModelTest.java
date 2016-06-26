package com.example.geoji.webgallery.test;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import com.example.geoji.webgallery.MainActivity;
import com.example.geoji.webgallery.viewModel.ActivityMainViewModel;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Created by Geoji on 2016-06-26.
 */
@RunWith(AndroidJUnit4.class)
public class ActivityMainViewModelTest extends TestCase{
    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    private MainActivity mainActivity;
    private ActivityMainViewModel testActivityMainViewModel;

    @Before
    public void testSetup(){
        this.testActivityMainViewModel = new ActivityMainViewModel(rule.getActivity());
        this.mainActivity = rule.getActivity();
    }

    @Test
    public void test_setMainRecyclerViewAdapter(){
       RecyclerView testRecyclerView = new RecyclerView(mainActivity);
        testActivityMainViewModel.setMainRecyclerViewAdapter(testRecyclerView);
    }

    @Test
    public void test_getData(){
        testActivityMainViewModel.getData("사과");
    }
}
