package character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Character
{
	private String name;
	private HashMap<String, Option> characteristics;
	private HashMap<String, Integer> scores;
	private AdditionSet additionSet;
	private HashMap<String, String> states;
	
	public Character(HashMap<String, Option> characteristics, HashMap<String, Integer> scores, AdditionSet additionSet, 
			HashMap<String, String> states, String identifier)
	{
		this.characteristics = characteristics;
		this.scores = scores;
		this.additionSet = additionSet;
		this.states = states;
		this.name = this.setupName(identifier); 
	}
	
	public String getName()
	{
		return name;
	}
	
	private String setupName(String identifier)
	{
		StringBuilder nameBuilder = new StringBuilder();
		String[] identifierParts = identifier.split("\\+");
		boolean first = true;
		for (String identifierPart : identifierParts)
		{
			if (!first)
				nameBuilder.append(" ");
			nameBuilder.append(this.characteristics.get(identifierPart).getName());
			first = false;
		}
		return nameBuilder.toString();
	}
	
	
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (Entry<String, Option> entry : this.characteristics.entrySet())
		{
			stringBuilder.append(entry.getKey());
			stringBuilder.append(" : ");
			stringBuilder.append(entry.getValue().getName());
			stringBuilder.append("\r\n");
		}
		for (Entry<String, String> entry : this.states.entrySet())
		{
			stringBuilder.append(entry.getKey());
			stringBuilder.append(" : ");
			stringBuilder.append(entry.getValue());
			stringBuilder.append("\r\n");
		}
		for (Entry<String, Integer> entry : this.scores.entrySet())
		{
			stringBuilder.append(entry.getKey());
			stringBuilder.append(" : ");
			stringBuilder.append(entry.getValue());
			stringBuilder.append("\r\n");
		}
		for (Entry<String, ArrayList<HashMap<String, Option>>> entry : this.additionSet.getAdditionListMap().entrySet())
		{
			for (HashMap<String, Option> map : entry.getValue())
			{
				stringBuilder.append(entry.getKey() + "\r\n");
				for (Entry<String, Option> entry2 : map.entrySet())
				{
					stringBuilder.append("	" + entry2.getKey());
					stringBuilder.append(" : ");
					stringBuilder.append(entry2.getValue().getName());
					stringBuilder.append("\r\n");
				}
			}
		}
		return stringBuilder.toString();
	}
}
