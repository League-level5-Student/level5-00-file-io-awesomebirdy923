package _02_File_Encrypt_Decrypt;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileEncryptor {
	// Create a program that takes a message from the user.
	// Use the methods in the String and Character classes to save
	// an encrypted form of the message to a file
	public static void main(String[] args) {
		String message = JOptionPane.showInputDialog("Write something down.");
		String newString = "";
		try {
			FileWriter writer = new FileWriter("src/_02_File_Encrypt_Decrypt/text.txt", false);
			for (int i = 0; i < message.length(); i++) {
				if(((int) message.charAt(i)) < 126) {
				newString += (((int) message.charAt(i)) + 1) + " ";
				}else {
				newString += 34;
				}
			}
			writer.write(newString);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
