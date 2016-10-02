package me.xiazdong.recyclerviewdemo.demo1;

import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.xiazdong.recyclerviewdemo.R;

/**
 * Created by xiazdong on 16/9/26.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.VH> {

    private ArrayList<ObjectModel> mDatas;
    private OnClickListener mListener;

    public CustomAdapter(ArrayList<ObjectModel> datas){
        this.mDatas = datas;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_1, parent, false);
        //LayoutInflater.from(parent.getContext()).inflate(R.layout.item_1, null); 是不行的
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        ObjectModel model = mDatas.get(position);
        holder.number.setText(model.number + "");
        holder.title.setText(model.title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    mListener.onItemClick(view, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public static class VH extends RecyclerView.ViewHolder{
        public final TextView title;
        public final TextView number;
        public VH(View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            number = (TextView) v.findViewById(R.id.number);
        }
    }

    public void setOnClickListener(OnClickListener listener){
        this.mListener = listener;
    }


    public interface OnClickListener{
        void onItemClick(View view, int position);
    }
}
