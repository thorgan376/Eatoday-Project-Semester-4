package eatoday.com.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import eatoday.com.R;
import eatoday.com.model.Food;

/**
 * Created by Achmad Qomarudin on 19-10-2020.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Food> items;
    private MainAdapter.onSelectData onSelectData;
//    private Context mContext;

    public interface onSelectData {
        void onSelected(Food modelMain);
    }

    public MainAdapter(List<Food> items) {
//        this.mContext = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Food data = items.get(position);

        //Get Image
//        Glide.with(mContext)
//                .load(data.strCategoryThumb)
//                .placeholder(R.drawable.ic_food_placeholder)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.imgKategori);
        holder.textFood.setText(data.getNameFood());
        //holder.imgfood.setImageURI(data.getImageFood());
//        holder.imgfood.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onSelectData.onSelected(data);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textFood;
        public CircleImageView imgfood;

        public ViewHolder(View itemView) {
            super(itemView);
            textFood = itemView.findViewById(R.id.tvCategory);
            imgfood = itemView.findViewById(R.id.imgCategory);
        }
    }

}
