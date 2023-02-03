package eatoday.com.ui;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import eatoday.com.R;
import eatoday.com.adapter.FoodAdapters;
import eatoday.com.model.Food;
import io.reactivex.rxjava3.annotations.NonNull;

public class HomeFragment extends Fragment {
    private List<Food> mList;
    private FoodAdapters foodAdapters;
    private DatabaseReference mDatabaseReference;
    private RecyclerView recview;
    private FirebaseAuth mAuth;
    private String user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_home, container, false);
        recview=(RecyclerView)view.findViewById(R.id.list_item);
        recview.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mList = new ArrayList<>();
        foodAdapters = new FoodAdapters(mList);
        recview.setAdapter(foodAdapters);
        getListFoodFromRealtime();
        return view;
    }
    private void getListFoodFromRealtime(){
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser().getUid();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Foods").child("datas").child(user);
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Food food = dataSnapshot.getValue(Food.class);
                    mList.add(food);
                }
                foodAdapters.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"error",Toast.LENGTH_SHORT).show();
            }
        });
    }


//    @Override
//    public void onStart() {
//        super.onStart();
//        foodAdapters.startListening();
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        foodAdapters.stopListening();
//    }
}