package eatoday.com.model;

import android.net.Uri;

public class Food {
    public static int count = 0;
    private int FoodId;
    private String nameFood;
    private String linKVideo;
    private String describle;
    private String imageFood;
    private String ingredient;

    public Food() {
        FoodId +=count;
    }

    public Food(int FoodId,String nameFood,String ingredient, String linKVideo, String describle, String imageFood) {
        this.FoodId = FoodId;
        this.nameFood = nameFood;
        this.linKVideo = linKVideo;
        this.describle = describle;
        this.ingredient = ingredient;
        this.imageFood = imageFood;
    }
    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getLinKVideo() {
        return linKVideo;
    }

    public void setLinKVideo(String linKVideo) {
        this.linKVideo = linKVideo;
    }

    public String getDescrible() {
        return describle;
    }

    public void setDescrible(String describle) {
        this.describle = describle;
    }

    public String getImageFood() {
        return imageFood;
    }

    public void setImageFood(String imageFood) {
        this.imageFood = imageFood;
    }
    public int getFoodId() {
        return FoodId;
    }

    public void setFoodId(int foodId) {
        FoodId = foodId;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }


}
