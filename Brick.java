import java.awt.Graphics;
//import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
//import java.io.IOException;
import java.io.File;
//import java.awt.Color;
//import java.awt.Toolkit;

public class Brick extends Sprite 
{
	Image mario_brick;
	
	/*Brick(Model m)
	{
		super(m);
		if (mario_brick == null) 
		{
			try {
				mario_brick = ImageIO.read(new File("mario_brick.png"));
				}
			catch (Exception e) 
				{
				e.printStackTrace(System.err);
				System.exit(1);
				}
		}

	}
	*/

	Brick(Model m, String t, int xx, int yy, int ww, int hh)
	{
		super(m, t, xx, yy, ww, hh);
		if (mario_brick == null)
		{
			try {
				mario_brick = ImageIO.read(new File("mario_brick.png"));
				}
			catch (Exception e) 
				{
				e.printStackTrace(System.err);
				System.exit(1);
				}
		}
	}

	Brick(Json ob, Model m) 
	{
		super(ob, m);
		if (mario_brick == null) {
            try {
                mario_brick = ImageIO.read(new File("mario_brick.png"));
            } catch (Exception e) {
                e.printStackTrace(System.err);
                System.exit(1);
            }
        }
	}

	boolean isBrick() { return true; }
	boolean isCoinBlock() { return false; }
	boolean isCoin() { return false; }
	boolean isMario() { return false; }

	
	boolean update() { return true;	}

	void draw(Graphics g) 
	{		// Draw bricks
			g.drawImage(mario_brick,  this.x - model.scrollPos, this.y, this.w, this.h, null);
	}

	Json marshall() {
		Json ob = Json.newObject();
		ob.add("type", "Brick");
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		ob.add("vvel", vvel);
		return ob;
	}
	

	

}