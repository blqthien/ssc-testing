package sys.exe.co.jp.documents;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Date1904Support;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.util.LocaleUtil;
import org.springframework.util.Assert;

public class ExcelUtils {

  private static DataFormatter formatter = new DataFormatter();
  // redmine date format
  private static String DATE_FORMAT_STRING = "MM/dd/yyyy";
  private static String EMPTY = "";

  /**
   * Get cell value
   *
   * @param xlCell {@link Cell}
   *
   * @return cell value
   */
  public static String getCellStringValue(Cell xlCell) {
    String cellVal = null;
    if (xlCell != null) {
      try {
        switch (xlCell.getCellType()) {
          case NUMERIC: {
            cellVal = NumberToTextConverter.toText(xlCell.getNumericCellValue());
            break;
          }
          case STRING: {
            cellVal = xlCell.getStringCellValue();
            break;
          }
          case FORMULA: {
            try {
              Workbook workbook = xlCell.getSheet().getWorkbook();
              CreationHelper creationHelper = workbook.getCreationHelper();
              FormulaEvaluator evaluator = creationHelper.createFormulaEvaluator();
              cellVal = getFormulaValue(evaluator, xlCell);
            } catch (Exception e) {
              cellVal = xlCell.getCellFormula();
            }
            break;
          }
          default:
            break;
        }
      } catch (Exception e) {
        // LogUtils.logError(ExcelUtils.class, e.getMessage());
        cellVal = null;
      }
    }
    return cellVal;
  }

