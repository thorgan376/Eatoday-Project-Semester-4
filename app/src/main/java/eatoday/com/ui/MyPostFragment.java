package eatoday.com.ui;
import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;


import static eatoday.com.model.Food.count;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

import eatoday.com.R;
import eatoday.com.model.Food;

public class MyPostFragment extends Fragment {
    private static final int SELECT_FILE = 1;
    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;
    Button btnPost;
    private static final int RESULT_LOAD_IMAGE = 1;
    int SELECT_PICTURE = 200;
    ImageView circleImageView;
    Food food;
    private EditText edt_namefood;
    private EditText edt_Ingredient;
    private EditText edt_Describle;
    private EditText edt_link;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage storage;
    StorageReference storageReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onResume() {
        super.onResume();
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edt_Ingredient = view.findViewById(R.id.txtIngredient);

        view.setOnClickListener(v -> {
            edt_Ingredient.clearFocus();

            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_post, container, false);
        btnPost = view.findViewById(R.id.btnPost);
        edt_namefood = view.findViewById(R.id.txtNameFood);
        edt_Describle = view.findViewById(R.id.txtDescrible);
        edt_Ingredient = view.findViewById(R.id.txtIngredient);
        circleImageView = view.findViewById(R.id.imageFood);
        edt_link = view.findViewById(R.id.txtLinkVideo);

        firebaseDatabase = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        databaseReference = firebaseDatabase.getReference("Food");

        food = new Food();
        edt_Describle.setOnTouchListener((v1, event) -> {
            if (edt_Describle.hasFocus()) {
                v1.getParent().requestDisallowInterceptTouchEvent(true);
                if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_SCROLL) {
                    v1.getParent().requestDisallowInterceptTouchEvent(false);
                    return true;
                }
            }
            return false;
        });
        edt_Ingredient.setOnTouchListener((v1, event) -> {
            if (edt_Ingredient.hasFocus()) {
                v1.getParent().requestDisallowInterceptTouchEvent(true);
                if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_SCROLL) {
                    v1.getParent().requestDisallowInterceptTouchEvent(false);
                    return true;
                }
            }
            return false;
        });
        btnPost.setOnClickListener(v -> addListener(v));
        circleImageView.setOnClickListener(v12 -> SelectImage());
        return view;
    }

    private void SelectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(getActivity().getContentResolver(), filePath);
                circleImageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
//    void imageChooser() {
//        Intent i = new Intent();
//        i.setType("image/*");
//        i.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//            if (requestCode == SELECT_PICTURE) {
//                Uri selectedImageUri = data.getData();
//                if (null != selectedImageUri) {
//                    circleImageView.setImageURI(selectedImageUri);
//                    Log.v(TAG, "hahahah=" + circleImageView);
//                }
//            }
//        }
//    }
//    public void configListView() {
//        foodList = new ArrayList<>();
//        mainAdapter = new MainAdapter(foodList);
//        recyclerView.setAdapter(mainAdapter);
//    }

    private void addListener(View v) {
        String namefoods = edt_namefood.getText().toString();
        String ingredient = edt_Ingredient.getText().toString();
        String describle = edt_Describle.getText().toString();
        String link = edt_link.getText().toString();
        String image = UUID.randomUUID().toString();
        int idfood = count + 1;
        if (!namefoods.isEmpty() && !ingredient.isEmpty() && !describle.isEmpty() && !link.isEmpty()) {
            Log.v(TAG, "index=" + image);
            //Toast.makeText(getActivity(), image + " " + namefoods + " " + ingredient + " " + describle + " " + link, Toast.LENGTH_SHORT).show();
            Food food = new Food();
            food.setNameFood(namefoods);
            food.setImageFood(image);
            food.setIngredient(ingredient);
            food.setDescrible(describle);
            food.setLinKVideo(link);
            food.setFoodId(idfood);
            addDatatoFirebase(namefoods, ingredient, describle, link, idfood);
            uploadImage();
//            foodList.add(food);
//            mainAdapter.notifyDataSetChanged();
            edt_namefood.setText("");
            edt_Ingredient.setText("");
            edt_Describle.setText("");
            edt_link.setText("");
            circleImageView.setImageResource(R.drawable.ic_food_placeholder);
        } else {
            Toast.makeText(getActivity(), "Need to fill information!", Toast.LENGTH_SHORT).show();
        }
    }

    private void addDatatoFirebase(String namefood, String Ingredient, String Describle, String link, int idFood) {
        food.setNameFood(namefood);
        food.setIngredient(Ingredient);
        food.setDescrible(Describle);
        food.setLinKVideo(link);
        food.setFoodId(idFood);
//        food.setImageFood(idFood);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.setValue(food);
                Toast.makeText(getActivity(), "data added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Fail to add data " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadImage() {
        if (filePath != null) {
            ProgressDialog progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            taskSnapshot -> {
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                            })
                    .addOnFailureListener(e -> {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    })
                    .addOnProgressListener(
                            taskSnapshot -> {
                                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                progressDialog.setMessage("Uploaded " + (int) progress + "%");
                            });
            setToFireStorage(filePath);
        }
    }

    private void setToFireStorage(Uri imageUri) {

        final StorageReference ImageReference = storageReference.child("112233");
        ImageReference.putFile(imageUri).addOnSuccessListener(taskSnapshot -> ImageReference.getDownloadUrl().addOnSuccessListener(uri -> {
            DatabaseReference db = databaseReference;
            db.child("newImage").setValue(uri.toString());
            // Toast.makeText(MainActivity.this, "Successfully added to real time", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show())).addOnFailureListener(e -> Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show());

    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }

    }

}