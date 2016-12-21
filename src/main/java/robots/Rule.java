package robots;

/**
 * @author Giuseppe Giorgi <giuseppe.giorgi1987@gmail.com>
 *
 * Regole robots.txt
 * se un particolare path è permesso
 * */
public interface Rule {
	
	/**
	 * @return True se è ALLOWED
	 * @return false se è DISALLOWED
	 * null se la regola non è applicabile
	 * */

	Boolean isAllowed(String path);
}
