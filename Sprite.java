import java.awt.Graphics;


abstract class Sprite extends Object {
	int x, y, w, h;
	String type;
	Model model;
	double vvel;
	double hvel;

	//abstract boolean doesCollide(Sprite s);
	abstract void draw(Graphics g);
	abstract boolean update();
	abstract Json marshall();
	abstract boolean isBrick();
	abstract boolean isCoinBlock();
	abstract boolean isCoin();
	abstract boolean isMario();
	
	Sprite(Model m) 
	{
		model = m;
	}
	
	Sprite(Model m, String t, int xx, int yy, int ww, int hh)
	{
		model = m;
		type = t;
		x = xx;
		y = yy;
		w = ww;
		h = hh;
		vvel = 0;
	}
	
	Sprite(Json ob, Model m)
	{
		model = m;
		type = ob.getString("type");
        x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
		vvel = ob.getDouble("vvel");
	}

	Sprite(Model m, String t, int xx, int yy, double hh, double vv)
	{
		model = m;
		type = t;
		x = xx;
		y = yy;
		hvel = hh;
		vvel = vv;
	}
	
	public boolean doesCollide(Sprite that)
	{
		// Mario Sides 		 : LEFT this.x RIGHT this.x + this.w TOP this.y BOT this.y + this.h
		// Brick/Block Sides : LEFT that.x RIGHT that.x + that.w TOP that.y BOT that.y + that.h

		if (this.x + this.w <= that.x)  	// did not collide from right
			return false;

		if (this.x >= that.x + that.w)		// did not collide from left
			return false;

		if (this.y + this.h <= that.y)		// did not collide from top
			return false;

		if (this.y >= that.y + that.h)		// did not collide from bottom
			return false;
		return true;						// collided
	}


	void getOut(Sprite s) 
	{
		if ((this.x + this.w > s.x) && (model.mario.prev_x + this.w <= s.x)) // entered from left
            this.x = model.mario.prev_x;

		else if ((this.x < s.x + s.w) && (model.mario.prev_x >= s.x + s.w))    // entered from right
         	this.x = model.mario.prev_x;

		else if ((this.y + this.h >= s.y) && (model.mario.prev_y + this.h <= s.y)) // on top
		{
			this.y = model.mario.prev_y;
			this.vvel = 0;
			model.mario.frame_counter = 0;
			model.mario.coin_flag = 0;  
		}
		else if ((this.y < s.y + s.h) && (model.mario.prev_y >= s.y + s.h)) // under
		{
			if(s.isCoinBlock())
			{
				CoinBlock cb = (CoinBlock)s;
				cb.spawnCoin();
			}
				this.y = model.mario.prev_y;
				this.vvel = 0;
		}
	}
}
