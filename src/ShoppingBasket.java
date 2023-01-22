import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedString;

public class ShoppingBasket {
    public Position position;
    private int maxValue;
    private String maxValueString;
    private AttributedString attributedText;
    private int collectedFood;

    //Grafiske variabler
    private int fontSize = 15;
    private int shoppingBasketWidth = 125;
    private int shoppingBasketHeight = 105; //I alt er tekst 95 pixel. For at få 5 pixel i top og bund sættes height til 105
    private int textX = 5;
    private int textY = 25;
    private int configurationGraphic = 5;
    private Font font = new Font("Monospaced", Font.PLAIN, fontSize);

    public ShoppingBasket(int amount) {
        position = new Position(0, 0);
        this.maxValue = amount;
        maxValueString = String.valueOf(maxValue); //Laver maxValue om til String
    }


    //Metode til at tilføje værdien i parameteren til collectedFood
    public void addCollectedFood(int foodPrice) {
        this.collectedFood = this.collectedFood + foodPrice; //Opdaterer nowCollectedFood
    }

    //Tegner info om shoppingkurven
    public Image getSprite() {
        BufferedImage image = new BufferedImage(shoppingBasketWidth, shoppingBasketHeight, BufferedImage.TYPE_INT_RGB); //Laver Buffered billede
        Graphics2D graphics = image.createGraphics(); //Instans af Graphics2D klasse så vi kan bruge klassen
        graphics.setColor(Color.PINK); //Farve på billedet
        graphics.fillRect(0, 0, shoppingBasketWidth, shoppingBasketWidth); //fylder rect - Placeres ovenpå Image

        //Tegner tekst i billede
        setText(graphics, "Shoppingkurv:", textX, textY);
        setText(graphics, "Indsamlet:", textX, textY * 2 + fontSize + configurationGraphic);
        setText(graphics, maxValueString, textX + fontSize, textY + fontSize + configurationGraphic); //Tegner maxValue
        setText(graphics, String.valueOf(collectedFood), textX + fontSize, textY * 3 + fontSize + configurationGraphic); //Tegner ShoppingBasket CollectedFood værdi

        return image;
    }

    //Tekst i firkant
    public void setText(Graphics2D graphics, String text, int x, int y) {
        attributedText = new AttributedString(text);
        attributedText.addAttribute(TextAttribute.FONT, font); //Font
        attributedText.addAttribute(TextAttribute.FOREGROUND, Color.WHITE); //Sættes til foreground + farve = hvid
        graphics.drawString(attributedText.getIterator(), x, y); //Placeres i billede -> X og y kordinat er i henhold til image
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getCollectedFood() {
        return collectedFood;
    }
}
