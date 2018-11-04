package room;

import java.util.HashMap;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

public abstract class RoomOperation 
{
	private static final String TYPE = "type";
	
	public RoomOperation(JsonObject jsonObject)
	{
		
	}
	
	public static RoomOperation createRoomOperation(JsonObject jsonObject)
	{
		String type = jsonObject.getString(TYPE);
		if (type.equals("scoreIncrease"))
		{
			return new ScoreIncreaseOperation(jsonObject);
		}
		else if (type.equals("scoreDecrease"))
		{
			return new ScoreDecreaseOperation(jsonObject);
		}
		else if (type.equals("stateChange"))
		{
			return new StateChangeOperation(jsonObject);
		}
		else if (type.equals("move"))
		{
			return new MoveOperation(jsonObject);
		}
		return null;
	}
	
}
