package jardin.plante;

public enum TypePlante {
	HERBE(0),
	BUISSON(1),
	FLEUR(2);
	
	private final int value;

    TypePlante(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
