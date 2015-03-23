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
    
    public static String getTypeSol(int type) {
    	if (type == 0) {
    		return "ARGILEUX";
    	} else if (type == 1) {
    		return "CALCAIRE";
    	} else if (type == 2) {
    		return "HUMIFERE";
    	} else if (type == 3) {
    		return "LIMONEUX";
    	}
    	return "SABLEUX";
    }
}
