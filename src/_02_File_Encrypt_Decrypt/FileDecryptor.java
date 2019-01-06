package _02_File_Encrypt_Decrypt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileDecryptor {
	// Create a program that opens the file created by FileEncryptor and display
	// the decrypted message to the user in a JOptionPane.
	public static void main(String[] args) {
		String line = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/_02_File_Encrypt_Decrypt/text.txt"));
			line = reader.readLine();
			String file = "";
			while(line != null) {
				file+=line;
				line = reader.readLine();
				System.out.println(line);
			}
			String[] splitLine = file.split(" ");
			String newString = "";
			for (int i = 0; i < splitLine.length; i++) {
				if(Integer.parseInt(splitLine[i])>34 && Integer.parseInt(splitLine[i])<126 || Integer.parseInt(splitLine[i])<=33) {
					int currentNum = Integer.parseInt(splitLine[i]);
					char character = (char) (currentNum-1);
					newString += character;
				} else if(Integer.parseInt(splitLine[i])==34){
					newString += "~";
				}
			}
			JOptionPane.showMessageDialog(null, newString);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
