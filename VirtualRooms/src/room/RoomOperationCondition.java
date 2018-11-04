package room;

import javax.json.JsonObject;

public class RoomOperationCondition
{
	private static final String TYPE = "type";
	private static final String SOURCE = "source";
	private static final String TARGET = "target";
	
	ConditionType conditionType;
	String source;
	String target;
	
	public RoomOperationCondition(JsonObject jsonObject)
	{
		this.conditionType = ConditionType.getConditionType(jsonObject.getString(TYPE));
		this.source = jsonObject.getString(SOURCE);
		this.target = jsonObject.getString(TARGET);
	}
	
	private enum ConditionType
	{
		GREATER(">"), STATE_NOT_EQUAL("state_not_equal"), LESS_OR_EQUAL("<=");
		
		public String text;
		
		private ConditionType(String text)
		{
			this.text = text;
		}
		
		public static ConditionType getConditionType(String conditionString)
		{
			for (ConditionType conditionType : ConditionType.values())
			{
				if (conditionType.text.equals(conditionString))
					return conditionType;
			}
			return null;
		}
	}
}
