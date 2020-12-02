package version1;

import java.util.List;

public interface Visitable {
	
	public int accept(ShowGroupTotalVisitor visitor);
	
	public int accept(ShowMessageTotalVisitor visitor);
	
	public float accept(ShowPositivePercentVisitor visitor, List<String> list);
	
	public int accept(ShowUserTotalVisitor visitor);
	
}
