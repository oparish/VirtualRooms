package room;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

import character.Character;
import character.CharacterType;
import character.Option;
import main.Main;
import main.Scenario;

public class RoomCategory 
{
	private static final String NAME = "name";
	private static final String REQUIREDCHARACTERS = "requiredCharacters";
	private static final String OPERATIONS = "operations";
	private static final String OPERATION = "operation";
	private static final String CONDITION = "condition";
	
	String name;
	ArrayList<CharacterType> requiredCharacters = new ArrayList<CharacterType>();
	HashMap<String, RoomOperationCondition> conditionMap = new HashMap<String, RoomOperationCondition>();
	
	public RoomCategory(JsonObject jsonObject)
	{
		this.name = jsonObject.getString(NAME);
		this.loadRequiredCharacters(jsonObject);
		this.loadConditionMap(jsonObject);
	}
	
	private void loadRequiredCharacters(JsonObject jsonObject)
	{
		JsonArray jsonArray = jsonObject.getJsonArray(REQUIREDCHARACTERS);
		if (jsonArray == null)
			return;
		for (JsonValue jsonValue : jsonArray)
		{
			JsonString jsonString = (JsonString) jsonValue;
			CharacterType characterType = Main.getCurrentScenario().getCharacterType(jsonString.getString());
			this.requiredCharacters.add(characterType);
		}
	}
	
	private void loadConditionMap(JsonObject jsonObject)
	{
		JsonArray jsonArray = jsonObject.getJsonArray(OPERATIONS);
		if (jsonArray == null)
			return;
		for (JsonValue jsonValue : jsonArray)
		{
			JsonObject innerJson = (JsonObject) jsonValue;
			String operationKey = innerJson.getString(OPERATION);
			JsonObject conditionJson = innerJson.getJsonObject(CONDITION);
			if (conditionJson != null)
				this.conditionMap.put(operationKey, new RoomOperationCondition(conditionJson));
			else
				this.conditionMap.put(operationKey, null);
		}
	}
	
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		for (CharacterType characterType : this.requiredCharacters)
		{
			stringBuilder.append(characterType.toString());
			stringBuilder.append("\r\n");
		}
		for (Entry<String, RoomOperationCondition> entry : this.conditionMap.entrySet())
		{
			stringBuilder.append(entry.getKey());
			stringBuilder.append(" : ");
			stringBuilder.append(entry.toString());
			stringBuilder.append("\r\n");
		}
		return stringBuilder.toString();
	}
	
	
	public static void main(String[] args)
	{	
		File file = new File("scenario.json");
		JsonObject jsonObject = Main.loadJsonObject(file);
		Scenario scenario = new Scenario(jsonObject);
		for (String roomCategoryName : scenario.getRoomCategoryNameList())
		{
			System.out.println(scenario.getRoomCategory(roomCategoryName).toString());
		}
	}
	
}
