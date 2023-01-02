package eatoday.com;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MyPostFragment extends Fragment {
    Button btnPost;
    private EditText edt_namefood;
    private EditText edt_Ingredient;
    private EditText edt_Describle;
    private EditText edt_link;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onResume() {
        super.onResume();
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        configView();
//        addListener();
        View v = inflater.inflate(R.layout.fragment_my_post, container, false);

        btnPost = (Button) v.findViewById(R.id.btnPost);
//        listView = findViewById(R.id.list_view_note);
        edt_namefood = (EditText) v.findViewById(R.id.txtNameFood);
        edt_Describle = (EditText) v.findViewById(R.id.txtDescrible);
        edt_Ingredient = (EditText) v.findViewById(R.id.txtIngredient);
        edt_link = (EditText) v.findViewById(R.id.txtLinkVideo);
        edt_Describle.setOnTouchListener((v1, event) -> {
            if (edt_Describle.hasFocus()) {
                v1.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_SCROLL:
                        v1.getParent().requestDisallowInterceptTouchEvent(false);
                        return true;
                }
            }
            return false;
        });
        edt_Ingredient.setOnTouchListener((v1, event) -> {
            if (edt_Ingredient.hasFocus()) {
                v1.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_SCROLL:
                        v1.getParent().requestDisallowInterceptTouchEvent(false);
                        return true;
                }
            }
            return false;
        });
//        edt_Ingredient.setOnEditorActionListener(new DoneOnEditorActionListener());
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListener(v);
            }
        });

        return v;
    }


    //    private void configView() {
//        btnPost = btnPost.findViewById(R.id.btnPost);
////        listView = findViewById(R.id.list_view_note);
//        edt_namefood = edt_namefood.findViewById(R.id.txtNameFood);
//        edt_Describle = edt_Describle.findViewById(R.id.txtDescrible);
//        edt_Ingredient = edt_Ingredient.findViewById(R.id.txtIngredient);
//        edt_link = edt_link.findViewById(R.id.txtLinkVideo);
//
//    }
    private void addListener(View v) {
        String namefoods = edt_namefood.getText().toString();
        String Ingredient = edt_Ingredient.getText().toString();
        String Describle = edt_Describle.getText().toString();
        String link = edt_link.getText().toString();
        if (!namefoods.isEmpty() && !Ingredient.isEmpty() && !Describle.isEmpty() && !link.isEmpty()) {
            Toast.makeText(getActivity(), namefoods + " " + Ingredient + " " + Describle + " " + link, Toast.LENGTH_SHORT).show();
////                Mytask taskModel = new Mytask();
//                taskModel.setTaskId(id);
//                taskModel.setTaskTitle(title);
//                taskModel.setTaskContent(content);
//                taskModel.setImportant(important);
//                DBHelper database = (DBHelper) DBHelper.getInstance(MainActivity.this);
//                database.addTask(taskModel);
//
//                mytasks.add(taskModel);
//                taskAdapter.notifyDataSetChanged();
            edt_namefood.setText("");
            edt_Ingredient.setText("");
            edt_Describle.setText("");
            edt_link.setText("");

        } else {
            Toast.makeText(getActivity(), "Need to fill information!", Toast.LENGTH_SHORT).show();

        }
    }

//    public static void hideSoftKeyboard(Activity activity) {
//        InputMethodManager inputMethodManager =
//                (InputMethodManager) activity.getSystemService(
//                        Activity.INPUT_METHOD_SERVICE);
//        if (inputMethodManager.isAcceptingText()) {
//            inputMethodManager.hideSoftInputFromWindow(
//                    activity.getCurrentFocus().getWindowToken(),
//                    0
//            );
//        }
//
//    }

//    private class DoneOnEditorActionListener implements TextView.OnEditorActionListener {
//        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                return true;
//            }
//            return false;
//        }
//    }
}