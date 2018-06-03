package character;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.swing.JFrame;
import javax.swing.JWindow;

import data.OptionList;
import main.Main;
import main.Scenario;

public class CharacterType 
{
	private static final String CHARACTERISTICS = "characteristics";
	private static final String SCORES = "scores";
	private static final String STATES = "states";
	private static final String ADDITIONS = "additions";
	
	HashMap<String, Characteristic> characteristics = new HashMap<String, Characteristic>();
	HashMap<String, Score> scores = new HashMap<String, Score>();
	HashMap<String, Addition> additions = new HashMap<String, Addition>();
	
	public CharacterType(JsonObject jsonObject)
	{
		super();

		this.loadCharacteristicTypes(jsonObject);
		this.loadScoreTypes(jsonObject);
		this.loadAdditions(jsonObject);
	}
	
	private void loadCharacteristicTypes(JsonObject jsonObject)
	{
		JsonObject charJson = jsonObject.getJsonObject(CHARACTERISTICS);
		for (Entry<String, JsonValue> entry : charJson.entrySet())
		{
			String name = entry.getKey();
			this.characteristics.put(name, new Characteristic((JsonObject) entry.getValue()));
		}
	}
	
	private void loadScoreTypes(JsonObject jsonObject)
	{
		JsonObject scoreJson = jsonObject.getJsonObject(SCORES);
		for (Entry<String, JsonValue> entry : scoreJson.entrySet())
		{
			String name = entry.getKey();
			this.scores.put(name, new Score((JsonObject) entry.getValue()));
		}
	}

	private void loadAdditions(JsonObject jsonObject)
	{
		JsonObject additionsJson = jsonObject.getJsonObject(ADDITIONS);
		for (Entry<String, JsonValue> entry : additionsJson.entrySet())
		{
			String name = entry.getKey();
			this.additions.put(name, new Addition((JsonObject) entry.getValue()));
		}
	}
	
	public Character generateCharacter()
	{
		HashMap<String, Option> charCharacteristics = new HashMap<String, Option>();
		for (Entry<String, Characteristic> entry : this.characteristics.entrySet())
		{
			Option option = entry.getValue().getOptionList().getRandomItem();
			charCharacteristics.put(entry.getKey(), option);
		}
		
		HashMap<String, Integer> scores = new HashMap<String, Integer>();
		for (Entry<String, Score> entry : this.scores.entrySet())
		{
			Integer result = entry.getValue().getDiceSet().rollDice();
			scores.put(entry.getKey(), result);
		}
		
		AdditionSet additionDetails = new AdditionSet();
		for (Entry<String, Addition> entry : this.additions.entrySet())
		{
			additionDetails.addAdditionList(entry.getKey(), entry.getValue().generateAdditionsList());
		}
		
		return new Character(charCharacteristics, scores, additionDetails);
	}
	
	public static void main(String[] args)
	{	
		File file = new File("scenario.json");
		JsonObject jsonObject = Main.loadJsonObject(file);
		Scenario scenario = new Scenario(jsonObject);
		CharacterType characterType = scenario.getFirstCharacterType();
		Character character = characterType.generateCharacter();
		System.out.println(character.toString());
	}
	

}
