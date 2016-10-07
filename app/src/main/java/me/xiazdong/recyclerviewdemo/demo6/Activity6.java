package me.xiazdong.recyclerviewdemo.demo6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.xiazdong.recyclerviewdemo.R;

/**
 * RecyclerView实现瀑布流
 */
public class Activity6 extends AppCompatActivity {
    private RecyclerView mRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        StaggeredAdapter mAdapter = new StaggeredAdapter(this,initData());
        mAdapter.setHasStableIds(true);
        mRv.setAdapter(mAdapter);
        ((SimpleItemAnimator)mRv.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    public List<Integer> initData(){
        Integer[] images = {R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
                        R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10
                };
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<2;i++){
            for(Integer image:images){
                list.add(image);
            }
        }
        return list;
    }





}
