package sys.exe.co.jp.documents;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

/**
 * Number utilities
 *
 */
public final class NumberUtils implements Serializable {

	/** */
	private static final long serialVersionUID = 1L;

	/**
	 * Convert the string value to {@link BigDecimal}
	 *
	 * @param sVal        to convert
	 * @param mathContext {@link MathContext}
	 *
	 * @return {@link BigDecimal} value or null if failed
	 */
	public static BigDecimal toBigDecimal(String sVal, MathContext mathContext) {
		try {
			return new BigDecimal(Double.parseDouble(sVal), mathContext);
		} catch (Exception e) {
//			LogUtils.logError(NumberUtils.class, e.getMessage());
			return null;
		}
	}

	/**
	 * Convert the string value to {@link BigDecimal} with
	 * {@link MathContext#DECIMAL32}
	 *
	 * @param sVal to convert
	 *
	 * @return {@link BigDecimal} value or null if failed
	 */
	public static BigDecimal toBigDecimal(String sVal) {
		return toBigDecimal(sVal, MathContext.DECIMAL32);
	}

	/**
	 * Convert the object value to {@link BigDecimal}
	 *
	 * @param objVal      to convert
	 * @param mathContext {@link MathContext}
	 *
	 * @return {@link BigDecimal} value or null if failed
	 */
	public static BigDecimal toBigDecimal(Object objVal, MathContext mathContext) {
		return toBigDecimal(objVal == null ? "" : String.valueOf(objVal), mathContext);
	}

	/**
	 * Convert the object value to {@link BigDecimal} with
	 * {@link MathContext#DECIMAL32}
	 *
	 * @param objVal to convert
	 *
	 * @return {@link BigDecimal} value or null if failed
	 */
	public static BigDecimal toBigDecimal(Object objVal) {
		return toBigDecimal(objVal == null ? "" : String.valueOf(objVal));
	}

	/**
	 * Convert the string value to {@link BigInteger}
	 *
	 * @param sVal  to convert
	 * @param radix to be used in interpreting value
	 *
	 * @return {@link BigInteger} value or null if failed
	 */
	public static BigInteger toBigInteger(String sVal, int radix) {
		try {
			return new BigInteger(sVal, radix);
		} catch (Exception e) {
//			LogUtils.logError(NumberUtils.class, e.getMessage());
			return null;
		}
	}

	/**
	 * Convert the string value to {@link BigInteger} with
	 * {@link Character#MAX_RADIX}
	 *
	 * @param sVal to convert
	 *
	 * @return {@link BigInteger} value or null if failed
	 */
	public static BigInteger toBigInteger(String sVal) {
		return toBigInteger(sVal, Character.MAX_RADIX);
	}

	/**
	 * Convert the object value to {@link BigInteger}
	 *
	 * @param objVal to convert
	 * @param radix  to be used in interpreting val.
	 *
	 * @return {@link BigInteger} value or null if failed
	 */
	public static BigInteger toBigInteger(Object objVal, int radix) {
		return toBigInteger(objVal == null ? "" : String.valueOf(objVal), radix);
	}

	/**
	 * Convert the object value to {@link BigInteger} with
	 * {@link Character#MAX_RADIX}
	 *
	 * @param objVal to convert
	 *
	 * @return {@link BigInteger} value or null if failed
	 */
	public static BigInteger toBigInteger(Object objVal) {
		return toBigInteger(objVal == null ? "" : String.valueOf(objVal));
	}

	/**
	 * Convert the string value to integer
	 *
	 * @param sVal to convert
	 *
	 * @return integer value or null if failed
	 */
	public static Integer toInt(String sVal) {
		try {
			return Integer.parseInt(sVal);
		} catch (Exception e) {
//			LogUtils.logError(NumberUtils.class, e.getMessage());
			return null;
		}
	}

	/**
	 * Convert the object value to integer
	 *
	 * @param objVal to convert
	 *
	 * @return integer value or null if failed
	 */
	public static Integer toInt(Object objVal) {
		return toInt(objVal == null ? "" : String.valueOf(objVal));
	}

	/**
	 * Convert the string value to integer
	 *
	 * @param sVal   to convert
	 * @param defVal default value if failed
	 *
	 * @return integer value or default value
	 */
	public static int toInt(String sVal, int defVal) {
		Integer val = toInt(sVal);
		return (val == null ? defVal : val.intValue());
	}

	/**
	 * Convert the object value to integer
	 *
	 * @param objVal to convert
	 * @param defVal default value if failed
	 *
	 * @return integer value or null if failed
	 */
	public static int toInt(Object objVal, int defVal) {
		return toInt(objVal == null ? "" : String.valueOf(objVal), defVal);
	}

	/**
	 * Convert the string value to long
	 *
	 * @param sVal to convert
	 *
	 * @return long value or null if failed
	 */
	public static Long toLong(String sVal) {
		try {
			return Long.parseLong(sVal);
		} catch (Exception e) {
//			LogUtils.logError(NumberUtils.class, e.getMessage());
			return null;
		}
	}

	/**
	 * Convert the object value to long
	 *
	 * @param objVal to convert
	 *
	 * @return long value or null if failed
	 */
	public static Long toLong(Object objVal) {
		return toLong(objVal == null ? "" : String.valueOf(objVal));
	}

	/**
	 * Convert the string value to long
	 *
	 * @param sVal   to convert
	 * @param defVal default value if failed
	 *
	 * @return long value or default value
	 */
	public static long toLong(String sVal, long defVal) {
		Long val = toLong(sVal);
		return (val == null ? defVal : val.longValue());
	}

