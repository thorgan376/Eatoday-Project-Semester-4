package eatoday.com.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


import eatoday.com.R;
import eatoday.com.model.Food;

public class DetailApdapter extends FirebaseRecyclerAdapter<Food, DetailApdapter.foodViewholder> {

    public DetailApdapter(
            @NonNull FirebaseRecyclerOptions<Food> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull foodViewholder holder, int position, @NonNull Food model) {
        holder.nameFood.setText(model.getNameFood());
         Glide.with(holder.imgFood.getContext()).load(model.getImageFood()).into(holder.imgFood);
//        Glide.with(holder.imgFood.getContext())
//                .load(model.getImageFood())
//                .placeholder(R.drawable.ic_food_placeholder)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.imgFood);
//        holder.img1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AppCompatActivity activity=(AppCompatActivity)view.getContext();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new descfragment(model.getName(),model.getCourse(),model.getEmail(),model.getPurl())).addToBackStack(null).commit();
//            }
//        });
    }

    @NonNull
    @Override
    public foodViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_category, parent, false);
        return new DetailApdapter.foodViewholder(view);
    }
    class foodViewholder
            extends RecyclerView.ViewHolder {
        TextView nameFood,des,ingre,link;
        ImageView imgFood;
        public foodViewholder(@NonNull View itemView)
        {
            super(itemView);
            nameFood = itemView.findViewById(R.id.tvTitle);
            des = itemView.findViewById(R.id.tvInstructions);
            ingre = itemView.findViewById(R.id.tvIngredients);
            link = itemView.findViewById(R.id.tvYoutube);
            imgFood = itemView.findViewById(R.id.imgThumb);
        }

    }
}
