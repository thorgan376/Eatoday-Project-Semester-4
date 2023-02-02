package eatoday.com.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import eatoday.com.R;
import eatoday.com.adapter.FoodAdapters;
import eatoday.com.model.Food;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    List<Food> foodList;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    private RecyclerView recyclerView;
    FoodAdapters foodAdapters; // Create Object of the Adapter class
    DatabaseReference mbase;
    RecyclerView recview;
    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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

        View view=inflater.inflate(R.layout.fragment_home, container, false);

        recview=(RecyclerView)view.findViewById(R.id.list_item);
//        recview.setLayoutManager(new LinearLayoutManager(getContext()));
        recview.setLayoutManager(new GridLayoutManager(getContext(), 2));

        FirebaseRecyclerOptions<Food> options =
                new FirebaseRecyclerOptions.Builder<Food>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Foods"), Food.class)
                        .build();

        foodAdapters=new FoodAdapters(options);
        recview.setAdapter(foodAdapters);
//        GridLayoutManager mLayoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
//        list_item.setLayoutManager(mLayoutManager);
//        list_item.setHasFixedSize(true);
//        recview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        foodAdapters.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        foodAdapters.stopListening();
    }
}