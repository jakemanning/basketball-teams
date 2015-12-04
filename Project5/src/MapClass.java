import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

//FROM ONLINE LIBRARY
//	http://stackoverflow.com/questions/109383/how-to-sort-a-mapkey-value-on-the-values-in-java
public class MapClass {
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		Map<K, V> result = new LinkedHashMap<>();
		Stream<Entry<K, V>> st = map.entrySet().stream();

		st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));

		return result;
	}

	public static <K, V extends Comparable<? super V>> V putInMapListIfAbsent(Map<K, V> map, K k, V v) {
		v = map.putIfAbsent(k, v);

		if (v == null) {
			v = map.get(k);
		}

		return v;
	}
}