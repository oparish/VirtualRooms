package data;

import java.util.ArrayList;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonValue;

import main.Main;

public class DiceSet 
{
	ArrayList<Integer> diceList = new ArrayList<Integer>();
	
	public DiceSet(JsonArray jsonArray)
	{
		for (JsonValue jsonValue : jsonArray)
		{
			this.diceList.add(((JsonNumber) jsonValue).intValue());
		}
	}
	
	public int rollDice()
	{
		int result = 0;
		for (Integer dice : this.diceList)
		{
			result += Main.getRnd(dice) + 1;
		}
		return result;
	}
}