	/**
	 * Convert the object value to long
	 *
	 * @param objVal to convert
	 * @param defVal default value if failed
	 *
	 * @return long value or default value
	 */
	public static long toLong(Object objVal, long defVal) {
		return toLong(objVal == null ? "" : String.valueOf(objVal), defVal);
	}

	/**
	 * Convert the string value to double
	 *
	 * @param sVal to convert
	 *
	 * @return double value or null if failed
	 */
	public static Double toDouble(String sVal) {
		try {
			return Double.parseDouble(sVal);
		} catch (Exception e) {
//			LogUtils.logError(NumberUtils.class, e.getMessage());
			return null;
		}
	}

	/**
	 * Convert the object value to double
	 *
	 * @param objVal to convert
	 *
	 * @return double value or null if failed
	 */
	public static Double toDouble(Object objVal) {
		return toDouble(objVal == null ? "" : String.valueOf(objVal));
	}

	/**
	 * Convert the string value to double
	 *
	 * @param sVal   to convert
	 * @param defVal default value if failed
	 *
	 * @return double value or default value
	 */
	public static double toDouble(String sVal, double defVal) {
		Double val = toDouble(sVal);
		return (val == null ? defVal : val.doubleValue());
	}

	/**
	 * Convert the object value to double
	 *
	 * @param objVal to convert
	 * @param defVal default value if failed
	 *
	 * @return double value or default value
	 */
	public static double toDouble(Object objVal, double defVal) {
		return toDouble(objVal == null ? "" : String.valueOf(objVal), defVal);
	}

	/**
	 * Convert the string value to float
	 *
	 * @param sVal to convert
	 *
	 * @return float value or null if failed
	 */
	public static Float toFloat(String sVal) {
		try {
			return Float.parseFloat(sVal);
		} catch (Exception e) {
//			LogUtils.logError(NumberUtils.class, e.getMessage());
			return null;
		}
	}

	/**
	 * Convert the object value to float
	 *
	 * @param objVal to convert
	 *
	 * @return float value or null if failed
	 */
	public static Float toFloat(Object objVal) {
		return toFloat(objVal == null ? "" : String.valueOf(objVal));
	}

	/**
	 * Convert the string value to float
	 *
	 * @param sVal   to convert
	 * @param defVal default value if failed
	 *
	 * @return float value or default value
	 */
	public static float toFloat(String sVal, float defVal) {
		Float val = toFloat(sVal);
		return (val == null ? defVal : val.floatValue());
	}

	/**
	 * Convert the object value to float
	 *
	 * @param objVal to convert
	 * @param defVal default value if failed
	 *
	 * @return float value or default value
	 */
	public static float toFloat(Object objVal, float defVal) {
		return toFloat(objVal == null ? "" : String.valueOf(objVal), defVal);
	}

	/**
	 * Convert the string value to byte
	 *
	 * @param sVal to convert
	 *
	 * @return byte value or null if failed
	 */
	public static Byte toByte(String sVal) {
		try {
			return Byte.parseByte(sVal);
		} catch (Exception e) {
//			LogUtils.logError(NumberUtils.class, e.getMessage());
			return null;
		}
	}

	/**
	 * Convert the object value to byte
	 *
	 * @param objVal to convert
	 *
	 * @return byte value or null if failed
	 */
	public static Byte toByte(Object objVal) {
		return toByte(objVal == null ? "" : String.valueOf(objVal));
	}

	/**
	 * Convert the string value to byte
	 *
	 * @param sVal   to convert
	 * @param defVal default value if failed
	 *
	 * @return byte value or default value
	 */
	public static byte toByte(String sVal, byte defVal) {
		Byte val = toByte(sVal);
		return (val == null ? defVal : val.byteValue());
	}

	/**
	 * Convert the object value to byte
	 *
	 * @param objVal to convert
	 * @param defVal default value if failed
	 *
	 * @return byte value or default value
	 */
	public static byte toByte(Object objVal, byte defVal) {
		return toByte(objVal == null ? "" : String.valueOf(objVal), defVal);
	}

	/**
	 * Swap two value
	 *
	 * @param val1 value 1
	 * @param val2 value 2
	 */
	public static void swap(Number val1, Number val2) {
		Number tmp = val1;
		val1 = val2;
		val2 = tmp;
	}

	/**
	 * Ensure number value in range (min &lt;= max)
	 *
	 * @param min value 1
	 * @param max value 2
	 */
	public static void ensureRange(Number min, Number max) {
		if ((min != null && max == null) || (min != null && max != null && min.doubleValue() >= max.doubleValue())) {
			swap(min, max);
		}
	}

	/**
	 * Compare and find the first occurred maximum value.
	 *
	 * @param values to compare. NULL or empty for returning
	 *               {@link Integer#MAX_VALUE}
	 *
	 * @return the maximum value or {@link Integer#MAX_VALUE}
	 */
	public static int max(int... values) {
		int maxVal = Integer.MIN_VALUE;
		boolean found = !CollectionUtils.isEmpty(values);
		if (found) {
			for (int value : values) {
				if (maxVal <= value) {
					maxVal = value;
					found = true;
				}
			}
		}
		return (!found ? Integer.MAX_VALUE : maxVal);
	}

	/**
	 * Compare and find the first occurred minimum value.
	 *
	 * @param values to compare. NULL or empty for returning
	 *               {@link Integer#MIN_VALUE}
	 *
	 * @return the minimum value or {@link Integer#MIN_VALUE}
	 */
	public static int min(int... values) {
		int minVal = Integer.MAX_VALUE;
		boolean found = !CollectionUtils.isEmpty(values);
		if (found) {
			for (int value : values) {
				if (minVal >= value) {
					minVal = value;
					found = true;
				}
			}
		}
		return (!found ? Integer.MIN_VALUE : minVal);
	}
}
