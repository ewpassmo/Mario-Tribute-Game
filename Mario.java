import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
//import java.io.IOException;
import java.io.File;
//import java.awt.Color;
//import java.awt.Toolkit;


class Mario extends Sprite 
{
	// Previous coordinates
	int prev_x;
	int prev_y;
	
	// Image size
	//int h = 95;
	//int w = 50;
	
	// Current View Position
	//int marioPos;

	//Model model;
	int coin_flag = 0;
	int frame_counter;
	int jump_counter;
	static Image[] mario_images = null;

	int marioFrame;
	
	Mario(Model m)
	{
		super(m);
		if (mario_images == null) 
		{
			mario_images = new Image[5];
			try 
			{
				mario_images[0] = ImageIO.read(new File("mario1.png"));
				mario_images[1] = ImageIO.read(new File("mario2.png"));
				mario_images[2] = ImageIO.read(new File("mario3.png"));
				mario_images[3] = ImageIO.read(new File("mario4.png"));
				mario_images[4] = ImageIO.read(new File("mario5.png"));
			} 
			catch (Exception e) 
			{
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}	
		type = "Mario";
		x = 0;
		y = 0;
		w = 50;
		h = 95;
		vvel = 20;
		System.out.println("Mario model ");

	}
	
	
	boolean isNotGreedy()
	{
		if(coin_flag == 1)
		return false;
		else
		return true;
	}
	/*
	Mario(Json ob, Model m)
	{
		super(ob, m);
		if (mario_images == null)
		{
			mario_images = new Image[5];
			try
			{
				mario_images[0] = ImageIO.read(new File("mario1.png"));
				mario_images[1] = ImageIO.read(new File("mario2.png"));
				mario_images[2] = ImageIO.read(new File("mario3.png"));
				mario_images[3] = ImageIO.read(new File("mario4.png"));
				mario_images[4] = ImageIO.read(new File("mario5.png"));
			}
			catch (Exception e)
			{
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
		System.out.println("Mario Json object and model ");
	}
	*/

	void pastMario() {
		prev_x = model.mario.x;
		prev_y = model.mario.y;
	}

	void draw(Graphics g)
	{
		// Mario's Frame for walking through pictures
		marioFrame = Math.abs(model.scrollPos / 17) % 5;
		// Draw Mario
		g.drawImage(mario_images[marioFrame], model.mario.x - model.scrollPos, y, w, h, null);
	}

	boolean isBrick() { return false; }
	boolean isCoinBlock() { return false; }
	boolean isCoin() { return false; }
	boolean isMario() { return true; }

	boolean update()
	{
	    model.scrollPos = x - 222;
        // Gravity
        vvel += 3.14159;
        y += vvel;
        frame_counter++;
	for (int i = 0; i < model.sprites.size(); i++)
	{

	if(y < 500)
		{
		Sprite s = model.sprites.get(i);
			if (s.isBrick())
			{
			 if (this.doesCollide(s))
				{
                    this.getOut(s);
                    if (this.doesCollide(s))
                        System.out.println("get outta here" + s);
                }
			}
			if (s.isCoinBlock())
			{
			 if (this.doesCollide(s))
			    {
					this.getOut(s);
                    if (this.doesCollide(s))
                        System.out.println("cmon now, get out" + s);
                }
			}
 		}
 	else
		{
			frame_counter = 0;
			coin_flag = 0;
			vvel = 0;
			return true;
		}
	}
	return true;
	}

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
}
