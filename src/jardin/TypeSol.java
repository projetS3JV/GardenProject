package jardin;

public enum TypeSol {

	ARGILEUX(0), CALCAIRE(1), HUMIFERE(2), LIMONEUX(3), SABLEUX(4);
	
	private final int value;

    private TypeSol(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
