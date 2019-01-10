import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame {
	Model model;
	View view;
	Controller controller;

	public Game() {
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		view.addMouseListener(controller);
		this.setTitle("This is just a (Mario) Tribute, yyeaaa");
		this.setSize(900, 700);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(controller);
		model.load("map.json");
	}

	public void run() {
		while (true) {
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// Go to sleep for 40 miliseconds
			try {
				Thread.sleep(40);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
			// System.out.println("hi"); // remove this line
		}
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.run();
	}
}
