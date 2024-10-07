package histoire;

public class VillageSansChefExeception extends Exception {


	private static final long serialVersionUID = 1L;

	public VillageSansChefExeception() {
		
	}
	
	public VillageSansChefExeception(String message) {
		super(message);
	}
	
	public VillageSansChefExeception(Throwable cause) {
		super(cause);
	}
	
	public VillageSansChefExeception(String message, Throwable cause) {
		super(message,cause);
	}
	
}
