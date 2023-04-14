package au.david.church.subtitle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DrawText {



    public static void draw(List<String> messages) throws IOException {
        int line = messages.size();
        int yOffSet = 50;
        int xOffSet = 50;
        int maxCharacter = 35;
        int fontSize = 44;
        int totalWidth = fontSize * maxCharacter + 2 * xOffSet;
        BufferedImage bufferedImage = new BufferedImage(totalWidth, yOffSet+ line * fontSize,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, totalWidth, yOffSet+ line * fontSize);
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        for (int i=0;i<line;i++) {
            try {
                String message = messages.get(i);
                int sizeWidth = (int)Math.floor(totalWidth-message.length()*fontSize)/2;
                byte[] utf8 = message.getBytes("UTF-8");
                String data = new String(utf8);
                g.drawString(data, sizeWidth+xOffSet, i*fontSize+yOffSet+fontSize/2);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        g.dispose();
        ImageIO.write(bufferedImage, "JPG", new File("test2.jpg"));

    }
}
