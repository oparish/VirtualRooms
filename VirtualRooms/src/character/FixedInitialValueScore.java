package character;

import javax.json.JsonObject;

import data.DiceSet;
import main.Main;

public class FixedInitialValueScore extends Score
{
	private static final String STARTINGVALUE = "startingValue";
	
	int startingValue;
	
	public int getStartingValue() {
		return startingValue;
	}

	public FixedInitialValueScore(JsonObject jsonObject)
	{
		super(jsonObject);
		this.startingValue = jsonObject.getInt(STARTINGVALUE);
	}

	@Override
	public int generateValue()
	{
		return this.startingValue;
	}

}
