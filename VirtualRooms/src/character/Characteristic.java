package character;

import java.util.ArrayList;

import javax.json.JsonObject;

import data.OptionList;

public class Characteristic
{
	private static final String OPTIONS = "options";
	private static final String NAME = "name";
	
	private OptionList optionList;
	private String name;
	
	public Characteristic(JsonObject jsonObject)
	{
		this.optionList = new OptionList(jsonObject.getJsonArray(OPTIONS));
		this.name = jsonObject.getString(NAME);		
	}
	
	public OptionList getOptionList()
	{
		return this.optionList;
	}
}
