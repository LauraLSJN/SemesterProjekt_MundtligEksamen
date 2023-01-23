import javax.swing.*;
import java.awt.event.ActionListener;

public class LaunchGameLevel extends JPanel {
    private static Timer timer; //Java public klasse

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
