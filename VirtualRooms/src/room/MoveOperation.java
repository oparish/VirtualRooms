package room;

import javax.json.JsonObject;

public class MoveOperation extends RoomOperation
{
	private static final String TARGET = "target";
	
	String target;
	
	public MoveOperation(JsonObject jsonObject)
	{
		super(jsonObject);
		this.target = jsonObject.getString(TARGET);
	}
}
