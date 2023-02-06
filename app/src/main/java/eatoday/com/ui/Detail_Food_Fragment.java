package eatoday.com.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import eatoday.com.R;

public class Detail_Food_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String nameFood, desFood, ingre, linkVideo,imgFood;
    private Toolbar toolbar;
    public Detail_Food_Fragment() {

    }
    public Detail_Food_Fragment(String nameFood, String desFood, String ingre, String linkVideo,String imgFood) {
        this.nameFood=nameFood;
        this.desFood=desFood;
        this.ingre=ingre;
        this.linkVideo=linkVideo;
        this.imgFood=imgFood;
    }

    public static Detail_Food_Fragment newInstance(String param1, String param2) {
        Detail_Food_Fragment fragment = new Detail_Food_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_detail__food, container, false);
        ImageView imageholder=view.findViewById(R.id.imgThumb);
        TextView nameholder=view.findViewById(R.id.tvTitle);
        TextView desholder=view.findViewById(R.id.tvInstructions);
        TextView inglholder=view.findViewById(R.id.tvIngredients);

        nameholder.setText(nameFood);
        desholder.setText(desFood);
        inglholder.setText(ingre);

        Glide.with(getContext()).load(imgFood).into(imageholder);
        toolbar = view.findViewById(R.id.toolbar_detail_a);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        return  view;
       // View view=inflater.inflate(R.layout.fragment_detail__food, container, false);
    }

    private void onBackPressed() {
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new HomeFragment()).addToBackStack(null).commit();

    }
}