package _03_To_Do_List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
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

	public ToDoList(int width, int height) {
		tasks = new ArrayList<String>();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("r30rw4j");
		JButton clicked = (JButton) e.getSource();
		if(clicked == addTask) {
			tasks.add(JOptionPane.showInputDialog("What's your task?"));
		}else if(clicked == viewTasks) {
			String taskList = "";
			for (int i = 0; i < tasks.size(); i++) {
				taskList += tasks.get(i) + "\n";
			}
			JOptionPane.showMessageDialog(null, "Your tasks are:\n" + taskList);
		}
	}

	

}
