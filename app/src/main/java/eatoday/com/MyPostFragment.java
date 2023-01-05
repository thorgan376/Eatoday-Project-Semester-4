package eatoday.com;


import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;


public class MyPostFragment extends Fragment {
    Button btnPost;
    Button btnUploadImage;
    private static int RESULT_LOAD_IMAGE = 1;
    Uri imageUri;
    CircleImageView circleImageView;
    //ImageView imageView;
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        configView();
//        addListener();
        View v = inflater.inflate(R.layout.fragment_my_post, container, false);
//        listView = findViewById(R.id.list_view_note);
        circleImageView = (CircleImageView) v.findViewById(R.id.imageFood);
        btnPost = (Button) v.findViewById(R.id.btnPost);
        btnUploadImage = (Button) v.findViewById(R.id.btnUpload);
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
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addListener(v);
            }
        });
        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // ******** code for crop image
                i.putExtra("crop", "true");
                i.putExtra("aspectX", 100);
                i.putExtra("aspectY", 100);
                i.putExtra("outputX", 256);
                i.putExtra("outputY", 356);

                try {

                    i.putExtra("return-data", true);
                    startActivityForResult(
                            Intent.createChooser(i, "Select Picture"), 0);
                }catch (ActivityNotFoundException ex){
                    ex.printStackTrace();
                }


            }
        });

        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0 && resultCode == Activity.RESULT_OK){
            try {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = bundle.getParcelable("data");
                circleImageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //Uri returnUri;

    //        private void configView() {
//        btnPost = btnPost.findViewById(R.id.btnPost);
////        listView = findViewById(R.id.list_view_note);
//        edt_namefood = edt_namefood.findViewById(R.id.txtNameFood);
//        edt_Describle = edt_Describle.findViewById(R.id.txtDescrible);
//        edt_Ingredient = edt_Ingredient.findViewById(R.id.txtIngredient);
//        edt_link = edt_link.findViewById(R.id.txtLinkVideo);
//
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
//            Uri selectedImage = data.getData();
//            String[] filePathColumn = { MediaStore.Images.Media.DATA };
//            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
//            cursor.moveToFirst();
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String picturePath = cursor.getString(columnIndex);
//            cursor.close();
//            ImageView imageView = (ImageView) findViewById(R.id.imgView);
//            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//        }
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