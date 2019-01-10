import javax.swing.JPanel;
import java.awt.Graphics;
//import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
//import java.io.IOException;
import java.io.File;
//import javax.swing.JButton;
import java.awt.Color;
import java.awt.Image;
import java.util.Iterator;

class View extends JPanel {
	Model model;
	Image background;

	View(Controller c, Model m) {
		model = m;

		if (background == null) {
			try {
				background = ImageIO.read(new File("mario_background.png"));
			} catch (Exception e) {
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
	}

	public void paintComponent(Graphics g) {
		// Clear screen
		g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// Draw Background
		g.drawImage(background, -(model.scrollPos)/2 - 222, 0, null);

		// Draw ground
		g.setColor(new Color(17, 212, 69));
		g.fillRect(0, 595, 10000, 595);

		for (int i = 0; i < model.sprites.size(); i++) 
		{
			Sprite s = model.sprites.get(i);
			s.draw(g);
		}
	}
}
