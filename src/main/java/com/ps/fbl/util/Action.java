package com.ps.fbl.util;
/**
 * 
 * @author Mukesh.V
 *
 */
public enum Action {
	
	LINEUPS("get_lineups"),
	STATISTICS("get_statistics"),
	OODS("get_odds"),
	H2H("get_H2H"),
	COUNTRIES("get_countries"),
	LEAGUES("get_leagues"),
	STANDINGS("get_standings");
	
	public String value;

	Action(String value) {
		this.value = value;
	}

	public static Action get(String value) {
		for (Action val : values())
			if (val.value.equals(value))
				return val;
		return null;
	}

	public String getValue() {
		return value;
	}

}
