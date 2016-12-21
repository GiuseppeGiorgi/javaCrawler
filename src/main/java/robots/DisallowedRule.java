package robots;

/**
 * @author Giuseppe Giorgi <giuseppe.giorgi1987@gmail.com>
 */

public class DisallowedRule extends AbstractRule {

	
	public DisallowedRule(String path, boolean wildcardsAllowed) {
		super(path, wildcardsAllowed);
		
	}

	@Override
	public Boolean isAllowed(String path) {

		if("".equals(super.getPath())){
			
			return Boolean.TRUE;
		}
		if(!match(path)){
			
			return null;
			
		}
		else{
			return Boolean.FALSE;
		}
		
	}
	
}
