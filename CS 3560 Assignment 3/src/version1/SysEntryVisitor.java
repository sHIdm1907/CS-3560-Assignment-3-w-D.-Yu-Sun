package version1;

import java.util.List;

public class SysEntryVisitor implements Visitable {

	
	
	public void visit() {
		
	}

	@Override
	public int accept(ShowGroupTotalVisitor visitor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int accept(ShowMessageTotalVisitor visitor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float accept(ShowPositivePercentVisitor visitor, List<String> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int accept(ShowUserTotalVisitor visitor) {
		// TODO Auto-generated method stub
		return 0;
	}

}
