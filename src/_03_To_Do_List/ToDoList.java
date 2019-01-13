package _03_To_Do_List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ToDoList implements ActionListener {
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save
	 * list, and load list.
	 * 
	 * 
	 * 
	 * When add task is clicked: ask the user for a task and save it to an array
	 * list
	 * 
	 * When the view tasks button is clicked: show all the tasks in the list
	 * 
	 * When the remove task button is clicked: prompt the user for which task to
	 * remove and take it off of the list.
	 * 
	 * When the save list button is clicked: Save the list to a file
	 * 
	 * When the load list button is clicked: Prompt the user for the location of the
	 * file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file
	 * into the list.
	 */

	private List<String> tasks;
	private JButton addTask;
	private JButton viewTasks;
	private JButton saveList;
	private JButton removeTask;
	private JButton loadList;
	private BufferedReader reader;
	private FileWriter writer;
	private String fileName;

	public ToDoList(int width, int height) {
		JFileChooser jfc = new JFileChooser();
		int val = jfc.showOpenDialog(null);
		if(val == JFileChooser.APPROVE_OPTION) {
			fileName = jfc.getSelectedFile().getAbsolutePath();
		}
		tasks = loadList();
		JFrame frame = new JFrame("fortnite");
		JPanel panel = new JPanel();
		addTask = new JButton("Add Task");
		addTask.addActionListener(this);
		viewTasks = new JButton("View Tasks");
		viewTasks.addActionListener(this);
		removeTask = new JButton("Remove Task");
		removeTask.addActionListener(this);
		saveList = new JButton("Save List");
		saveList.addActionListener(this);
		loadList = new JButton("Load List");
		loadList.addActionListener(this);
		panel.add(loadList);
		panel.add(addTask);
		panel.add(viewTasks);
		panel.add(removeTask);
		panel.add(saveList);
		frame.add(panel);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.pack();
	}

	public static void main(String[] args) {
		new ToDoList(700, 700);
	}
	
	public List<String> loadList(){
	
		try {
			 reader = new BufferedReader(new FileReader(fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		List<String> newList = new ArrayList<>();
		try {
			String line = reader.readLine();
			while(line != null) {
				newList.add(line);
				System.out.println(line);
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newList;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clicked = (JButton) e.getSource();
		if(clicked == addTask) {
			tasks.add(JOptionPane.showInputDialog("What's your task?"));
		} else if(clicked == viewTasks) {
			String taskList = "";
			for (int i = 0; i < tasks.size(); i++) {
				taskList += tasks.get(i) + "\n";
			}
			JOptionPane.showMessageDialog(null, "Your tasks are:\n" + taskList);
		} else if(clicked == removeTask) {
			tasks.remove(JOptionPane.showInputDialog(null, "What task would you like to remove?"));
		} else if(clicked == saveList) {
			for (int i = 0; i < tasks.size(); i++) {
				try {
					System.out.println(tasks.get(i));
					try {
						writer = new FileWriter(fileName, false);
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					writer.write(tasks.get(i) + "\n");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Failed to save list!");
				}
			}
			try {
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if(clicked == loadList) {
			JFileChooser jfc = new JFileChooser();
			int val = jfc.showOpenDialog(null);
			if(val == JFileChooser.APPROVE_OPTION) {
				fileName = jfc.getSelectedFile().getAbsolutePath();
			}
			try {
				writer = new FileWriter(fileName, false);
				writer.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			tasks = loadList();
		}
	}

}