  /**
   *
   * @param evaluator {@link FormulaEvaluator}
   * @param cell {@link Cell}
   * @return cell value
   */
  public static String getFormulaValue(FormulaEvaluator evaluator, Cell cell) {

    CellValue cellValue = evaluator.evaluate(cell);

    CellStyle style = cell.getCellStyle();

    switch (cellValue.getCellType()) {
      case NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) {
          // https://mlog.club/article/4055764
          if (!DATE_FORMAT_STRING.matches(".*(s\\.0{1,3}).*")) {
            // the format string does not show milliseconds
            boolean use1904Windowing = false;
            if (cell != null && cell.getSheet().getWorkbook() instanceof Date1904Support)
              use1904Windowing = ((Date1904Support) cell.getSheet().getWorkbook()).isDate1904();
            boolean roundSeconds = true; // we round seconds
            Date date = DateUtil.getJavaDate(cell.getNumericCellValue(), use1904Windowing,
                LocaleUtil.getUserTimeZone(), roundSeconds);
            double value = DateUtil.getExcelDate(date);

            return formatter.formatRawCellContents(value, style.getDataFormat(), DATE_FORMAT_STRING,
                use1904Windowing);
          }

          return formatter.formatCellValue(cell, evaluator);

        }
        return String.valueOf(cellValue.getNumberValue());

      case STRING:
        return cellValue.getStringValue();
      default:
        break;
    }
    return EMPTY;
  }

  /**
   *
   * @param evaluator {@link FormulaEvaluator}
   * @param cell {@link Cell}
   * @return cell value
   */
  public static String getFormulaDateValue(FormulaEvaluator evaluator, Cell cell) {

    CellValue cellValue = evaluator.evaluate(cell);

    CellStyle style = cell.getCellStyle();

    switch (cellValue.getCellType()) {
      case NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) {
          // https://mlog.club/article/4055764
          if (!DATE_FORMAT_STRING.matches(".*(s\\.0{1,3}).*")) {
            // the format string does not show milliseconds
            boolean use1904Windowing = false;
            if (cell != null && cell.getSheet().getWorkbook() instanceof Date1904Support)
              use1904Windowing = ((Date1904Support) cell.getSheet().getWorkbook()).isDate1904();
            boolean roundSeconds = true; // we round seconds
            Date date = DateUtil.getJavaDate(cell.getNumericCellValue(), use1904Windowing,
                LocaleUtil.getUserTimeZone(), roundSeconds);
            double value = DateUtil.getExcelDate(date);

            return formatter.formatRawCellContents(value, style.getDataFormat(), DATE_FORMAT_STRING,
                use1904Windowing);
          }

          return formatter.formatCellValue(cell, evaluator);

        }
        return String.valueOf(cellValue.getNumberValue());

      default:
        break;
    }
    return EMPTY;
  }

  /**
   * Get cell value
   *
   * @param sheet {@link Sheet}
   * @param row row index. 0-based row index
   * @param col column index. 0-based column index
   *
   * @return cell value
   */
  public static String getCellStringValue(Sheet sheet, int row, int col) {
    String cellVal = null;
    if (sheet != null && 0 <= row && 0 <= col) {
      Row xlRow = sheet.getRow(row);
      if (xlRow != null) {
        cellVal = getCellStringValue(xlRow.getCell(col));
      }
    }
    return cellVal;
  }

  /**
   * Get cell value
   *
   * @param xlCell {@link Cell}
   *
   * @return cell value
   */
  public static Integer getCellIntValue(Cell xlCell) {
    String cellVal = getCellStringValue(xlCell);
    return (!StringUtils.hasText(cellVal) ? null : NumberUtils.toInt(cellVal));
  }

  /**
   * Get cell value
   *
   * @param sheet {@link Sheet}
   * @param row row index. 0-based row index
   * @param col column index. 0-based column index
   *
   * @return cell value
   */
  public static Integer getCellIntValue(Sheet sheet, int row, int col) {
    String cellVal = getCellStringValue(sheet, row, col);
    return (!StringUtils.hasText(cellVal) ? null : NumberUtils.toInt(cellVal));
  }

  /**
   * Get cell value
   *
   * @param xlCell {@link Cell}
   * @param defVal default value
   *
   * @return cell value
   */
  public static int getCellIntValue(Cell xlCell, int defVal) {
    String cellVal = getCellStringValue(xlCell);
    return (!StringUtils.hasText(cellVal) ? defVal : NumberUtils.toInt(cellVal, defVal));
  }

  /**
   * Get cell value
   *
   * @param sheet {@link Sheet}
   * @param row row index. 0-based row index
   * @param col column index. 0-based column index
   * @param defVal default value
   *
   * @return cell value
   */
  public static int getCellIntValue(Sheet sheet, int row, int col, int defVal) {
    String cellVal = getCellStringValue(sheet, row, col);
    return (!StringUtils.hasText(cellVal) ? defVal : NumberUtils.toInt(cellVal, defVal));
  }

  /**
   * Get cell value
   *
   * @param xlCell {@link Cell}
   *
   * @return cell value
   */
  public static Double getCellDoubleValue(Cell xlCell) {
    String cellVal = getCellStringValue(xlCell);
    return (!StringUtils.hasText(cellVal) ? null : NumberUtils.toDouble(cellVal));
  }

  /**
   * Get cell value
   *
   * @param sheet {@link Sheet}
   * @param row row index. 0-based row index
   * @param col column index. 0-based column index
   *
   * @return cell value
   */
  public static Double getCellDoubleValue(Sheet sheet, int row, int col) {
    String cellVal = getCellStringValue(sheet, row, col);
    return (!StringUtils.hasText(cellVal) ? null : NumberUtils.toDouble(cellVal));
  }

  /**
   * Get cell value
   *
   * @param xlCell {@link Cell}
   * @param defVal default value
   *
   * @return cell value
   */
  public static double getCellDoubleValue(Cell xlCell, double defVal) {
    String cellVal = getCellStringValue(xlCell);
    return (!StringUtils.hasText(cellVal) ? defVal : NumberUtils.toDouble(cellVal, defVal));
  }

  /**
   * Get cell value
   *
   * @param sheet {@link Sheet}
   * @param row row index. 0-based row index
   * @param col column index. 0-based column index
   * @param defVal default value
   *
   * @return cell value
   */
  public static double getCellDoubleValue(Sheet sheet, int row, int col, double defVal) {
    String cellVal = getCellStringValue(sheet, row, col);
    return (!StringUtils.hasText(cellVal) ? defVal : NumberUtils.toDouble(cellVal, defVal));
  }

  /**
   * Get cell value
   *
   * @param xlCell {@link Cell}
   *
   * @return cell value
   */
  public static BigDecimal getCellBigDecimalValue(Cell xlCell) {
    String cellVal = getCellStringValue(xlCell);
    return (!StringUtils.hasText(cellVal) ? null : NumberUtils.toBigDecimal(cellVal));
  }

  /**
   * Get cell value
   *
   * @param sheet {@link Sheet}
   * @param row row index. 0-based row index
   * @param col column index. 0-based column index
   *
   * @return cell value
   */
  public static BigDecimal getCellBigDecimalValue(Sheet sheet, int row, int col) {
    String cellVal = getCellStringValue(sheet, row, col);
    return (!StringUtils.hasText(cellVal) ? null : NumberUtils.toBigDecimal(cellVal));
  }

  /**
   * Get cell value
   *
   * @param xlCell {@link Cell}
   *
   * @return cell value
   */
  public static BigInteger getCellBigIntegerValue(Cell xlCell) {
    String cellVal = getCellStringValue(xlCell);
    return (!StringUtils.hasText(cellVal) ? null : NumberUtils.toBigInteger(cellVal));
  }

  /**
   * Get cell value
   *
   * @param sheet {@link Sheet}
   * @param row row index. 0-based row index
   * @param col column index. 0-based column index
   *
   * @return cell value
   */
  public static BigInteger getCellBigIntegerValue(Sheet sheet, int row, int col) {
    String cellVal = getCellStringValue(sheet, row, col);
    return (!StringUtils.hasText(cellVal) ? null : NumberUtils.toBigInteger(cellVal));
  }

  /**
   * Get cell value
   *
   * @param xlCell {@link Cell}
   *
   * @return cell value
   */
  public static Long getCellLongValue(Cell xlCell) {
    String cellVal = getCellStringValue(xlCell);
    return (!StringUtils.hasText(cellVal) ? null : NumberUtils.toLong(cellVal));
  }

  /**
   * Get cell value
   *
   * @param sheet {@link Sheet}
   * @param row row index. 0-based row index
   * @param col column index. 0-based column index
   *
   * @return cell value
   */
  public static Long getCellLongValue(Sheet sheet, int row, int col) {
    String cellVal = getCellStringValue(sheet, row, col);
    return (!StringUtils.hasText(cellVal) ? null : NumberUtils.toLong(cellVal));
  }

  /**
   * Get cell value
   *
   * @param xlCell {@link Cell}
   * @param defVal default value
   *
   * @return cell value
   */
  public static long getCellLongValue(Cell xlCell, long defVal) {
    String cellVal = getCellStringValue(xlCell);
    return (!StringUtils.hasText(cellVal) ? defVal : NumberUtils.toLong(cellVal, defVal));
  }

  /**
   * Get cell value
   *
   * @param sheet {@link Sheet}
   * @param row row index. 0-based row index
   * @param col column index. 0-based column index
   * @param defVal default value
   *
   * @return cell value
   */
  public static long getCellLongValue(Sheet sheet, int row, int col, long defVal) {
    String cellVal = getCellStringValue(sheet, row, col);
    return (!StringUtils.hasText(cellVal) ? defVal : NumberUtils.toLong(cellVal, defVal));
  }

  /**
   * Get cell value
   *
   * @param xlCell {@link Cell}
   *
   * @return cell value
   */
  public static Float getCellFloatValue(Cell xlCell) {
    String cellVal = getCellStringValue(xlCell);
    return (!StringUtils.hasText(cellVal) ? null : NumberUtils.toFloat(cellVal));
  }

  /**
   * Get cell value
   *
   * @param sheet {@link Sheet}
   * @param row row index. 0-based row index
   * @param col column index. 0-based column index
   *
   * @return cell value
   */
  public static Float getCellFloatValue(Sheet sheet, int row, int col) {
    String cellVal = getCellStringValue(sheet, row, col);
    return (!StringUtils.hasText(cellVal) ? null : NumberUtils.toFloat(cellVal));
  }

  /**
   * Get cell value
   *
   * @param xlCell {@link Cell}
   * @param defVal default value
   *
   * @return cell value
   */
  public static float getCellFloatValue(Cell xlCell, float defVal) {
    String cellVal = getCellStringValue(xlCell);
    return (!StringUtils.hasText(cellVal) ? defVal : NumberUtils.toFloat(cellVal, defVal));
  }

  /**
   * Get cell value
   *
   * @param sheet {@link Sheet}
   * @param row row index. 0-based row index
   * @param col column index. 0-based column index
   * @param defVal default value
   *
   * @return cell value
   */
  public static float getCellFloatValue(Sheet sheet, int row, int col, float defVal) {
    String cellVal = getCellStringValue(sheet, row, col);
    return (!StringUtils.hasText(cellVal) ? defVal : NumberUtils.toFloat(cellVal, defVal));
  }

  /**
   * Get cell value
   *
   * @param xlCell {@link Cell}
   *
   * @return cell value
   */
  public static Byte getCellByteValue(Cell xlCell) {
    String cellVal = getCellStringValue(xlCell);
    return (!StringUtils.hasText(cellVal) ? null : NumberUtils.toByte(cellVal));
  }

  /**
   * Get cell value
   *
   * @param sheet {@link Sheet}
   * @param row row index. 0-based row index
   * @param col column index. 0-based column index
   *
   * @return cell value
   */
  public static Byte getCellByteValue(Sheet sheet, int row, int col) {
    String cellVal = getCellStringValue(sheet, row, col);
    return (!StringUtils.hasText(cellVal) ? null : NumberUtils.toByte(cellVal));
  }

  /**
   * Get cell value
   *
   * @param xlCell {@link Cell}
   * @param defVal default value
   *
   * @return cell value
   */
  public static byte getCellByteValue(Cell xlCell, byte defVal) {
    String cellVal = getCellStringValue(xlCell);
    return (!StringUtils.hasText(cellVal) ? defVal : NumberUtils.toByte(cellVal, defVal));
  }

  /**
   * Get cell value
   *
   * @param sheet {@link Sheet}
   * @param row row index. 0-based row index
   * @param col column index. 0-based column index
   * @param defVal default value
   *
   * @return cell value
   */
  public static byte getCellByteValue(Sheet sheet, int row, int col, byte defVal) {
    String cellVal = getCellStringValue(sheet, row, col);
    return (!StringUtils.hasText(cellVal) ? defVal : NumberUtils.toByte(cellVal, defVal));
  }

  /**
   * Get cell value
   *
   * @param xlCell {@link Cell}
   * @param tryFormat the date format pattern while excel not format cell as date
   *
   * @return cell value
   */
  public static Date getCellDateValue(Cell xlCell, String tryFormat) {
    Date dt = getCellDateValue(xlCell);
    // try with format pattern
    if (dt == null && StringUtils.hasText(tryFormat)) {
      String cellVal = getCellStringValue(xlCell);
      if (StringUtils.hasText(cellVal)) {
        dt = DateUtils.toTimestamp(cellVal, tryFormat);
      }
    }
    return dt;
  }

  /**
   * Get cell value
   *
   * @param sheet {@link Sheet}
   * @param row row index. 0-based row index
   * @param col column index. 0-based column index
   * @param tryFormat the date format pattern while excel not format cell as date
   *
   * @return cell value
   */
  public static Date getCellDateValue(Sheet sheet, int row, int col, String tryFormat) {
    Date dt = getCellDateValue(sheet, row, col);
    // try with format pattern
    if (dt == null && StringUtils.hasText(tryFormat)) {
      String cellVal = getCellStringValue(sheet, row, col);
      if (StringUtils.hasText(cellVal)) {
        dt = DateUtils.toTimestamp(cellVal, tryFormat);
      }
    }
    return dt;
  }

  /**
   * Get cell value with excel has been formatted as date
   *
   * @param xlCell {@link Cell}
   *
   * @return cell value
   */
  @SuppressWarnings("deprecation")
  public static Date getCellDateValue(Cell xlCell) {
    Date dt = null;
    String strDt = null;
    if (xlCell != null) {
      try {
        switch (xlCell.getCellType()) {
          case NUMERIC: {
            if (DateUtil.isCellDateFormatted(xlCell)) {
              dt = xlCell.getDateCellValue();
            }
            break;
          }
          case FORMULA: {
            try {
              Workbook workbook = xlCell.getSheet().getWorkbook();
              CreationHelper creationHelper = workbook.getCreationHelper();
              FormulaEvaluator evaluator = creationHelper.createFormulaEvaluator();
              strDt = getFormulaDateValue(evaluator, xlCell);
              if (!org.springframework.util.StringUtils.isEmpty(strDt)) {
                dt = new Date(strDt);
              }
            } catch (Exception e) {
            }
            break;
          }
          default:
            break;
        }
      } catch (Exception e) {
        // LogUtils.logError(ExcelUtils.class, e.getMessage());
        dt = null;
      }
    } else {

    }
    return dt;
  }

  /**
   * Get cell value with excel has been formatted as date
   *
   * @param sheet {@link Sheet}
   * @param row row index. 0-based row index
   * @param col column index. 0-based column index
   *
   * @return cell value
   */
  public static Date getCellDateValue(Sheet sheet, int row, int col) {
    Date dt = null;
    if (sheet != null && 0 <= row && 0 <= col) {
      Row xlRow = sheet.getRow(row);
      if (xlRow != null) {
        dt = getCellDateValue(xlRow.getCell(col));
      }
    }
    return dt;
  }

  /**
   * Write the specified data list to the excel {@link Sheet}
   *
   * @param <T> data element class type
   * @param sheet to write
   * @param startRow the start row to read. 0-based row index
   * @param endRow the end row to read. 0-based row index. -1 for reading to end
   * @param startColumn the start column to read. 0-based row index
   * @param endColumn the end column to read. 0-based row index. -1 for reading to end
   * @param dataClass data class
   * @param properties data property name to apply property value
   *
   * @return the data entities list (by the specified data class) or empty if failed
   */
  public static <T> List<T> readFromSheet(Sheet sheet, int startRow, int endRow, int startColumn,
      int endColumn, Class<T> dataClass, String... properties) {
    Assert.notNull(sheet, "Sheet");
    Assert.notNull(dataClass, "DataClass");

    // detect parameter
    Integer iStartRow = NumberUtils.max(new int[] {startRow, 0});
    Integer iEndRow = NumberUtils.max(new int[] {endRow, -1});
    Integer iStartColumn = NumberUtils.max(new int[] {startColumn, 0});
    Integer iEndColumn = NumberUtils.max(new int[] {endColumn, -1});
    if (iEndRow.intValue() <= -1)
      iEndRow = sheet.getLastRowNum();

    // make valid parameters
    iEndRow = (iEndRow.intValue() < 0 ? sheet.getLastRowNum() : iEndRow);
    if (iStartRow.intValue() > iEndRow.intValue())
      NumberUtils.swap(iStartRow, iEndRow);
    if (iEndColumn.intValue() >= 0 && iStartColumn.intValue() > iEndColumn.intValue()) {
      NumberUtils.swap(iStartColumn, iEndColumn);
    }

    // read data
    List<T> entities = new LinkedList<T>();
    if (iStartRow.intValue() <= sheet.getLastRowNum()) {
      List<List<Object>> dataLst = new LinkedList<List<Object>>();
      try {
        for (int i = iStartRow.intValue(); i <= iEndRow.intValue(); i++) {
          List<Object> dataRow = new LinkedList<Object>();
          // parse cell value
          for (int j = iStartColumn.intValue(); j <= iEndColumn.intValue(); j++) {
            dataRow.add(getCellValue(sheet, i, j));
          }
          dataLst.add(dataRow);
        }

        // map data matrix to data class
        if (!CollectionUtils.isEmpty(dataLst)) {
          entities.addAll(BeanUtils.asDataList(dataClass, dataLst, properties));
        }
      } catch (Exception e) {
        // LogUtils.logError(ExcelUtils.class, e.getMessage());
        if (!CollectionUtils.isEmpty(dataLst))
          dataLst.clear();
        if (!CollectionUtils.isEmpty(entities))
          entities.clear();
        throw e;
      }
    }
    // else
    // LogUtils.logWarn(ExcelUtils.class, "Overflow sheet latest data row!");
    return entities;
  }

  /**
   * Get cell value
   *
   * @param sheet {@link Sheet}
   * @param row row index. 0-based row index
   * @param col column index. 0-based column index
   *
   * @return cell value
   */
  public static Object getCellValue(Sheet sheet, int row, int col) {
    Object value = null;
    if (sheet != null && row >= 0 && col >= 0) {
      // parse cell row
      Row xlRow = sheet.getRow(row);
      if (xlRow != null) {
        // parse cell
        value = getCellValue(xlRow.getCell(col));
      }
    }
    return value;
  }

  /**
   * Get cell value
   *
   * @param xlCell {@link Cell}
   *
   * @return cell value
   */
  public static Object getCellValue(Cell xlCell) {
    Object value = null;
    if (xlCell != null) {
      try {
        switch (xlCell.getCellType()) {
          case BOOLEAN:
            value = xlCell.getBooleanCellValue();
            break;
          case NUMERIC:
            if (DateUtil.isCellDateFormatted(xlCell)) {
              value = xlCell.getDateCellValue();
            } else {
              value = xlCell.getNumericCellValue();
            }
            break;
          case STRING:
            value = xlCell.getStringCellValue();
            break;
          case FORMULA:
            try {
              Workbook workbook = xlCell.getSheet().getWorkbook();
              CreationHelper creationHelper = workbook.getCreationHelper();
              FormulaEvaluator evaluator = creationHelper.createFormulaEvaluator();
              CellValue cellValue = evaluator.evaluate(xlCell);
              value = cellValue.getStringValue();
            } catch (Exception e) {
              value = xlCell.getCellFormula();
            }
            break;
          case ERROR:
            value = xlCell.getErrorCellValue();
            break;
          default:
            break;
        }
      } catch (Exception e) {
        // LogUtils.logError(ExcelUtils.class, e.getMessage());
        value = null;
      }
    }
    // else
    // LogUtils.logWarn(ExcelUtils.class, "Could not get value of NULL cell object!!!");
    return value;
  }

}
