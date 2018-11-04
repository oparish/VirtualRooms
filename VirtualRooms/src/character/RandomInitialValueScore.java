package character;

import javax.json.JsonObject;

import data.DiceSet;
import main.Main;

public class RandomInitialValueScore extends Score
{
	private static final String DICESET = "diceSet";
	
	DiceSet diceSet;
	
	public DiceSet getDiceSet() {
		return diceSet;
	}
	
	public RandomInitialValueScore(JsonObject jsonObject)
	{
		super(jsonObject);
		this.diceSet = Main.getCurrentScenario().getDiceSet(jsonObject.getString(DICESET));
	}

	@Override
	public int generateValue()
	{
		return this.diceSet.rollDice();
	}

}
