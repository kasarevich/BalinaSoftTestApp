package com.balinasoft.balinasoftapp.mvp.photos;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balinasoft.balinasoftapp.R;
import com.balinasoft.domain.entity.Photo;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    private ArrayList<Photo> photoArrayList;
    private int itemLayout;
    private RecyclerItemClickListener recyclerItemClickListener;

    public void setRecyclerItemClickListener(RecyclerItemClickListener recyclerItemClickListener) {
        this.recyclerItemClickListener = recyclerItemClickListener;
    }



    public Adapter(ArrayList<Photo> photoArrayList, int itemLayout) {
        this.photoArrayList = photoArrayList;
        this.itemLayout = itemLayout;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new Holder(view);
    }

    public void addItems(List<Photo> photos) {
        photoArrayList.addAll(photos);
        notifyDataSetChanged();
    }




    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final Photo photo = photoArrayList.get(position);
        holder.title.setText(photo.getDate().toString());
        Glide
                .with(holder.itemView.getContext())
                .load(photo.getUrl())
                .into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        return photoArrayList.size();
    }




    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_photo_date);
            imageView = itemView.findViewById(R.id.item_image_preview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (recyclerItemClickListener != null)
                recyclerItemClickListener.onItemClickListener(photoArrayList.get(getAdapterPosition()));
        }
    }

    public interface RecyclerItemClickListener {
        void onItemClickListener(Photo photo);
    }


}
