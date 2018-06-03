package character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.json.JsonObject;
import javax.json.JsonValue;

import data.OptionList;
import main.Main;

public class Addition
{
	private static final String NAME = "name";
	private static final String CHANCE = "chance";
	private static final String NONREPEATABLELISTS = "nonrepeatableLists";
	private static final String REPEATABLELISTS = "repeatableLists";
	
	private String name;
	private int chance;
	private HashMap<String, Characteristic> nonRepeatableLists = new HashMap<String, Characteristic>();
	private HashMap<String, Characteristic> repeatableLists = new HashMap<String, Characteristic>();
	
	public Addition(JsonObject jsonObject)
	{
		this.name = jsonObject.getString(NAME);
		this.chance = jsonObject.getInt(CHANCE);
		this.loadNonRepeatableLists(jsonObject.getJsonObject(NONREPEATABLELISTS));
		this.loadRepeatableLists(jsonObject.getJsonObject(REPEATABLELISTS));
	}
	
	private void loadNonRepeatableLists(JsonObject jsonObject)
	{
		for (Entry<String, JsonValue> entry : jsonObject.entrySet())
		{
			this.nonRepeatableLists.put(entry.getKey(), new Characteristic((JsonObject) entry.getValue()));
		}
	}
	
	private void loadRepeatableLists(JsonObject jsonObject)
	{
		for (Entry<String, JsonValue> entry : jsonObject.entrySet())
		{
			this.repeatableLists.put(entry.getKey(), new Characteristic((JsonObject) entry.getValue()));
		}
	}
	
	public ArrayList<HashMap<String, Option>> generateAdditionsList()
	{
		ArrayList<HashMap<String, Option>> additionsList = new ArrayList<HashMap<String, Option>>();
		int result = Main.getRnd(100);
		HashMap<String, ArrayList<Option>> usedNoRepeatableOptions = new HashMap<String, ArrayList<Option>>();
		for (String key : this.nonRepeatableLists.keySet())
		{
			usedNoRepeatableOptions.put(key, new ArrayList<Option>());
		}	
		
		while (result < chance && this.stillViableAdditions(usedNoRepeatableOptions))
		{
			HashMap<String, Option> additionInstance = new HashMap<String, Option>();

			for (Entry<String, Characteristic> entry : this.nonRepeatableLists.entrySet())
			{
				String key = entry.getKey();
				Option newOption = entry.getValue().getOptionList().getRandomOptionWithExclusions(usedNoRepeatableOptions.get(key));
				usedNoRepeatableOptions.get(key).add(newOption);
				additionInstance.put(key, newOption);
			}
			for (Entry<String, Characteristic> entry : this.repeatableLists.entrySet())
			{
				additionInstance.put(entry.getKey(), entry.getValue().getOptionList().getRandomItem());
			}
			additionsList.add(additionInstance);
			result = Main.getRnd(100);
		}
		return additionsList;
	}
	
	private boolean stillViableAdditions(HashMap<String, ArrayList<Option>> usedNoRepeatableOptions)
	{
		for (Entry<String, Characteristic> entry : this.nonRepeatableLists.entrySet())
		{
			if (entry.getValue().getOptionList().size() == usedNoRepeatableOptions.get(entry.getKey()).size())
				return false;
		}
		return true;
	}
}
