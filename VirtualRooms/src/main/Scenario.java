package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import character.CharacterType;
import data.DiceSet;
import room.RoomCategory;

public class Scenario
{
	private static final String CHARACTERTYPES = "characterTypes";
	private static final String DICESETS = "diceSets";
	private static final String ROOMCATEGORIES = "roomCategories";
	
	HashMap<String, CharacterType> characterTypes = new HashMap<String, CharacterType>();
	HashMap<String, DiceSet> diceSets = new HashMap<String, DiceSet>();
	HashMap<String, RoomCategory> roomCategories = new HashMap<String, RoomCategory>();
	
	public Scenario(JsonObject jsonObject)
	{
		Main.setCurrentScenario(this);
		this.loadDiceSets(jsonObject.getJsonObject(DICESETS));
		this.loadCharacterTypes(jsonObject.getJsonObject(CHARACTERTYPES));
		this.loadRoomCategories(jsonObject.getJsonObject(ROOMCATEGORIES));
	}
	
	public Set<String> getRoomCategoryNameList()
	{
		return this.roomCategories.keySet();
	}
	
	private void loadDiceSets(JsonObject jsonObject)
	{
		for (Entry<String, JsonValue> entry : jsonObject.entrySet())
		{
			this.diceSets.put(entry.getKey(), new DiceSet((JsonArray) entry.getValue()));
		}
	}
	
	private void loadRoomCategories(JsonObject jsonObject)
	{
		for (Entry<String, JsonValue> entry : jsonObject.entrySet())
		{
			String name = entry.getKey();
			JsonObject typeJson = (JsonObject) entry.getValue();
			this.roomCategories.put(name, new RoomCategory(typeJson));
		}
	}
	
	private void loadCharacterTypes(JsonObject jsonObject)
	{
		for (Entry<String, JsonValue> entry : jsonObject.entrySet())
		{
			String name = entry.getKey();
			JsonObject typeJson = (JsonObject) entry.getValue();
			this.characterTypes.put(name, new CharacterType(typeJson, name));
		}
	}
	
	public DiceSet getDiceSet(String diceSetKey)
	{
		return this.diceSets.get(diceSetKey);
	}
	
	public RoomCategory getRoomCategory(String typeName)
	{
		return this.roomCategories.get(typeName);
	}
	
	public CharacterType getCharacterType(String typeName)
	{
		return this.characterTypes.get(typeName);
	}
	
	public CharacterType getFirstCharacterType()
	{
		return (CharacterType) this.characterTypes.values().toArray()[0];
	}
}
