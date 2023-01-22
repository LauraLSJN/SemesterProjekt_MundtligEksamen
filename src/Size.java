public class Size {

    private int foodObjectWidth;
    private int foodObjectHeight;
    private int displayWidth;
    private int displayHeight;
    private int playerObjectWidth;
    private int playerObjectHeight;

    public Size (){
        this.foodObjectHeight = 30;
        this.foodObjectWidth = 30;
        this.displayWidth = 700;
        this.displayHeight = 500;
        this.playerObjectWidth = 60;
        this.playerObjectHeight = 60;
    }

    public int getFoodObjectWidth() {
        return foodObjectWidth;
    }

    public int getFoodObjectHeight() {
        return foodObjectHeight;
    }

    public int getPlayerObjectWidth() {
        return playerObjectWidth;
    }

    public int getPlayerObjectHeight() {
        return playerObjectHeight;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

}