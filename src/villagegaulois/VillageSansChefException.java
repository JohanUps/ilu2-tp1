package villagegaulois;

public class VillageSansChefException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public VillageSansChefException() {
		super("Le village n'a pas de chef");
	}

}
