package eatoday.com.model;

public class Food {
    private int Foodid;
    private String nameFood;
    private String linKVideo;
    private String describle;
    private String imageFood;

    public Food() {

    }

    public Food(int Foodid,String nameFood, String linKVideo, String describle, String imageFood) {
        this.Foodid = Foodid;
        this.nameFood = nameFood;
        this.linKVideo = linKVideo;
        this.describle = describle;
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
    public int getFoodid() {
        return Foodid;
    }

    public void setFoodid(int foodid) {
        Foodid = foodid;
    }


}
