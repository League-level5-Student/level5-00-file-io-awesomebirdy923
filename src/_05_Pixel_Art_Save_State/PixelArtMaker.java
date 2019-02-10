package _05_Pixel_Art_Save_State;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PixelArtMaker implements MouseListener, ActionListener {
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gp;
	private JButton save;
	private JButton load;
	private static boolean saving;
	ColorSelectionPanel csp;

	public void start() {
		save = new JButton("fortnite");
		load = new JButton("save");
		gip = new GridInputPanel(this);
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);

		save.addActionListener(this);
		load.addActionListener(this);

		window.add(gip);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

	}

	public void submitGridData(int w, int h, int r, int c) {
		gp = new GridPanel(w, h, r, c);
		csp = new ColorSelectionPanel();
		window.remove(gip);
		window.add(gp);
		window.add(csp);
		gp.repaint();
		gp.addMouseListener(this);
		window.add(save);
		window.add(load);
		window.pack();
	}

	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		System.out.println(csp.getSelectedColor());
		if (e.getButton() == e.BUTTON1) {
			gp.clickPixel(e.getX(), e.getY());
			gp.repaint();
		} else if (e.getButton() == e.BUTTON3) {
			gp.erasePixel(e.getX(), e.getY());
			gp.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		System.out.println(csp.getSelectedColor());
		if (e.getButton() == e.BUTTON1) {
			gp.clickPixel(e.getX(), e.getY());
			gp.repaint();
		} else if (e.getButton() == e.BUTTON3) {
			gp.erasePixel(e.getX(), e.getY());
			gp.repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		saving = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public static boolean isSaving() {
		return saving;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton pressed = (JButton) e.getSource();
		if (pressed == save) {
			try {
				FileWriter writer = new FileWriter("src/_05_Pixel_Art_Save_State/saveState.txt", false);
				Pixel[][] array = gp.getPixelArray();
				System.out.println(gip.getRows() + " " + gip.getCollumns());
				writer.write(gip.getRows() + "\n");
				writer.write(gip.getCollumns() + "\n");
				writer.write(gp.getPixelWidth() + "\n");
				writer.write(gp.getPixelHeight() + "\n");
				writer.write(gp.getWindowWidth() + "\n");
				writer.write(gp.getWindowHeight() + "\n");
				for (int i = 0; i < array.length; i++) {
					for (int j = 0; j < array.length; j++) {
						writer.write(array[i][j].color.getRed() + "/" + array[i][j].color.getGreen() + "/"
								+ array[i][j].color.getBlue() + "\n");
					}
				}
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if (pressed == load) {
			try {
				BufferedReader reader = new BufferedReader(
						new FileReader("src/_05_Pixel_Art_Save_State/saveState.txt"));
				System.out.println("Test");
				try {
					Pixel[][] array = new Pixel[Integer.parseInt(reader.readLine())][Integer
							.parseInt(reader.readLine())];
					gp.setPixelScale(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()));
					int width = Integer.parseInt(reader.readLine());
					int height = Integer.parseInt(reader.readLine());
					for (int i = 0; i < array.length; i++) {
						for (int j = 0; j < array.length; j++) {
							String[] colorData = reader.readLine().split("/");
							Color color = new Color(Integer.parseInt(colorData[0]), Integer.parseInt(colorData[1]),
									Integer.parseInt(colorData[2]));
							Pixel pixel = new Pixel(i, j);
							pixel.color = color;
							array[i][j] = pixel;
						}
					}
					gp.setPixelArray(array);
					gp.update();
					gp.setPreferredSize(new Dimension(width, height));
					window.pack();
					reader.close();
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}
}
