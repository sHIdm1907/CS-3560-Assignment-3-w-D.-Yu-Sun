package version1;

import java.util.List;

public class ShowMessageTotalVisitor {
	
	public int visit(int counter) {
		int total = 0;
		total += counter;
		return total;
	}
}
