import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerObject extends GameObject {
    private Controller controller;
    private int speedX;
    private int speed;
    private int oldPosX;
    private int player;
    public Image imgPlayer = Toolkit.getDefaultToolkit().getImage("Ressourcer/shoppingKurv3.png");

    public PlayerObject(Controller controller, int speed, int player) {
        this.player = player;
        this.controller = controller;
        this.speed = speed;
        if (player == 1) {
            position = new Position(350, 440); //Startposition for player 1
        }

        if (player == 2){
            position = new Position(250,440); //Starposition for player 2
        }

    }

    @Override
    public void updateGameObject() {
        speedX = 0;

        oldPosX = position.getX(); //Gemmer tidligere x koordinat

        //Player 1
            if (controller.isRequestiongLeft() && player==1){
                speedX = speedX - this.speed;
            }
            if (controller.isRequestiongRight() && player==1) {
                speedX = speedX + this.speed;
            }

        //Player 2
            if (controller.isRequestiongA() && player==2){
                speedX = speedX - this.speed;
            }
             if (controller.isRequestiongD() && player==2){
            speedX = speedX + this.speed;
        }


        position.setX(oldPosX+ speedX); //Sætter ny x-koordinat
    }


    @Override
    public Image getSprite() {
        BufferedImage image = new BufferedImage(size.getPlayerObjectWidth(), size.getPlayerObjectHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(imgPlayer,0,0,size.getPlayerObjectWidth(), size.getPlayerObjectHeight(),null);
        return image;
    }
}
