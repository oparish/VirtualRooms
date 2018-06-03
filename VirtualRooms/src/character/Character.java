package character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Character
{
	private HashMap<String, Option> characteristics;
	private HashMap<String, Integer> scores;
	private AdditionSet additionSet;
	
	public Character(HashMap<String, Option> characteristics, HashMap<String, Integer> scores, AdditionSet additionSet)
	{
		this.characteristics = characteristics;
		this.scores = scores;
		this.additionSet = additionSet;
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
