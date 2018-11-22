package main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main
{
	private static Random random = new Random();
	private static Scenario currentScenario;
	
	public static Scenario getCurrentScenario() {
		return currentScenario;
	}

	public static void setCurrentScenario(Scenario currentScenario) {
		Main.currentScenario = currentScenario;
	}

	public synchronized static int getRnd(int rangeSize)
	{
		return Main.random.nextInt(rangeSize);
	}
	
	public static JsonObject loadJsonObject(File file)
	{
		JsonObject jsonObject = null;
		FileReader fileReader;
		try
		{
			fileReader = new FileReader(file);
			JsonReader jsonReader= Json.createReader(fileReader);	
			jsonObject = jsonReader.readObject();
			jsonReader.close();	
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return jsonObject;		
	}
	
	public static File chooseFile(Component parent)
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("Json", "json"));
		if (chooser.showOpenDialog(parent) == JFileChooser.APPROVE_OPTION)
		{
			File file = chooser.getSelectedFile();
			return file;
		}
		return null;
	}
	
	public static void loadScenario()
	{
		File file = new File("scenario.json");
		JsonObject jsonObject = Main.loadJsonObject(file);
		Main.setCurrentScenario(new Scenario(jsonObject));
	}
	
	public static void displayWindow(JFrame frame)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width - frame.getWidth())/2, (screenSize.height - frame.getHeight())/2);
		frame.setVisible(true);
	}
}
