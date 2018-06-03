package main;

import java.util.HashMap;
import java.util.Map.Entry;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import character.CharacterType;
import data.DiceSet;

public class Scenario
{
	private static final String CHARACTERTYPES = "characterTypes";
	private static final String DICESETS = "diceSets";
	
	HashMap<String, CharacterType> characterTypes = new HashMap<String, CharacterType>();
	HashMap<String, DiceSet> diceSets = new HashMap<String, DiceSet>();
	
	public Scenario(JsonObject jsonObject)
	{
		Main.setCurrentScenario(this);
		this.loadDiceSets(jsonObject.getJsonObject(DICESETS));
		this.loadCharacterTypes(jsonObject.getJsonObject(CHARACTERTYPES));
	}
	
	private void loadDiceSets(JsonObject jsonObject)
	{
		for (Entry<String, JsonValue> entry : jsonObject.entrySet())
		{
			this.diceSets.put(entry.getKey(), new DiceSet((JsonArray) entry.getValue()));
		}
	}
	
	private void loadCharacterTypes(JsonObject jsonObject)
	{
		for (Entry<String, JsonValue> entry : jsonObject.entrySet())
		{
			String name = entry.getKey();
			JsonObject typeJson = (JsonObject) entry.getValue();
			this.characterTypes.put(name, new CharacterType(typeJson));
		}
	}
	
	public DiceSet getDiceSet(String diceSetKey)
	{
		return this.diceSets.get(diceSetKey);
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
