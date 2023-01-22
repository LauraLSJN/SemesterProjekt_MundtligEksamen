import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener {

    private boolean[] pressed;

    public Input(){
        pressed = new boolean[255];
    }

    public boolean isPressed(int keyCode){
        return pressed[keyCode];
    }

    //KeyEvent e Repræsentere en int (nummer). Kigger på indeks i keyPressed og se om det er pressed eller ikke (boolean)
    @Override
    public void keyTyped(KeyEvent e) {
    }

    //Vi har trykket noget -> det har en keycode (keyEvent e) -> get the slot of our boolean array and say that this slot now holds true
    @Override
    public void keyPressed(KeyEvent e) {
        pressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed[e.getKeyCode()] = false;
    }
}
