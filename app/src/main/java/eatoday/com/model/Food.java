package eatoday.com.model;

import android.net.Uri;

public class Food {
//    private String FoodId;
    private String foodName;
    private String linKVideo;
    private String describle;
    private String foodImage;
    private String ingredient;

    public Food() {
    }

    public Food(String foodName,String ingredient, String linKVideo, String describle, String foodImage) {
     //   this.FoodId = FoodId;
        this.foodName = foodName;
        this.linKVideo = linKVideo;
        this.describle = describle;
        this.ingredient = ingredient;
        this.foodImage = foodImage;
    }
    public String getNameFood() {
        return foodName;
    }

    public void setNameFood(String foodName) {
        this.foodName = foodName;
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
        return foodImage;
    }

    public void setImageFood(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }


}
