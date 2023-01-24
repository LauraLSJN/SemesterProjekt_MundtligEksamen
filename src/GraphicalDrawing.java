import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedString;

public class GraphicalDrawing {
    private AttributedString attributedText;
    private int fontSize = 15;
    private Font font = new Font("Monospaced", Font.PLAIN, fontSize);


    public GraphicalDrawing(){

    }

    public Image getSpriteTest(int width, int height, Color farve, String textInImage, int textX, int textY){
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(farve);
        graphics.fillRect(0,0,width,height);
        attributedText = new AttributedString(textInImage);
        attributedText.addAttribute(TextAttribute.FONT, font);
        attributedText.addAttribute(TextAttribute.FOREGROUND, Color.WHITE);
        //Skal finde ud af hvordan x og y gøres generiske alt efter hvilken klasse element der skal tegnes
        graphics.drawString(attributedText.getIterator(), textX, textY);
        return image;

    }


    public Image getSpriteTest2(int x, int y, int width, int height, Color farve){
        //Kode
        return null;
    }

    public void setTextTest2(Graphics2D graphics, String textInImage, int x, int y){

    }

    public void setTextTest(Graphics2D graphics, String textInImage, int x, int y){
        attributedText = new AttributedString(textInImage);
        attributedText.addAttribute(TextAttribute.FONT, font);
        attributedText.addAttribute(TextAttribute.FOREGROUND, Color.WHITE);
        graphics.drawString(attributedText.getIterator(), x, y);


    }

}
