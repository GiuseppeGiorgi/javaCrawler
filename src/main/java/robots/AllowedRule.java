package robots;

/**
 * @author Giuseppe Giorgi <giuseppe.giorgi1987@gmail.com>
 */

public class AllowedRule extends AbstractRule {

	public AllowedRule(String path, boolean wildcardsAllowed) {		
		super(path, wildcardsAllowed);
		
	}

	@Override
	public Boolean isAllowed(String path) {

		if("".equals(super.getPath())){
			
			return null;
			
		}
		if(!match(path)){
			
		return null;

		}
		else{
			
			return Boolean.TRUE;
		}
	}

}
