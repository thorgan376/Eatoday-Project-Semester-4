package eatoday.com.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import eatoday.com.R;
import eatoday.com.model.Food;

public class FoodAdapters extends BaseAdapter {
    private final List<Food> foods;

    public FoodAdapters(List<Food> foods) {
        this.foods = foods;
    }

    @Override
    public int getCount() {
        return foods.size();
    }

    @Override
    public Object getItem(int i) {//Cho listview biết object data được hiển thị ở vị trí thứ i là object nào
        return foods.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_item_category, viewGroup, false);
        }

        ImageView imgfoods = view.findViewById(R.id.imgFood);
        TextView txtFoods = view.findViewById(R.id.txtNameFood);

        Food food = foods.get(i);
       // imgfoods.setText(food.getImageFood());
        txtFoods.setText(food.getNameFood());

        return view;
    }
}
