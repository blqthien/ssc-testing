package sys.exe.co.jp.documents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.util.StringUtils;

//@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Value("${table.path}")
	private String tablePath;

	@Value("${document.path}")
	private String documentPath;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {

		System.out.println("onApplicationEvent Start");
		long start = System.currentTimeMillis();

		// Get data from テーブル定義書
		List<DocumentInDto> lstTracker = getData();

		try {
			Set<DocInDto> listFiles = new HashSet<>();
			this.listFilesUsingDirectoryStream(listFiles, documentPath);

			for (DocInDto file : listFiles) {
				System.out.println(file.toString());
				if (file.getFilename().contains("基本設計書_")) {
					this.updateDocument(lstTracker, file.getPath());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		float msec = end - start;
		System.out.println(msec + " milliseconds");

		System.out.println("onApplicationEvent End");
	}

	public Set<DocInDto> listFilesUsingDirectoryStream(Set<DocInDto> fileSet, String dir) throws IOException {
//		Set<DocInDto> fileSet = new HashSet<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
			for (Path path : stream) {

				if (!path.toString().contains("96.PMレビュー指摘")) {
					if (!Files.isDirectory(path)) {
						if (path.getFileName().toString().indexOf("基本設計書_") == 0) {
							DocInDto item = new DocInDto();
							item.setFilename(path.getFileName().toString());
							item.setPath(path.toString());
							fileSet.add(item);
						}
					} else {
						// Recursively
						listFilesUsingDirectoryStream(fileSet, path.toAbsolutePath().toString());
					}
				}
			}
		}
		return fileSet;
	}

	/**
	 * update details design document sheet 5
	 *
	 * @param lstTracker
	 * @param documentPath
	 * @return number row update
	 */
	@SuppressWarnings("deprecation")
	private int updateDocument(List<DocumentInDto> lstTracker, String documentPath) {
		try {
			File wbsFile = new File(documentPath);
			FileInputStream fis = new FileInputStream(wbsFile);
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			Sheet wbsSheet = workbook.getSheetAt(5);

			int beggin = 7, end = wbsSheet.getLastRowNum();
			Row curRow = null;

			for (; beggin <= end; beggin++) {

				curRow = wbsSheet.getRow(beggin);

				// Cell id = curRow.getCell(11); // # 11
				// curId = ExcelUtils.getCellIntValue(id);

				Cell webPageElementNameCell = curRow.getCell(13); // 画面項目名
				String webPageElementName = ExcelUtils.getCellStringValue(webPageElementNameCell);

				System.out.println(webPageElementName);
				if (!StringUtils.isEmpty(webPageElementName)) {

					// get details data name from list track document
					DocumentInDto item = getDetailsData(lstTracker, webPageElementName);
					System.out.println(item.toString());

					// termType
					Cell termType = curRow.getCell(24);
					if (StringUtils.isEmpty(ExcelUtils.getCellStringValue(termType))) {
						if (null == termType) {
							termType = curRow.createCell(24);
						}
						termType.setCellValue(item.getTermType());
					}

					// domain
					Cell domain = curRow.getCell(25);
					if (StringUtils.isEmpty(ExcelUtils.getCellStringValue(domain))) {
						if (null == domain) {
							domain = curRow.createCell(25);
						}
						domain.setCellValue(item.getDomain());
					}

					// "noLimit", 28
					Cell noLimit = curRow.getCell(28);
					if (StringUtils.isEmpty(ExcelUtils.getCellStringValue(noLimit))) {
						if (null == noLimit) {
							noLimit = curRow.createCell(28);
						}
						noLimit.setCellValue(item.getNoLimit());
					}

					// "alphanumeric", 29
					Cell alphanumeric = curRow.getCell(29);
					if (StringUtils.isEmpty(ExcelUtils.getCellStringValue(alphanumeric))) {
						if (null == alphanumeric) {
							alphanumeric = curRow.createCell(29);
						}
						alphanumeric.setCellValue(item.getAlphanumeric());
					}

					// "password", 30
					Cell password = curRow.getCell(30);
					if (StringUtils.isEmpty(ExcelUtils.getCellStringValue(password))) {
						if (null == password) {
							password = curRow.createCell(30);
						}
						password.setCellValue(item.getPassword());
					}

					// "number", 31
					Cell number1 = curRow.getCell(31);
					if (StringUtils.isEmpty(ExcelUtils.getCellStringValue(number1))) {
						if (null == number1) {
							number1 = curRow.createCell(31);
						}
						number1.setCellValue(item.getNumber());
					}

					// "strNumber", 32
					Cell strNumber = curRow.getCell(32);
					if (StringUtils.isEmpty(ExcelUtils.getCellStringValue(strNumber))) {
						if (null == strNumber) {
							strNumber = curRow.createCell(32);
						}
						strNumber.setCellValue(item.getStrNumber());
					}

					// "eMail", 33
					Cell eMail = curRow.getCell(33);
					if (StringUtils.isEmpty(ExcelUtils.getCellStringValue(eMail))) {
						if (null == eMail) {
							eMail = curRow.createCell(33);
						}
						eMail.setCellValue(item.getEMail());
					}

					// "kanaMajor", 34
					Cell kanaMajor = curRow.getCell(34);
					if (StringUtils.isEmpty(ExcelUtils.getCellStringValue(kanaMajor))) {
						if (null == kanaMajor) {
							kanaMajor = curRow.createCell(34);
						}
						kanaMajor.setCellValue(item.getKanaMajor());
					}

					// "accountHolder", 35
					Cell accountHolder = curRow.getCell(35);
					if (StringUtils.isEmpty(ExcelUtils.getCellStringValue(accountHolder))) {
						if (null == accountHolder) {
							accountHolder = curRow.createCell(35);
						}
						accountHolder.setCellValue(item.getAccountHolder());
					}

					// "least", 36
					Cell least = curRow.getCell(36);
					if (StringUtils.isEmpty(ExcelUtils.getCellStringValue(least))) {
						if (null == least) {
							least = curRow.createCell(36);
						}
						least.setCellValue(item.getLeast());
					}

					// "max", 37
					Cell max1 = curRow.getCell(37);
					if (StringUtils.isEmpty(ExcelUtils.getCellStringValue(max1))) {
						if (null == max1) {
							max1 = curRow.createCell(37);
						}
						max1.setCellValue(item.getMax());
					}

					// "physicsName", 38
					Cell physicsName = curRow.getCell(38);
					if (StringUtils.isEmpty(ExcelUtils.getCellStringValue(physicsName))) {
						if (null == physicsName) {
							physicsName = curRow.createCell(38);
						}
						physicsName.setCellValue(item.getPhysicsName());
					}

					// "type", 39
					Cell type = curRow.getCell(39);
					if (StringUtils.isEmpty(ExcelUtils.getCellStringValue(type))) {
						if (null == type) {
							type = curRow.createCell(39);
						}
						type.setCellValue(item.getType());
					}

					// "digitsLength", 40
					Cell digitsLength = curRow.getCell(40);
					if (StringUtils.isEmpty(ExcelUtils.getCellStringValue(digitsLength))) {
						if (null == digitsLength) {
							digitsLength = curRow.createCell(40);
						}
						digitsLength.setCellValue(item.getDigitsLength());
					}

				}
			}

			// open an OutputStream to save written data into Excel file
			FileOutputStream os = new FileOutputStream(wbsFile);
			workbook.write(os);
			os.close();
			System.out.println("Writing on Excel file Finished ...");

			// Close workbook, OutputStream and Excel file to prevent leak
			workbook.close();
			fis.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * Compare each row from details design and テーブル定義書 for get details data mapping
	 *
	 * @param lstTracker
	 * @param webPageElementName
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private DocumentInDto getDetailsData(List<DocumentInDto> lstTracker, String webPageElementName) {
		DocumentInDto result = new DocumentInDto();
		for (DocumentInDto item : lstTracker) {
			if (!StringUtils.isEmpty(item.getWebPageElementName())) {

				ArrayList<String> lstWebPageElementName = new ArrayList<>(
						Arrays.asList(item.getWebPageElementName().split(",")));

				if (lstWebPageElementName.contains(webPageElementName)) {
					result = new DocumentInDto();

					result.setLogicalName(item.getLogicalName());
					result.setKana(item.getKana());

					result.setTermType(item.getTermType());
					result.setDomain(item.getDomain());
					result.setComponentOnEntry(item.getComponentOnEntry());
					result.setNoLimit(item.getNoLimit());
					result.setAlphanumeric(item.getAlphanumeric());
					result.setPassword(item.getPassword());
					result.setNumber(item.getNumber());
					result.setStrNumber(item.getStrNumber());
					result.setEMail(item.getEMail());
					result.setKanaMajor(item.getKanaMajor());
					result.setAccountHolder(item.getAccountHolder());
					result.setLeast(item.getLeast());
					result.setMax(item.getMax());
					result.setPhysicsName(item.getPhysicsName());
					result.setType(item.getType());
					result.setDigitsLength(item.getDigitsLength()); // 40

					return result;
				}
			}
		}
		return result;
	}

	/**
	 * Get master data from テーブル定義書
	 *
	 * @return List
	 */
	private List<DocumentInDto> getData() {
		List<DocumentInDto> lstTracker = null;
		try {

			File wbsFile = new File(tablePath);
			FileInputStream fis = new FileInputStream(wbsFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			Sheet master = workbook.getSheet("項目一覧");

			// 7: start row
			// 8: end row
			// 1 : start column
			// 24: end column ( "現行桁（バイト数）)
			lstTracker = ExcelUtils.readFromSheet(master, 7, 1612, 1, 24, DocumentInDto.class, "logicalName", "kana",
					"termType", "domain", "webPageElementName", "componentOnEntry", "noLimit", "alphanumeric",
					"password", "number", "strNumber", "eMail", "kanaMajor", "accountHolder", "least", "max",
					"physicsName", "type", "digitsLength", "setup", "sequence", "itemSupplement", "currentType",
					"currentDigitsByteCount");

			fis.close();
			workbook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return lstTracker;
	}

}
