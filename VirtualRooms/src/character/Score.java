package character;

import javax.json.JsonObject;

import data.DiceSet;
import main.Main;

public abstract class Score
{
	private static final String NAME = "name";
	private static final String TYPE = "type";
	
	String name;

	public String getName() {
		return name;
	}

	public Score(JsonObject jsonObject)
	{
		this.name = jsonObject.getString(NAME);
	}
	
	public abstract int generateValue();
	
	public static Score makeScore(JsonObject jsonObject)
	{
		String typeString = jsonObject.getString(TYPE);
		ScoreType scoreType = ScoreType.valueOf(typeString.toUpperCase());
		switch (scoreType)
		{
			case RANDOMINITIAL:
				return new RandomInitialValueScore(jsonObject);
			case FIXEDINITIAL:
				return new FixedInitialValueScore(jsonObject);
			default:
				System.out.println("Score Type not recognised.");
				return null;
		}

	}
	
	private enum ScoreType
	{
		RANDOMINITIAL, FIXEDINITIAL;
	}
}
