import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import java.util.Random;

public class CoinBlock extends Sprite 
{
	static Image[] coin_block_image = null;
	int coinsRemaining;
	static Random r = new Random();

	CoinBlock(Model m, String t, int xx, int yy, int ww, int hh)
	{
		super(m);
			if(coin_block_image == null)
		{
			coin_block_image = new Image[2];
			try
			{
					coin_block_image[0] = ImageIO.read(new File("coin_block_contains.png"));
					coin_block_image[1] = ImageIO.read(new File("coin_block_empty.png"));
			} 
			catch(Exception e) 
			{
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
		
		type = t;
		x = xx;
		y = yy;
		w = ww;
		h = hh;
		vvel = 0;
		coinsRemaining = 5;
	}

	CoinBlock(Json ob, Model m)
	{
		super(ob, m);
		
		if(coin_block_image == null)
		{
			coin_block_image = new Image[2];
			try
			{
					coin_block_image[0] = ImageIO.read(new File("coin_block_contains.png"));
					coin_block_image[1] = ImageIO.read(new File("coin_block_empty.png"));
			} 
			catch(Exception e) 
			{
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
		coinsRemaining = 5;
    }

	Json marshall()
	{
         Json ob = Json.newObject();
         ob.add("type", "CoinBlock");
         ob.add("x", x);  
		 ob.add("y", y);
		 ob.add("w", w);
		 ob.add("h", h);
		 ob.add("vvel", vvel);
         return ob;
	}

	void draw(Graphics g)
	{
	    if (coinsRemaining > 0)
		g.drawImage(coin_block_image[0], this.x - model.scrollPos, this.y, this.w, this.h, null);
       else
        g.drawImage(coin_block_image[1], this.x - model.scrollPos, this.y, this.w, this.h, null);
    }

	boolean update()
	{
		return true;
	}

	void spawnCoin()
	{
		double horvel = r.nextDouble() * 16 - 8;
		double vervel = -22.22;

        if ((coinsRemaining > 0) && (model.mario.isNotGreedy()))        {
        coinsRemaining--;
		model.mario.coin_flag = 1;
    	Model m = model;
		Coin c = new Coin(m,"Coin", this.x, this.y, horvel, vervel);
		model.sprites.add(c);
        }
	}

	boolean isBrick() { return false; }
	boolean isCoinBlock() { return true; }
	boolean isCoin() { return false; }
	boolean isMario() { return false; }


}