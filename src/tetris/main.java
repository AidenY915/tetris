package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;


class MainFrame extends JFrame implements Size{
	MainFrame() {
		super();
		setSize(Size.FRAME_WIDTH, Size.FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		JPanel gamePanel = new GamePanel();
		add(gamePanel,BorderLayout.CENTER);
		setVisible(true);
	}
}

public class main {
	public static void main(String args[]) {
		MainFrame mainFrame = new MainFrame();
	}
}
