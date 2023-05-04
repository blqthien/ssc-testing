package sys.exe.co.jp.documents;

import java.io.Serializable;

/**
 * String utilities
 *
 */
public final class StringUtils implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;
	public static final String HTML_PATTERN_REGEX = ".*\\<[^>]+>.*";
	public static final int DEFAULT_BUFFER_SIZE = (4 * 1024);
	public static final String PREFIX_CLASSPATH = "classpath:";
	public static final String PREFIX_WIRECAST_CLASSPATH = "classpath*:";
	public static final String PREFIX_SUB_CLASSPATH = "classpath:**";
	public static final String PREFIX_SUB_WIRECAST_CLASSPATH = "classpath*:**";

	/**
	 * Window special characters restriction
	 */
	private static String[] WIN_FILE_NAME_SYMBOLS;

	/**
	 * Get the window special characters restriction
	 *
	 * @return the window special characters restriction
	 */
	public static final String[] getSpecialWinFileNameSymbols() {
		if (WIN_FILE_NAME_SYMBOLS == null) {
			WIN_FILE_NAME_SYMBOLS = new String[] { "<", ">", ":", "\"", "/", "\\", "|", "?", "*", "{", "}", "\t",
					"\r\n", "\n" };
		}
		synchronized (WIN_FILE_NAME_SYMBOLS) {
			return WIN_FILE_NAME_SYMBOLS;
		}
	}

	/**
	 * Check that the given string is neither null nor of length 0. Alias of
	 * {@link org.springframework.util.StringUtils#hasLength(String)} function
	 *
	 * @param s the string to check (may be null)
	 *
	 * @return true if the string is not null and has length
	 */
	public static boolean hasLength(String s) {
		return org.springframework.util.StringUtils.hasLength(s);
	}

	/**
	 * Check that the given string is neither null nor of length 0. Alias of
	 * {@link org.springframework.util.StringUtils#hasLength(CharSequence)} function
	 *
	 * @param s the string to check (may be null)
	 *
	 * @return true if the string is not null and has length
	 */
	public static boolean hasLength(CharSequence s) {
		return org.springframework.util.StringUtils.hasLength(s);
	}

	/**
	 * Check that the given string is neither null nor of length 0. Alias of
	 * {@link org.springframework.util.StringUtils#hasLength(String)} function
	 *
	 * @param s the {@link Object#toString()} to check (may be null)
	 *
	 * @return true if the string is not null and has length
	 */
	public static boolean hasLength(Object s) {
		return (s != null && hasLength(s.toString()));
	}

	/**
	 * Check whether the given string contains actual text. Alias of
	 * {@link org.springframework.util.StringUtils#hasText(String)} function
	 *
	 * @param s the string to check (may be null)
	 *
	 * @return true if the string is not null, its length is greater than 0, and it
	 *         does not contain whitespace only
	 */
	public static boolean hasText(String s) {
		return org.springframework.util.StringUtils.hasText(s);
	}

	/**
	 * Check whether the given string contains actual text. Alias of
	 * {@link org.springframework.util.StringUtils#hasText(CharSequence)} function
	 *
	 * @param s the string to check (may be null)
	 *
	 * @return true if the string is not null, its length is greater than 0, and it
	 *         does not contain whitespace only
	 */
	public static boolean hasText(CharSequence s) {
		return org.springframework.util.StringUtils.hasText(s);
	}

	/**
	 * Check whether the given string contains actual text. Alias of
	 * {@link org.springframework.util.StringUtils#hasText(String)} function
	 *
	 * @param s the {@link Object#toString()} to check (may be null)
	 *
	 * @return true if the string is not null, its length is greater than 0, and it
	 *         does not contain whitespace only
	 */
	public static boolean hasText(Object s) {
		return (s != null && hasText(s.toString()));
	}

	/**
	 * Convert to lower case the first char in string sentence.
	 *
	 * @param text the string sentence to convert
	 *
	 * @return the string after converting
	 */
	public static String toLowerCaseFirstChar(String text) {
		if (hasText(text)) {
			text = text.substring(0, 1).toLowerCase() + text.substring(1);
		}
		return text;
	}

	/**
	 * Convert to upper case the first char in string sentence.
	 *
	 * @param text the string sentence to convert
	 *
	 * @return the string after converting
	 */
	public static String toUpperCaseFirstChar(String text) {
		if (hasText(text)) {
			text = text.substring(0, 1).toUpperCase() + text.substring(1);
		}
		return text;
	}
}
