package robots;

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