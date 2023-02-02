package eatoday.com.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import eatoday.com.R;
import eatoday.com.adapter.DetailApdapter;
import eatoday.com.adapter.FoodAdapters;
import eatoday.com.model.Food;

public class Detail_Food_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DetailApdapter detailApdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        FirebaseRecyclerOptions<Food> options =
//                new FirebaseRecyclerOptions.Builder<Food>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference(), Food.class)
//                        .build();

        //detailApdapter=new DetailApdapter(options);
//        recview.setAdapter(foodAdapters);
        View view=inflater.inflate(R.layout.fragment_detail__food, container, false);
        return view;
    }
}