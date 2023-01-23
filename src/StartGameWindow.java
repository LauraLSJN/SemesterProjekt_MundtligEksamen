
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class StartGameWindow {
    private Size size;

    //Laver Start knap
    public void StartWindow() {
        size = new Size();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(size.getDisplayWidth(), size.getDisplayHeight())); //Sætter vinduet størrelse
        window.setTitle("MyFoodSolver");//titel på vinduet
        LaunchGameLevel launchGameLevel = new LaunchGameLevel(window);
        window.add(launchGameLevel);

        //Knap
        JButton startBtn = new JButton("Klik her for at starte MyFoodSolver");//Tekst i knappen
        startBtn.setFont(new Font("Monospaced", Font.BOLD, 25));//Font på teksten
        startBtn.setForeground(Color.PINK);//Farve på tekst
        startBtn.setPreferredSize(new Dimension(size.getDisplayWidth(), size.getDisplayHeight()));//Størrelse på knappen -> Hele GUI
        startBtn.addActionListener(e -> launchGameLevel.start());//Actionlistener når knappen trykkes skal spillet starte -> metode start i LaunchGameLevel

        window.add(startBtn, BorderLayout.PAGE_START);//tegner vores knap med alt det forrige indhold som str, farve og font
        window.pack();//tegner alt indhold
        window.setLocationRelativeTo(null); //Placerer GUI i midten af skræmen, når den er null ellers fra venstra top på computer skærm
        window.setVisible(true);//Gør vinduet synlig

    }

}
