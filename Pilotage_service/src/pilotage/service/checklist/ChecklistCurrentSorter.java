package pilotage.service.checklist;

import java.util.Comparator;

import pilotage.metier.Checklist_Current;

public class ChecklistCurrentSorter implements Comparator<Checklist_Current>{

	@Override
	public int compare(Checklist_Current o1, Checklist_Current o2) {
		Long l1 = o1.getJourStamp() + ((o1.getIdHoraire().getHoraireStamp() + o1.getSousTache().getDecalageStamp()) % 86400000);
		Long l2 = o2.getJourStamp() + ((o2.getIdHoraire().getHoraireStamp() + o2.getSousTache().getDecalageStamp()) % 86400000);
		
		return l1.compareTo(l2);
	}

}
