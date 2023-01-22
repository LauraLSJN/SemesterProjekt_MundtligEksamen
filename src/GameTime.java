import java.awt.*;
import java.text.AttributedString;
import java.text.DecimalFormat;

public class GameTime {
    public Position position;
    private int milliSecond;
    private int second;
    private int minute;
    private String dfSecond; //df = DecimalFormat
    private String dfMinute; //df = DecimalFormat
    private String dfMinSec; //df = DecimalFormat
    private DecimalFormat format = new DecimalFormat("00");
    private int fontSize = 15;
    private int xText = 15;
    private int width = 100;
    private int height = 50;
    GraphicalDrawing graphicalDrawing = new GraphicalDrawing();



    //TEKST
    private Font font = new Font("Monospaced", Font.BOLD, fontSize);



    private String textInImage;
    private AttributedString attributedText;
    private Size size;

    public GameTime(int minute, int second, int milliSecond) {
        this.minute = minute;
        this.second = second;
        this.milliSecond = milliSecond;
        size = new Size();
        position = new Position(size.getDisplayWidth() - width, 0);
    }


    public String getTextInImage() {
        return textInImage;
    }
    public void updateGameTime() {
        if (milliSecond == 0 && minute == 0 && second == 0) {
            milliSecond = 0;
            minute = 0;
            second = 0;

        } else {
            milliSecond--;
            if (milliSecond == -1) {
                milliSecond = 59;
                second--;
            }
            if (second == -1) {
                second = 59;
                minute--;
            }
        }
        dfSecond = format.format(second);
        dfMinute = format.format(minute);
        dfMinSec = format.format(milliSecond);
        this.textInImage = (dfMinute + ":" + dfSecond + ":" + dfMinSec);

    }

    public void stopTime() {
        this.minute = 0;
        this.second = 0;
        this.milliSecond = 0;
    }

  /*  public Image getSprite() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.PINK);
        graphics.fillRect(0, 0, width, height);
        setText(graphics);
        return image;
    }

    public void setText(Graphics2D graphics) {
        attributedText = new AttributedString(textInImage);
        attributedText.addAttribute(TextAttribute.FONT, font); //Font
        attributedText.addAttribute(TextAttribute.FOREGROUND, Color.WHITE); //Sættes til foreground + farve = hvid
        graphics.drawString(attributedText.getIterator(), xText, (height / 2) + 5); //Placeres i billede -> X og y kordinat er i henhold til image
    }*/



    public int getMilliSecond() {
        return milliSecond;
    }

    public int getSecond() {
        return second;
    }

    public int getMinute() {
        return minute;
    }

}
