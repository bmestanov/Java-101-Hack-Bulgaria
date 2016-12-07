package week8;

public enum Element {
	WALL("🚫"), PATH("     "), ENEMY("😈"), 
	TREASURE("★"), HERO("😼"), GATE("🚩");
	
	private String value;
	private Element(String val) {
		value = val;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
	public static Element get(String val){
		switch (val) {
		case "H": {
			return HERO;
		}

		case ".": {
			return PATH;
		}

		case "G": {
			return GATE;
		}

		case "#": {
			return WALL;
		} default : {
			return TREASURE;
		}
		}
	}
}
