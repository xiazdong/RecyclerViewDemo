package me.xiazdong.recyclerviewdemo.demo5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.xiazdong.recyclerviewdemo.R;
import me.xiazdong.recyclerviewdemo.demo1.ObjectModel;

/**
 * RecyclerView实现setEmptyView()
 */
public class Activity5 extends AppCompatActivity {
    private EmptyRecyclerView mRv;
    private List<String> mData;
    private NormalAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        mRv = (EmptyRecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        mAdapter = new NormalAdapter(mData);
        //View view = LayoutInflater.from(this).inflate(R.layout.empty, null);
        View view = findViewById(R.id.text_empty);
        mRv.setEmptyView(view);
        mRv.setAdapter(mAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_5, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_add:
                mData.add(0,"hello");
                mAdapter.notifyItemInserted(0);
                break;
            case R.id.item_delete:
                if(!mData.isEmpty()) {
                    mData.remove(0);
                    mAdapter.notifyItemRemoved(0);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
