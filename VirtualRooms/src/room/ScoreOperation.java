package room;

import javax.json.JsonObject;

public class ScoreOperation extends RoomOperation
{
	private static final String SCORE = "score";
	private static final String AMOUNT = "amount";
	
	protected String score;
	protected int amount;
	
	public ScoreOperation(JsonObject jsonObject)
	{
		super(jsonObject);
		this.amount = jsonObject.getInt(AMOUNT);
		this.score = jsonObject.getString(SCORE);
	}
}
