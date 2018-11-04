package room;

import javax.json.JsonObject;

public class StateChangeOperation extends RoomOperation
{
	private static final String STATE = "state";
	private static final String NEWVALUE = "newValue";
	
	private String state;
	private String newValue;
	
	public StateChangeOperation(JsonObject jsonObject)
	{
		super(jsonObject);
		this.state = jsonObject.getString(STATE);
		this.newValue = jsonObject.getString(NEWVALUE);
	}
}
