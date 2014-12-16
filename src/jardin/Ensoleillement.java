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
    
}
