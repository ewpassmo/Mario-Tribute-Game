import java.util.ArrayList;

class Model {
	int scrollPos;
	ArrayList<Sprite> sprites;
	Mario mario;
	
	//Model model;

	Model() 
	{
		mario = new Mario(this);
		sprites = new ArrayList<Sprite>();
		sprites.add(mario);
	}

	public void update() 
	{
		for (int i = 0; i < sprites.size(); i++) 
		{
			Sprite s = sprites.get(i);
			boolean Alive = s.update();
			if(!Alive)
			{
				sprites.remove(i);
				i--;
			}
		} 
	}

	void addBrick(Model m, String t, int x, int y, int w, int h)
	{
		Sprite s = new Brick(m, t, x, y, w, h);
		sprites.add(s);
	}

	void addCoinBlock(Model m, String t, int x, int y, int w, int h)
	{
		Sprite s = new CoinBlock(m, t, x, y, w, h);
		sprites.add(s);
	}
	
	void unmarshall(Json ob) {
		Sprite reset_mario = sprites.get(0);
		sprites.clear();
		sprites.add(reset_mario);

		Json json_sprites = ob.get("sprites");
		for (int i = 0; i < json_sprites.size(); i++) 
		{
			Json j = json_sprites.get(i);
			String str = j.getString("type");
			if (str.equals("Mario"))
			{
				//sprites.add(new Mario(j, this));
				//	System.out.println("Unmarshall mario ");
			}
			else if (str.equals("Brick"))
			{
				sprites.add(new Brick(j, this));
				//System.out.println("Unmarshall brick ");

			}
			else if (str.equals("CoinBlock"))
			{
				sprites.add(new CoinBlock(j, this));
			//	System.out.println("Unmarshall Coin Block ");
			}	
		}
	}

	Json marshall() 
	{
		Json ob = Json.newObject();
		Json json_sprites = Json.newList();

		ob.add("sprites", json_sprites);
		for (int i = 0; i < sprites.size(); i++) {
			Sprite s = sprites.get(i);
			Json j = s.marshall();
			json_sprites.add(j);
		}
		return ob;
	}

	void save(String filename) {
		Json ob = marshall();
		ob.save(filename);
		System.out.println("Map saved ");
	}

	void load(String filename) {
		Json ob = Json.load(filename);
		unmarshall(ob);
		System.out.println("Map loaded " + filename);
	}
	
	void clear(String filename)
	{
		sprites.clear();
	}
}