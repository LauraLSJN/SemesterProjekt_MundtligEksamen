import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

//Game klasse - Spillets logik
public class Game {
    private Random random = new Random();
    private Display display;
    private ArrayList<GameObject> gameObject;//ArrayList af Objekter = GameObject
    private ArrayList<ShoppingBasket> shoppingBaskets;
    private ArrayList<GameTime> gameTime;
    private Input input; //Input fra brugeren
    private Size size;
    private boolean stopDropFoodObjects;
    private boolean won;
    private boolean lost;
    private int currentLevel;

    public Game(int currentLevel) {
        this.currentLevel = currentLevel;
        this.won = false;
        this.lost = false;
        this.stopDropFoodObjects = false;

        size = new Size();
        input = new Input();
        display = new Display(input);

        //Tid
        gameTime = new ArrayList<>();
       // gameTime.add(new GameTime());

        //Shoppingkurven
        shoppingBaskets = new ArrayList<>();

        //Food og Player
        gameObject = new ArrayList<>();

        //Tilføjer Levels
        addLevels();

        //Stopper programmet når vinduet lukker
        display.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Anvendes til kontrol
        System.out.println("GameObject Størrelse: " + gameObject.size());
        System.out.println(getGameObject());
    }

    //Metode som opdateres med framerate
    public void updateGame() {
        gameObject.forEach(gameObject -> gameObject.updateGameObject());
        detectionOutOfDisplay();
        collision();
        checkGameState();
        dropFoodObjects();
        gameTime.forEach(gameTime -> gameTime.updateGameTime());

    }

    //Metode der styrer levels
    public void addLevels() {
        if (currentLevel == 1) {
            gameTime.add(new GameTime(0,30,0));
            shoppingBaskets.add(new ShoppingBasket(5));
            gameObject.add(new PlayerObject(new Player(input), 1,1));
            gameObject.add(new PlayerObject(new Player(input), 1,2));
            addFoodObjects();

        } else if (currentLevel == 2) {
            gameTime.add(new GameTime(1,0,0));
            shoppingBaskets.add(new ShoppingBasket(30));
            gameObject.add(new PlayerObject(new Player(input), 2,1));
            gameObject.add(new PlayerObject(new Player(input), 2,2));
            addFoodObjects();

        } else if (currentLevel == 3) {
            gameTime.add(new GameTime(2,0,0));
            shoppingBaskets.add(new ShoppingBasket(50));
            gameObject.add(new PlayerObject(new Player(input), 3,1));
            gameObject.add(new PlayerObject(new Player(input), 3,2));
            addFoodObjects();

        } else if (currentLevel == 4) {
            gameTime.add(new GameTime(2,0,0));
            shoppingBaskets.add(new ShoppingBasket(100));
            gameObject.add(new PlayerObject(new Player(input), 4,1));
            gameObject.add(new PlayerObject(new Player(input), 4,2));
            addFoodObjects();

        } else if (currentLevel == 5) {
            gameTime.add(new GameTime(2,0,0));
            shoppingBaskets.add(new ShoppingBasket(150));
            gameObject.add(new PlayerObject(new Player(input), 5,1));
            gameObject.add(new PlayerObject(new Player(input), 5,2));
            addFoodObjects();
        }
    }

    //Metode der tilføjer foodObjects til gameObject arraylisten
    public void addFoodObjects() {
        if (currentLevel == 1) {
            gameObject.add(new FoodObjects(1, false, false));
        } else if (currentLevel == 2) {
            gameObject.add(new FoodObjects(2, true, false));
        } else if (currentLevel == 3) {
            gameObject.add(new FoodObjects(3, true, true));
        } else if (currentLevel == 4) {
            gameObject.add(new FoodObjects(4, true, true));
        } else if (currentLevel == 5) {
            gameObject.add(new FoodObjects(5, true, true));
            gameObject.add(new FoodObjects(1, true, true));
        }
    }


    //Metode til at generere sandsynlighed vha. random, for hvornår foodobjecs skal tilføjes
    public void dropFoodObjects() {
        int randomNumber = random.nextInt(2000); //Random tal
        if (this.stopDropFoodObjects == false && randomNumber <= 25) { //Hvis stopDropFoodObject er falsk og randomNumber er mindre end eller lig 25, tilføjes nye foodObjects
            addFoodObjects();
        }
    }

    //Metode der fjerne alle objekter i arrayListen gameObjects
    public void removeGameObjects() {
        for (int i = 0; i < gameObject.size(); i++) {
            gameObject.remove(i); //Fjerne objekter i gameObject arraylisten
            gameTime.get(0).stopTime();
        }
    }

