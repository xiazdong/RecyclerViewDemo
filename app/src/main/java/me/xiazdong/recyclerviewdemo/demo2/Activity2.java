package me.xiazdong.recyclerviewdemo.demo2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import me.xiazdong.recyclerviewdemo.R;

/**
 * Demo2：ListView局部刷新
 */
public class Activity2 extends AppCompatActivity {

    public static final String ACTION_UPDATE_PROGRESS = "action_update_progress";
    public static final String KEY_POSITION = "key_position";
    public static final String KEY_PROGRESS = "key_progress";
    private ListView mLv;
    private List<Job> mData;
    private DownloadAdapter mAdapter;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int position = intent.getIntExtra(KEY_POSITION, -1);
            int progress = intent.getIntExtra(KEY_PROGRESS, -1);
            if(position != -1 && progress != -1) {
                Job job = mData.get(position);
                job.progress = progress;
                mAdapter.notifyItemChanged(mLv, position);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        mLv = (ListView) findViewById(R.id.lv);
        mAdapter = new DownloadAdapter(this, initData());
        mLv.setAdapter(mAdapter);
        IntentFilter filter = new IntentFilter(ACTION_UPDATE_PROGRESS);
        registerReceiver(mReceiver, filter);
        mAdapter.notifyDataSetChanged();
    }
    List<Job> initData(){
        String[] urls = this.getResources().getStringArray(R.array.download_array);
        mData = new ArrayList<>();
        for(String url : urls){
            Job job = new Job();
            job.name = url.substring(0,10);
            job.progress = 0;
            job.url = url;
            mData.add(job);
        }
        return mData;
    }
}
