package data;

import java.util.ArrayList;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JWindow;

import character.Option;
import main.Main;

@SuppressWarnings("serial")
public class OptionList extends ArrayList<Option>
{
	public OptionList(JsonArray jsonArray)
	{
		super();
		for (JsonValue jsonValue : jsonArray)
		{
			this.add(new Option((JsonObject) jsonValue));
		}
	}
	
	public Option getRandomItem()
	{
		return OptionList.getOptionFromList(this);
	}
	
	public Option getRandomOptionWithExclusions(ArrayList<Option> exclusions)
	{
		if (exclusions.size() == this.size())
			return null;
		ArrayList<Option> reducedList = new ArrayList<Option>();
		for (Option option : this)
		{
			if (!exclusions.contains(option))
				reducedList.add(option);
		}
		return OptionList.getOptionFromList(reducedList);
	}
	
	private static Option getOptionFromList(ArrayList<Option> list)
	{
		ArrayList<Option> weightedList = new ArrayList<Option>();
		for (Option option : list)
		{
			for (int i = 0; i < option.getWeight(); i++)
				weightedList.add(option);
		}
		int rnd = Main.getRnd(weightedList.size());
		return weightedList.get(rnd);
	}
}
