import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Coin extends Sprite
{
	Image coin_image = null;
/*
	Coin(Model m)
	{
		super(m);
		if(coin_image == null)
		{
			try
			{
				coin_image = ImageIO.read(new File("64_bit_coin.png"));
			}
			catch(Exception e) 
			{
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
	}
*/
	Coin(Model m, String t, int xx, int yy, double hh, double vv)
	{
		super(m, t, xx, yy, hh, vv);
		if(coin_image == null)
		{
			try
			{
					coin_image = ImageIO.read(new File("64_bit_coin.png"));
			}
			catch(Exception e) 
			{
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}

		w = 85;
		h = 95;
	}

	/*
	Coin(Json ob, Model m)
	{
		 super(ob, m);
			if (coin_image == null) 
			{
				try
				{
					coin_image = ImageIO.read(new File("64_bit_coin.png"));
				} 
				catch (Exception e) 
				{
					e.printStackTrace(System.err);
					System.exit(1);
				}
			}
			w = 85;
			h = 95;
     	}
*/

	Json marshall()
	{
         Json ob = Json.newObject();
         ob.add("type", type);
         ob.add("x", x);  
		 ob.add("y", y);
		 ob.add("w", w);
		 ob.add("h", h);
		 ob.add("vvel", vvel);
         return ob;
	}
 
	void draw(Graphics g)
	{
	    // Draw Coin
		g.drawImage(coin_image, this.x - model.scrollPos, this.y, this.w, this.h, null);
	}

	boolean update()
	{

        x += hvel;
        // Gravity
        vvel += 3.14159;
        y += vvel;

		if(y>700)
        {
			return false;
        }
		else
		return true;
	}
	
	boolean isBrick() 			 { return false; }
	boolean isCoinBlock() 		 { return false; }
	boolean isCoin()			 { return true; }
	boolean isMario()			 { return false; }
}
	