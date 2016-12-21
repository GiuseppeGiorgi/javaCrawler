package robots;

/**
 * @author Giuseppe Giorgi <giuseppe.giorgi1987@gmail.com>
 */

public class RobotsException extends Exception{
	
	public RobotsException(String message){
		super(message);
	}
	
	public RobotsException(String messagge, Throwable t){
		super(messagge + ":::::" + t.getMessage());
	}

}
