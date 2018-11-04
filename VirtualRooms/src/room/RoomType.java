package room;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.json.JsonObject;
import javax.json.JsonValue;

import character.Characteristic;
import main.Main;

public class RoomType 
{
	private static final String NAME = "name";
	private static final String TYPE = "type";
	private static final String OPERATIONS = "operations";
	
	String name;
	RoomCategory roomCategory;
	HashMap<String, RoomOperation> operations;
	
	public RoomType(JsonObject jsonObject)
	{
		this.roomCategory = Main.getCurrentScenario().getRoomCategory(jsonObject.getString(TYPE));
		this.name = jsonObject.getString(NAME);	
		this.loadOperationsMap(jsonObject);
	}
	
	private void loadOperationsMap(JsonObject jsonObject)
	{
		this.operations = new HashMap<String, RoomOperation>();
		JsonObject operationsJson = jsonObject.getJsonObject(OPERATIONS);
		for (Entry<String, JsonValue> entry : operationsJson.entrySet())
		{
			String operationName = entry.getKey();
			this.operations.put(operationName, RoomOperation.createRoomOperation((JsonObject) entry.getValue()));
		}
	}
}
