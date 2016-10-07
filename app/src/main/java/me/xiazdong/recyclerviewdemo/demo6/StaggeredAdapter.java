package me.xiazdong.recyclerviewdemo.demo6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.xiazdong.recyclerviewdemo.R;

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.VH>{

    private Context mContext;
    private List<Integer> mDatas;
    public StaggeredAdapter(Context context, List<Integer> data) {
        mContext = context;
        this.mDatas = data;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Picasso.with(mContext).load(mDatas.get(position)).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_6, parent, false);
        return new VH(v);
    }

    public static class VH extends RecyclerView.ViewHolder{
        public final ImageView image;
        public VH(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
        }
    }
}
