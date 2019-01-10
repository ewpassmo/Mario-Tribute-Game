
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener {
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keySpace;
	int mouseDownX;
	int mouseDownY;

	View view;

	Controller(Model m) {
		model = m;
	}

	void setView(View v) {
		view = v;
	}

	public void actionPerformed(ActionEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		mouseDownX = e.getX();
		mouseDownY = e.getY();

		// model.setDestination(e.getX(), e.getY());
	}

	public void mouseReleased(MouseEvent e) {

        int x1 = mouseDownX;
        int x2 = e.getX();
        int y1 = mouseDownY;
        int y2 = e.getY();
        int left = Math.min(x1, x2);
        int right = Math.max(x1, x2);
        int top = Math.min(y1, y2);
        int bottom = Math.max(y1, y2);

        Model m = model;

        if (e.getButton() == MouseEvent.BUTTON1) // Left Click
        {
            model.addBrick(m, "Brick", left + model.scrollPos, top, right - left, bottom - top);
        }

        if (e.getButton() == MouseEvent.BUTTON3) // Right Click
        {
            model.addCoinBlock(m, "CoinBlock", left + model.scrollPos, top, 85, 85);
        }
        /*
        if (e.getButton() == MouseEvent.BUTTON2) // Middle Click
        {
            model.sprites.remove(this);
        }
        */
    }

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			keyRight = true;
			break;
		case KeyEvent.VK_LEFT:
			keyLeft = true;
			break;
		case KeyEvent.VK_UP:
			keyUp = true;
			break;
		case KeyEvent.VK_DOWN:
			keyDown = true;
			break;
		case KeyEvent.VK_S:
			model.save("map.json");
			break;
		case KeyEvent.VK_L:
			model.load("map.json");
			break;
		case KeyEvent.VK_SPACE:
			keySpace = true;
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			keyRight = false;
			break;
		case KeyEvent.VK_LEFT:
			keyLeft = false;
			break;
		case KeyEvent.VK_UP:
			keyUp = false;
			break;
		case KeyEvent.VK_DOWN:
			keyDown = false;
			break;
		case KeyEvent.VK_SPACE:
			keySpace = false;
			break;
		case KeyEvent.VK_C: 
			model.clear("map.json"); 
			break;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	void update() {
		model.mario.pastMario();

		if (keyRight) {
			 model.mario.x += 10;
		}
		if (keyLeft) {
			 model.mario.x -= 10;
		}
		/*
		 * if(keyDown) model.dest_y++; if(keyUp) model.dest_y--;
		 */
		if (keySpace) {
			if (model.mario.frame_counter < 5) {
 				model.mario.vvel = -23.7;
 				/*if(model.mario.stood_flag == 0)
					{
						//model.mario.stood_flag = 1;
						model.mario.jump_counter++;
						System.out.println("stood_flag " + model.mario.stood_flag);
					}*/
			}
		}
	}
}