    //Metode tjekker hvorvidt level er vundet eller tabt
    public void checkGameState() {
        if (shoppingBaskets.get(0).getCollectedFood() == shoppingBaskets.get(0).getMaxValue() && this.stopDropFoodObjects == false) {
            this.stopDropFoodObjects = true;
            removeGameObjects();
            setWon(true);
            currentLevel++;
            display.levelWindow(currentLevel, true);
            display.dispose();
        }

        if ((gameTime.get(0).getMilliSecond() == 0) && (gameTime.get(0).getSecond() == 0) && (gameTime.get(0).getMinute() == 0) && (this.stopDropFoodObjects == false)) {
            this.stopDropFoodObjects = true;
            removeGameObjects();
            setLost(true);

            if (isWon() == false && isLost() == true) {
                display.levelWindow(currentLevel, false);
                display.dispose();
            }
        }
    }

    //Metode til hvorvidt firkanterne på displayet rammer hinanden
    public void collision() {
        for (int x = 2; x < gameObject.size(); x++) {
            if ((gameObject.get(x).getPosition().getX() >= (gameObject.get(0).getPosition().getX() - 30)) // food x >= player x - 30
                    && (gameObject.get(x).getPosition().getX() <= (gameObject.get(0).getPosition().getX() + size.getPlayerObjectWidth() + 20)) //food x <= player x+ size + 20
                    && ((gameObject.get(x).getPosition().getY() + size.getFoodObjectHeight()) >= gameObject.get(0).getPosition().getY() + 5) // food y + size >= player y +5
                    && ((gameObject.get(x).getPosition().getY() + size.getFoodObjectHeight()) <= (gameObject.get(0).getPosition().getY() + size.getPlayerObjectHeight() + 20)) //food y <= player y + size + 20
                    && ((gameObject.get(x).getPosition().getX() + size.getFoodObjectWidth()) >= (gameObject.get(0).getPosition().getX() - 35)) //food x + size >= player x -35
                    && ((gameObject.get(x).getPosition().getX() + size.getFoodObjectWidth()) <= (gameObject.get(0).getPosition().getX() + size.getFoodObjectWidth() + 60)) //food x + size <= player x + size + 60
                ||
                    //Player 2
                        (gameObject.get(x).getPosition().getX() >= (gameObject.get(1).getPosition().getX() - 30))
                    && (gameObject.get(x).getPosition().getX() <= (gameObject.get(1).getPosition().getX() + size.getPlayerObjectWidth() + 20))
                    && ((gameObject.get(x).getPosition().getY() + size.getFoodObjectHeight()) >= gameObject.get(1).getPosition().getY() + 5) // food y + size >= player y +5
                    && ((gameObject.get(x).getPosition().getY() + size.getFoodObjectHeight()) <= (gameObject.get(1).getPosition().getY() + size.getPlayerObjectHeight() + 20)) //food y <= player y + size + 20
                    && ((gameObject.get(x).getPosition().getX() + size.getFoodObjectWidth()) >= (gameObject.get(1).getPosition().getX() - 35)) //food x + size >= player x -35
                    && ((gameObject.get(x).getPosition().getX() + size.getFoodObjectWidth()) <= (gameObject.get(1).getPosition().getX() + size.getFoodObjectWidth() + 60)) //food x + size <= player x + size + 60


            ) {
                //ADD
                shoppingBaskets.get(0).addCollectedFood(gameObject.get(x).price.getPrice());
                
                //Sletter det ramte foodObject
                gameObject.remove(x); //Fjerner objektet -> Der bliver ramt
            }
        }
    }

    //Metode fjerner gameObjects der er udenfor display
    public void detectionOutOfDisplay() {
        for (int i = 2; i < gameObject.size(); i++) {
            if (gameObject.get(i).getPosition().getY() >= gameObject.get(0).getPosition().getY() + size.getPlayerObjectHeight()) { //food y >= player y + player height
                gameObject.remove(i);
            }
        }
    }

    //Metode til at lave currentlevel boksen
    public void currentLevelDisplay(Graphics g) {
        int tekstBoksWidth = 125;
        int tekstBoksHeight = 50;
        int tekstBoksX = 0;
        int tekstBoksY = 110;
        int fontSize = 15;
        Font font = new Font("Monospaced", Font.PLAIN, fontSize);
        g.setFont(font);
        g.setColor(Color.PINK);
        g.fillRect(tekstBoksX, tekstBoksY, tekstBoksWidth, tekstBoksHeight);
        g.setColor(Color.WHITE);
        g.drawString("Level: ", tekstBoksX + 5, tekstBoksY + 20);
        g.drawString(String.valueOf(currentLevel), tekstBoksX + 5 + fontSize, tekstBoksY + 40);
    }

    public void renderGame() {
        display.renderDisplay(this);
    }

    //Getters og Setters
    public ArrayList<GameObject> getGameObject() {
        return gameObject;
    }

    public ArrayList<ShoppingBasket> getShoppingBaskets() {
        return shoppingBaskets;
    }

    public ArrayList<GameTime> getGameTime() {
        return gameTime;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    public boolean isLost() {
        return lost;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }
}
