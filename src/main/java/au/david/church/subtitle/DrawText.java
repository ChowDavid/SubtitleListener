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
        int fontSize = 44;
        BufferedImage bufferedImage = new BufferedImage(1440, yOffSet*2+ line * fontSize,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufferedImage.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 1440, yOffSet*2+ line * fontSize);
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
        for (int i=0;i<line;i++) {
            try {
                String message = messages.get(i);
                byte[] utf8 = message.getBytes("UTF-8");
                String data = new String(utf8);
                g.drawString(data, 0+xOffSet, i*fontSize+yOffSet+fontSize/2);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        g.dispose();
        ImageIO.write(bufferedImage, "JPG", new File("test2.jpg"));

    }
}
