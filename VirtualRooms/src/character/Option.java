package character;

import javax.json.JsonObject;

public class Option
{
	private static final String NAME = "name";
	private static final String WEIGHT = "weight";
	
	String name;
	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}

	int weight;
	
	public Option(JsonObject jsonObject)
	{
		this.name = jsonObject.getString(NAME);
		this.weight = jsonObject.getInt(WEIGHT);
	}
}
