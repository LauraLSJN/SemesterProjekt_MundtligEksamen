import java.awt.event.KeyEvent;

public class Player implements Controller{
    private Input input;

    public Player(Input input){
        this.input=input;
    }

    @Override
    public boolean isRequestiongLeft() {
        return input.isPressed(KeyEvent.VK_LEFT); // Venstre pil (Tastatur)
    }

    @Override
    public boolean isRequestiongRight() {
        return input.isPressed(KeyEvent.VK_RIGHT); // Højre pil (Tastatur)
    }
}
