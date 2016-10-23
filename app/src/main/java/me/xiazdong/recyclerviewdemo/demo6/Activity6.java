package me.xiazdong.recyclerviewdemo.demo6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.xiazdong.recyclerviewdemo.R;

/**
 * RecyclerView实现瀑布流
 *
 * 由于该例子比较简单，因此顺便介绍万能Adapter方案
 */
public class Activity6 extends AppCompatActivity {
    private RecyclerView mRv;
    private QuickAdapter<Integer> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new QuickAdapter<Integer>(initData()) {
            @Override
            public int getLayoutId(int viewType) {
                return R.layout.item_6;
            }

            @Override
            public void convert(VH holder, Integer data, int position) {
                ImageView imageView = holder.getView(R.id.image);
                Picasso.with(Activity6.this).load(data).into(imageView);
                //holder.itemView.setOnClickListener();  此处添加点击事件
            }

            @Override
            public int getItemViewType(int position) {
                return super.getItemViewType(position);
            }
        };
        mAdapter.setHasStableIds(true);
        ((SimpleItemAnimator)mRv.getItemAnimator()).setSupportsChangeAnimations(false);
        mRv.setAdapter(mAdapter);

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
