package me.xiazdong.recyclerviewdemo.demo2;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.daimajia.numberprogressbar.NumberProgressBar;

import java.util.List;

import me.xiazdong.recyclerviewdemo.R;

/**
 * Created by xiazdong on 16/10/2.
 */
public class DownloadAdapter extends BaseAdapter{
    private List<Job> mData;
    private Context mContext;
    public DownloadAdapter(Context context, List<Job> datas) {
        mData = datas;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        VH holder = null;
        final Job job = mData.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_download,parent,false);
            holder = new VH();
            holder.btn = (Button) convertView.findViewById(R.id.btn_download);
            holder.progress = (NumberProgressBar) convertView.findViewById(R.id.progress);
            convertView.setTag(holder);
        } else{
            holder = (VH) convertView.getTag();
        }
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask(mContext, position).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, job.url);
            }
        });
        if(job.progress == 100){
            holder.btn.setText("完成");
        } else{
            holder.btn.setText("下载");
        }
        holder.progress.setProgress(job.progress);
        return convertView;
    }

    /**
     * 局部更新API
     */
    public void notifyItemChanged(ListView listview, int position){
        int firstPos = listview.getFirstVisiblePosition();
        int lastPos = listview.getLastVisiblePosition();
        Job job = mData.get(position);
        if(position >= firstPos && position <= lastPos){
            View view = listview.getChildAt(position - firstPos); //NOTE
            DownloadAdapter.VH vh = (DownloadAdapter.VH) view.getTag();
            vh.progress.setProgress(job.progress);
            if(job.progress == 100){
                vh.btn.setText("完成");
            }
        }
    }


    static class VH{
        Button btn;
        NumberProgressBar progress;
    }
}
