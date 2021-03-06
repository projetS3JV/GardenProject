package jardin;

public enum Ensoleillement {

	SOLEIL(0),	MIOMBRE(1), OMBRE(2);
	
	private final int value;

    Ensoleillement(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public Ensoleillement getEnsoleillement2(int type){
    	if (type == 0) {
    		return Ensoleillement.SOLEIL;
    	} else if (type == 1) {
    		return Ensoleillement.MIOMBRE;
    	}
    	return Ensoleillement.OMBRE;
    }
    
    public static String getEnsoleillement(int type) {
    	if (type == 0) {
    		return "SOLEIL";
    	} else if (type == 1) {
    		return "MIOMBRE";
    	}
    	return "OMBRE";
    }
    
}
