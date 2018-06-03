package character;

import java.util.ArrayList;
import java.util.HashMap;

public class AdditionSet 
{
	HashMap<String, ArrayList<HashMap<String, Option>>> additionLists = new HashMap<String, ArrayList<HashMap<String, Option>>>();
	
	public AdditionSet()
	{
		
	}
	
	public void addAdditionList(String key, ArrayList<HashMap<String, Option>> additionList)
	{
		this.additionLists.put(key, additionList);
	}
	
	public HashMap<String, ArrayList<HashMap<String, Option>>> getAdditionListMap()
	{
		return this.additionLists;
	}
}
