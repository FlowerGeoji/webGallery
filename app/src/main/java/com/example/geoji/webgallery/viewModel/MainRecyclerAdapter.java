package com.example.geoji.webgallery.viewModel;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.geoji.webgallery.R;
import com.example.geoji.webgallery.databinding.MainRecyclerItemBinding;
import com.example.geoji.webgallery.model.MainRecyclerItem;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Geoji on 2016-06-24.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainRecyclerViewHolder> {
    private List<MainRecyclerItem> itemList;
    private Context context;

    public MainRecyclerAdapter(List<MainRecyclerItem> itemList){
        this.itemList = new LinkedList<MainRecyclerItem>();
        this.itemList.addAll(itemList);
    }
    @Override
    public MainRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View statusContainer = inflater.inflate(R.layout.main_recycler_item, parent, false);
        return new MainRecyclerViewHolder(statusContainer);
    }

    @Override
    public void onBindViewHolder(MainRecyclerViewHolder holder, int position) {
        MainRecyclerItem item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void changeItemList(List<MainRecyclerItem> itemList){
        this.itemList.clear();
        this.itemList.addAll(itemList);
        notifyDataSetChanged();
    }

    public class MainRecyclerViewHolder extends RecyclerView.ViewHolder{
        private MainRecyclerItemBinding binding;

        public MainRecyclerViewHolder(View itemView) {
            super(itemView);
            this.binding = DataBindingUtil.bind(itemView);
        }

        public void bind(MainRecyclerItem item){
            int gridWidth = getGridWidth();

            this.binding.setViewModel(item);
            binding.itemImageView.getLayoutParams().width = gridWidth;
            binding.itemImageView.getLayoutParams().height = gridWidth;
        }

        private int getGridWidth(){
            WindowManager wm = (WindowManager)this.itemView.getContext().getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point displaySize = new Point();
            display.getSize(displaySize);
            int displayWidth = displaySize.x;

            return displayWidth / 3;
        }
    }
}
