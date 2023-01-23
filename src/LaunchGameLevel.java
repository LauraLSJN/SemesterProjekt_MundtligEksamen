import javax.swing.*;
import java.awt.event.ActionListener;

public class LaunchGameLevel extends JPanel {
    private static Timer timer; //Java public klasse

    //Timer er ikke nødvendig. Det anvendes kun til at dispose vores tidligere vindue, hvilket kan gøres ved at tage jFrame vindue
    // med osm paramter og derefter kalde window dispose, i start() metoderne
    //Derved vil vores konstruktør være tom

    public LaunchGameLevel(JFrame window) {
        ActionListener actionListener = pressed -> { //Når trykkes på knap, skal vinduet fjernes
            window.dispose();
        };
        timer = new Timer(200, actionListener);
    }

   public void start() { //JFrame window
       timer.start();
      // window.dispose();
        new Thread(new GameLoop(new Game(1))).start();//starter spillet på level 1

    }

    public void startLevel(int currentlevel){
        timer.start();
        new Thread(new GameLoop(new Game(currentlevel))).start();//starter et nyt game på level angivet i parameteren
    }
}
