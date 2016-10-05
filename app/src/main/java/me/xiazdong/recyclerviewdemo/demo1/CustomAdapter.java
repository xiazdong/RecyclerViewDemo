package me.xiazdong.recyclerviewdemo.demo1;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import me.xiazdong.recyclerviewdemo.R;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public enum ITEM_TYPE{
        ITEM_TYPE_HEADER,
        ITEM_TYPE_NORMAL,
        ITEM_TYPE_FOOTER
    }

    private ArrayList<ObjectModel> mDatas;
    private OnClickListener mListener;

    public CustomAdapter(ArrayList<ObjectModel> datas){
        this.mDatas = datas;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return ITEM_TYPE.ITEM_TYPE_HEADER.ordinal();
        } else if(position == mDatas.size() + 1){
            return ITEM_TYPE.ITEM_TYPE_FOOTER.ordinal();
        } else {
            return ITEM_TYPE.ITEM_TYPE_NORMAL.ordinal();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        if(viewType == ITEM_TYPE.ITEM_TYPE_HEADER.ordinal()){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
            return new HeaderVH(v);
        } else if(viewType == ITEM_TYPE.ITEM_TYPE_FOOTER.ordinal()){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer, parent, false);
            return new FooterVH(v);
        } else if(viewType == ITEM_TYPE.ITEM_TYPE_NORMAL.ordinal()){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_1, parent, false);
            Log.i("onCreateViewHolder","onCreateViewHolder");
            return new VH(v);
        }
        return null;
        //LayoutInflater.from(parent.getContext()).inflate(R.layout.item_1, null); 是不行的
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof HeaderVH){
            ((HeaderVH)holder).text.setText("Header View");
            ((HeaderVH)holder).image.setImageResource(R.mipmap.ic_launcher);
        } else if(holder instanceof FooterVH){
            ((FooterVH)holder).image.setImageResource(R.mipmap.ic_launcher);
        } else if(holder instanceof VH){
            ObjectModel model = mDatas.get(position - 1);
            ((VH)holder).number.setText(model.number + "");
            ((VH)holder).title.setText(model.title);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener != null){
                        mListener.onItemClick(view, position - 1);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 2 : mDatas.size() + 2;
    }

    public void setOnClickListener(OnClickListener listener){
        this.mListener = listener;
    }

    public interface OnClickListener{
        void onItemClick(View view, int position);
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

    public static class HeaderVH extends RecyclerView.ViewHolder{
        public final TextView text;
        public final ImageView image;

        public HeaderVH(View v){
            super(v);
            text = (TextView) v.findViewById(R.id.tv);
            image = (ImageView) v.findViewById(R.id.image);
        }
    }


    public static class FooterVH extends RecyclerView.ViewHolder{
        public final ImageView image;

        public FooterVH(View v){
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
        }
    }
}
