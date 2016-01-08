package util;

import org.apache.poi.ss.usermodel.*
import org.apache.poi.hssf.usermodel.*
import org.apache.poi.xssf.usermodel.*
import org.apache.poi.ss.util.*
import org.apache.poi.ss.usermodel.*
import java.io.*

class GroovyExcelParser {
  //http://poi.apache.org/spreadsheet/quick-guide.html#Iterator

  def parse(path) {
    InputStream inp = new FileInputStream(path)
    Workbook wb = WorkbookFactory.create(inp);
    Sheet sheet = wb.getSheetAt(0);

    Iterator<Row> rowIt = sheet.rowIterator()
    Row row = rowIt.next()
    def headers = getRowData(row)

    def rows = []
    while(rowIt.hasNext()) {
      row = rowIt.next()
      rows << getRowData(row)
    }
    [headers, rows]
  }

  def getRowData(Row row) {
    def data = []
    for (Cell cell : row) {
      getValue(row, cell, data)
    }
    data
  }

  def getRowReference(Row row, Cell cell) {
    def rowIndex = row.getRowNum()
    def colIndex = cell.getColumnIndex()
    CellReference ref = new CellReference(rowIndex, colIndex)
    ref.getRichStringCellValue().getString()
  }
 
  def getValue(Row row, Cell cell, List data) {
    def rowIndex = row.getRowNum()
    def colIndex = cell.getColumnIndex()
    def value = ""
    switch (cell.getCellType()) {
      case Cell.CELL_TYPE_STRING:
        value = cell.getRichStringCellValue().getString();
        break;
      case Cell.CELL_TYPE_NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) {
            value = cell.getDateCellValue();
        } else {
            value = cell.getNumericCellValue();
        }
        break;
      case Cell.CELL_TYPE_BOOLEAN:
        value = cell.getBooleanCellValue();
        break;
      case Cell.CELL_TYPE_FORMULA:
        value = cell.getCellFormula();
        break;
      default:
        value = ""
    }
    data[colIndex] = value
    data
  }

  def toXml(header, row) {
    def obj = "<object>\n"
    row.eachWithIndex { datum, i -> 
      def headerName = header[i]
      obj += "\t<$headerName>$datum</$headerName>\n" 
    } 
    obj += "</object>"
  }

    def toMap(header, row) {
        def map = [:]
        def sampleMap = [:]
        def orginalKey= ''
        row.eachWithIndex { datum, i ->
            if(i==0) {
                orginalKey = datum
            }else{
                def key = header[i]
                def value = datum
                sampleMap.put(key,value)
            }
        }
        map.put(orginalKey,sampleMap)
        map
    }

  public static void main(String[]args) {
    def filename = 'C:\\NOSCAN\\Application\\Application\\controller\\mypolicy-integration-test\\src\\test\\resources\\QA/Data.xlsx'
    GroovyExcelParser parser = new GroovyExcelParser()
    def (headers, rows) = parser.parse(filename)
    println 'Headers'
    println '------------------'
    headers.each { header -> 
      println header
    }
    println "\n"
    println 'Rows'
    println '------------------'
    rows.each { row ->
      println parser.toXml(headers, row)
    }
  }
}