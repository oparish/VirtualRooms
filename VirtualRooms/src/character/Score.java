package character;

import javax.json.JsonObject;

import data.DiceSet;
import main.Main;

public class Score
{
	private static final String NAME = "name";
	private static final String DICESET = "diceSet";
	
	String name;
	DiceSet diceSet;
	
	public String getName() {
		return name;
	}

	public DiceSet getDiceSet() {
		return diceSet;
	}

	public Score(JsonObject jsonObject)
	{
		this.name = jsonObject.getString(NAME);
		this.diceSet = Main.getCurrentScenario().getDiceSet(jsonObject.getString(DICESET));
	}
}
