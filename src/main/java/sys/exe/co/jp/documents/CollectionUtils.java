package sys.exe.co.jp.documents;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 *
 */
public final class CollectionUtils implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	// -------------------------------------------------
	// COLLECTION
	// -------------------------------------------------

	/**
	 * Find the common element type of the given Collection, if any. Alias of
	 * {@link org.springframework.util.CollectionUtils#findCommonElementType(Collection)}
	 * function
	 *
	 * @param collection to check
	 *
	 * @return the common element type, or null if no clear common type has been
	 *         found (or the collection was empty)
	 */
	public static Class<?> findCommonElementType(Collection<?> collection) {
		return (collection == null || isEmpty(collection) ? null
				: org.springframework.util.CollectionUtils.findCommonElementType(collection));
	}

	/**
	 * Return true if the supplied array/collection/map is null or empty. Otherwise,
	 * return false.
	 *
	 * @param obj to check
	 *
	 * @return whether the given array/collection/map is empty
	 */
	public static boolean isEmpty(Object obj) {
		return (obj == null || (obj.getClass().isArray() && Array.getLength(obj) <= 0)
				|| (isCollection(obj) && isEmpty((Collection<?>) obj)) || (isMap(obj) && isEmpty((Map<?, ?>) obj)));
	}

	/**
	 * Gets a boolean value indicating that specified object whether is map
	 *
	 * @param map the object to check
	 *
	 * @return true for map; else false
	 */
	public static boolean isMap(Object map) {
		return BeanUtils.isInstanceOf(map, Map.class);
	}

	/**
	 * Gets a boolean value indicating that specified object whether is collection
	 *
	 * @param collection the object to check
	 *
	 * @return true for collection; else false
	 */
	public static boolean isCollection(Object collection) {
		return BeanUtils.isInstanceOf(collection, Collection.class);
	}

	/**
	 * Return true if the supplied collection is null or empty. Otherwise, return
	 * false. Alias of
	 * {@link org.springframework.util.CollectionUtils#isEmpty(Collection)} function
	 *
	 * @param collection to check
	 *
	 * @return whether the given collection is empty
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return org.springframework.util.CollectionUtils.isEmpty(collection);
	}

	/**
	 * Get the size of specified collection
	 *
	 * @param collection to parse
	 *
	 * @return size of collection or 0 if empty
	 */
	public static int getSize(Collection<?> collection) {
		return (collection == null || collection.size() <= 0 ? 0 : collection.size());
	}

	/**
	 * Get a boolean value indicating the specified collection size whether is
	 * equals with the specified size
	 *
	 * @param collection the collection to check
	 * @param size       the size to compare with collection's size
	 *
	 * @return true for equals; else false
	 */
	public static boolean isElementsNumber(Collection<?> collection, int size) {
		return (!isEmpty(collection) && collection.size() == size);
	}

	/**
	 * Get a boolean value indicating the specified collection size whether is
	 * greater than with the specified size
	 *
	 * @param collection the collection to check
	 * @param size       the size to compare with collection's size
	 * @param equals     specify whether checking collection's size equals with the
	 *                   specified size
	 *
	 * @return true for equals or greater than the specified size; else false
	 */
	public static boolean isElementsNumberGreaterThan(Collection<?> collection, int size, boolean equals) {
		return (!isEmpty(collection)
				&& ((equals && collection.size() >= size) || (!equals && collection.size() > size)));
	}

	/**
	 * Get a boolean value indicating the specified collection size whether is less
	 * than with the specified size
	 *
	 * @param collection the collection to check
	 * @param size       the size to compare with collection's size
	 * @param equals     specify whether checking collection's size equals with the
	 *                   specified size
	 *
	 * @return true for equals or less than the specified size; else false
	 */
	public static boolean isElementsNumberLessThan(Collection<?> collection, int size, boolean equals) {
		return (!isEmpty(collection)
				&& ((equals && collection.size() <= size) || (!equals && collection.size() < size)));
	}

	/**
	 * Get the first occurred index of the specified value in collection
	 *
	 * @param collection to check
	 * @param value      to find by {@link Object#equals(Object)}
	 *
	 * @return index of value in collection (base on 0) or -1 if not found
	 */
	public static int indexOf(Collection<?> collection, Object value) {
		if (!isEmpty(collection) && collection.contains(value)) {
			int idx = -1;
			for (final Iterator<?> it = collection.iterator(); it.hasNext();) {
				Object item = it.next();
				idx++;
				if (item == null && value == null)
					break;
				if (item != null && value != null && (item == value || item.equals(value))) {
					return idx;
				}
			}
		}
		return -1;
	}

	/**
	 * Get the item at the specified index in collection
	 *
	 * @param collection to get
	 * @param idx        of item. base on 0
	 *
	 * @return the item at the specified index of NULL if not found
	 */
	public static Object get(Collection<?> collection, int idx) {
		int size = getSize(collection);
		if (!isEmpty(collection) && 0 <= idx && idx < size) {
			for (final Iterator<?> it = collection.iterator(); it.hasNext();) {
				Object item = it.next();
				if (idx <= 0)
					return item;
				idx--;
			}
		}
		return null;
	}

	// -------------------------------------------------
	// LIST
	// -------------------------------------------------

	/**
	 * Return true if the supplied list is null or empty. Otherwise, return false.
	 * Alias of {@link org.springframework.util.CollectionUtils#isEmpty(Collection)}
	 * function
	 *
	 * @param list to check
	 *
	 * @return whether the given list is empty
	 */
	public static boolean isEmpty(List<?> list) {
		return org.springframework.util.CollectionUtils.isEmpty(list);
	}

	/**
	 * Get the size of specified list
	 *
	 * @param list to parse
	 *
	 * @return size of list or 0 if empty
	 */
	public static int getSize(List<?> list) {
		return (list == null || list.size() <= 0 ? 0 : list.size());
	}

	/**
	 * Get a boolean value indicating the specified list size whether is equals with
	 * the specified size
	 *
	 * @param list the list to check
	 * @param size the size to compare with list's size
	 *
	 * @return true for equals; else false
	 */
	public static boolean isElementsNumber(List<?> list, int size) {
		return (!isEmpty(list) && list.size() == size);
	}

	/**
	 * Get a boolean value indicating the specified list size whether is greater
	 * than with the specified size
	 *
	 * @param list   the list to check
	 * @param size   the size to compare with list's size
	 * @param equals specify whether checking list's size equals with the specified
	 *               size
	 *
	 * @return true for equals or greater than the specified size; else false
	 */
	public static boolean isElementsNumberGreaterThan(List<?> list, int size, boolean equals) {
		return (!isEmpty(list) && ((equals && list.size() >= size) || (!equals && list.size() > size)));
	}

	/**
	 * Get a boolean value indicating the specified list size whether is less than
	 * with the specified size
	 *
	 * @param list   the list to check
	 * @param size   the size to compare with list's size
	 * @param equals specify whether checking list's size equals with the specified
	 *               size
	 *
	 * @return true for equals or less than the specified size; else false
	 */
	public static boolean isElementsNumberLessThan(List<?> list, int size, boolean equals) {
		return (!isEmpty(list) && ((equals && list.size() <= size) || (!equals && list.size() < size)));
	}

	/**
	 * Get the first occurred index of the specified value in list
	 *
	 * @param list  to check
	 * @param value to find by {@link List#indexOf(Object)}
	 *
	 * @return index of value in list (base on 0) or -1 if not found
	 */
	public static int indexOf(List<?> list, Object value) {
		return (isEmpty(list) ? -1 : list.indexOf(value));
	}

	/**
	 * Get the item at the specified index in list
	 *
	 * @param list to check
	 * @param idx  of item. base on 0
	 *
	 * @return the item at the specified index or NULL if not found
	 */
	public static Object get(List<?> list, int idx) {
		int size = getSize(list);
		return (0 <= idx && idx < size ? list.get(idx) : null);
	}

	// -------------------------------------------------
	// MAP
	// -------------------------------------------------

	/**
	 * Return true if the supplied map is null or empty. Otherwise, return false.
	 * Alias of {@link org.springframework.util.CollectionUtils#isEmpty(Map)}
	 * function
	 *
	 * @param map to check
	 *
	 * @return whether the given map is empty
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return org.springframework.util.CollectionUtils.isEmpty(map);
	}

	/**
	 * Get the size of specified map
	 *
	 * @param map to parse
	 *
	 * @return size of map or 0 if empty
	 */
	public static int getSize(Map<?, ?> map) {
		return (map == null || map.size() <= 0 ? 0 : map.size());
	}

	/**
	 * Get a boolean value indicating the specified map size whether is equals with
	 * the specified size
	 *
	 * @param map  the map to check
	 * @param size the size to compare with map's size
	 *
	 * @return true for equals; else false
	 */
	public static boolean isElementsNumber(Map<?, ?> map, int size) {
		return (!isEmpty(map) && map.size() == size);
	}

	/**
	 * Get a boolean value indicating the specified map size whether is greater than
	 * with the specified size
	 *
	 * @param map    the map to check
	 * @param size   the size to compare with map's size
	 * @param equals specify whether checking map's size equals with the specified
	 *               size
	 *
	 * @return true for equals or greater than the specified size; else false
	 */
	public static boolean isElementsNumberGreaterThan(Map<?, ?> map, int size, boolean equals) {
		return (!isEmpty(map) && ((equals && map.size() >= size) || (!equals && map.size() > size)));
	}

	/**
	 * Get a boolean value indicating the specified map size whether is less than
	 * with the specified size
	 *
	 * @param map    the map to check
	 * @param size   the size to compare with map's size
	 * @param equals specify whether checking map's size equals with the specified
	 *               size
	 *
	 * @return true for equals or less than the specified size; else false
	 */
	public static boolean isElementsNumberLessThan(Map<?, ?> map, int size, boolean equals) {
		return (!isEmpty(map) && ((equals && map.size() <= size) || (!equals && map.size() < size)));
	}

	// -------------------------------------------------
	// SET
	// -------------------------------------------------

	/**
	 * Return true if the supplied set is null or empty. Otherwise, return false.
	 * Alias of {@link org.springframework.util.CollectionUtils#isEmpty(Collection)}
	 * function
	 *
	 * @param set to check
	 *
	 * @return whether the given set is empty
	 */
	public static boolean isEmpty(Set<?> set) {
		return (set == null || set.size() <= 0);
	}

	/**
	 * Get the size of specified set
	 *
	 * @param set to parse
	 *
	 * @return size of set or 0 if empty
	 */
	public static int getSize(Set<?> set) {
		return (set == null || set.size() <= 0 ? 0 : set.size());
	}

	/**
	 * Get a boolean value indicating the specified set size whether is equals with
	 * the specified size
	 *
	 * @param set  the set to check
	 * @param size the size to compare with set's size
	 *
	 * @return true for equals; else false
	 */
	public static boolean isElementsNumber(Set<?> set, int size) {
		return (!isEmpty(set) && set.size() == size);
	}

	/**
	 * Get a boolean value indicating the specified set size whether is greater than
	 * with the specified size
	 *
	 * @param set    the set to check
	 * @param size   the size to compare with set's size
	 * @param equals specify whether checking set's size equals with the specified
	 *               size
	 *
	 * @return true for equals or greater than the specified size; else false
	 */
	public static boolean isElementsNumberGreaterThan(Set<?> set, int size, boolean equals) {
		return (!isEmpty(set) && ((equals && set.size() >= size) || (!equals && set.size() > size)));
	}

	/**
	 * Get a boolean value indicating the specified set size whether is less than
	 * with the specified size
	 *
	 * @param set    the set to check
	 * @param size   the size to compare with set's size
	 * @param equals specify whether checking set's size equals with the specified
	 *               size
	 *
	 * @return true for equals or less than the specified size; else false
	 */
	public static boolean isElementsNumberLessThan(Set<?> set, int size, boolean equals) {
		return (!isEmpty(set) && ((equals && set.size() <= size) || (!equals && set.size() < size)));
	}

	/**
	 * Get the first occurred index of the specified value in set
	 *
	 * @param set   to check
	 * @param value to find by {@link Object#equals(Object)}
	 *
	 * @return index of value in set (base on 0) or -1 if not found
	 */
	public static int indexOf(Set<?> set, Object value) {
		if (!isEmpty(set) && set.contains(value)) {
			int idx = -1;
			for (final Iterator<?> it = set.iterator(); it.hasNext();) {
				Object item = it.next();
				idx++;
				if (item == null && value == null)
					break;
				if (item != null && value != null && (item == value || item.equals(value))) {
					return idx;
				}
			}
		}
		return -1;
	}

	/**
	 * Get the item at the specified index in set
	 *
	 * @param set to check
	 * @param idx of item. base on 0
	 *
	 * @return the item at the specified index or NULL if not found
	 */
	public static Object get(Set<?> set, int idx) {
		int size = getSize(set);
		if (!isEmpty(set) && 0 <= idx && idx < size) {
			for (final Iterator<?> it = set.iterator(); it.hasNext();) {
				Object item = it.next();
				if (idx <= 0)
					return item;
				idx--;
			}
		}
		return null;
	}

	// -------------------------------------------------
	// ARRAY
	// -------------------------------------------------

	/**
	 * Return true if the supplied array is null or empty. Otherwise, return false.
	 *
	 * @param array to check
	 *
	 * @return whether the given array is empty
	 */
	public static boolean isEmpty(Object[] array) {
		return (array == null || array.length <= 0);
	}

	/**
	 * Get the size of specified array
	 *
	 * @param array to parse
	 *
	 * @return size of array or 0 if empty
	 */
	public static int getSize(Object[] array) {
		return (array == null || array.length <= 0 ? 0 : array.length);
	}

	/**
	 * Get a boolean value indicating the specified array length whether is equals
	 * with the specified size
	 *
	 * @param array the array to check
	 * @param size  the size to compare with array's length
	 *
	 * @return true for equals; else false
	 */
	public static boolean isElementsNumber(Object[] array, int size) {
		return (!isEmpty(array) && array.length == size);
	}

	/**
	 * Get a boolean value indicating the specified array length whether is greater
	 * than with the specified size
	 *
	 * @param array  the array to check
	 * @param size   the size to compare with array's length
	 * @param equals specify whether checking array's length equals with the
	 *               specified size
	 *
	 * @return true for equals or greater than the specified size; else false
	 */
	public static boolean isElementsNumberGreaterThan(Object[] array, int size, boolean equals) {
		return (!isEmpty(array) && ((equals && array.length >= size) || (!equals && array.length > size)));
	}

	/**
	 * Get a boolean value indicating the specified array length whether is less
	 * than with the specified size
	 *
	 * @param array  the array to check
	 * @param size   the size to compare with array's length
	 * @param equals specify whether checking array's length equals with the
	 *               specified size
	 *
	 * @return true for equals or less than the specified size; else false
	 */
	public static boolean isElementsNumberLessThan(Object[] array, int size, boolean equals) {
		return (!isEmpty(array) && ((equals && array.length <= size) || (!equals && array.length < size)));
	}

	/**
	 * Get the first occurred index of the specified value in array
	 *
	 * @param array to check
	 * @param value to find by {@link Object#equals(Object)}
	 *
	 * @return index of value in array (base on 0) or -1 if not found
	 */
	public static int indexOf(Object[] array, Object value) {
		if (!isEmpty(array)) {
			int idx = -1;
			for (Object item : array) {
				idx++;
				if (item == null && value == null)
					break;
				if (item != null && value != null && (item == value || item.equals(value))) {
					return idx;
				}
			}
		}
		return -1;
	}

	/**
	 * Get the item at the specified index in array
	 *
	 * @param array to check
	 * @param idx   of item. base on 0
	 *
	 * @return the item at the specified index or NULL if not found
	 */
	public static Object get(Object[] array, int idx) {
		int size = getSize(array);
		return (0 <= idx && idx < size ? array[idx] : null);
	}

	/**
	 * Convert object values array to objects collection
	 *
	 * @param <T>    item class type
	 * @param values to convert
	 *
	 * @return objects collection
	 */
	public static <T> List<T> toList(T[] values) {
		List<T> list = new LinkedList<T>();
		if (!isEmpty(values)) {
			list.addAll(Arrays.asList(values));
		}
		return list;
	}

}
