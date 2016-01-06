package pilotage.service.menu;

import java.util.Comparator;

public class PilotageMenuPlaceSorter implements Comparator<PilotageMenu>{

	@Override
	public int compare(PilotageMenu o1, PilotageMenu o2) {
		if(o1.getMenu().getPlace() > o2.getMenu().getPlace()){
			return 1;
		}else {
			return 0;
		}
	}

}
