/**
 * pilotage.service.statistique
 * 20 sept. 2011
 * @author xxu
 */
package pilotage.service.statistique;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 */
public class StatistiquesService {
	
	/**
	 * Return a map sort by value
	 * Value = the occurrence of a specific T instance
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static<T extends Object> LinkedHashMap<T, Integer> getTopFive(List<T> list){
		Map<T,Integer> resultMap = new HashMap<T, Integer>();
		for(T t : list){
			if(resultMap.get(t) == null){
				resultMap.put(t, 1);
			}else{
				resultMap.put(t, resultMap.get(t)+1);
			}
		}
		
		return sortByValue(resultMap);
	}
	
	/**
	 * 
	 * @param <T>
	 * @param <E>
	 * @param map
	 * @return
	 */
	public static<T,E extends Comparable<E>> LinkedHashMap<T,E> sortByValue(Map<T,E> map) {

        List<Entry<T,E>> list = new LinkedList<Entry<T,E>>(map.entrySet());
        Collections.sort(list, new Comparator<Entry<T,E>>() {

			@Override
			/**
			 * if want to sort from small to big, just modify
			 * arg1.getValue().compareTo(arg0.getValue())
			 * to
			 * arg0.getValue().compareTo(arg1.getValue())
			 */
			public int compare(Entry<T, E> arg0, Entry<T, E> arg1) {
				return arg1.getValue().compareTo(arg0.getValue());
			}
        });
        
        LinkedHashMap<T,E> result = new LinkedHashMap<T,E>();

        for (Iterator<Entry<T,E>> it = list.iterator(); it.hasNext();) {
            Entry<T,E> entry = it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
