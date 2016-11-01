package com.xzjie.gypt.common.utils.excel;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	/************************************XSSF*********************************************/
    
    /**
     * 取得指定单元格行和列
     * @param keyMap 所有单元格行、列集合
     * @param key 单元格标识
     * @return 0：列 1：行（列表型数据不记行，即1无值）
     */
    public static int[] getPos(HashMap keyMap, String key){
        int[] ret = new int[0];
          
        String val = (String)keyMap.get(key);
          
        if(val == null || val.length() == 0)
            return ret;
          
        String pos[] = val.split(",");
          
        if(pos.length == 1 || pos.length == 2){
            ret = new int[pos.length];
            for(int i0 = 0; i0 < pos.length; i0++){
                if(pos[i0] != null && pos[i0].trim().length() > 0){
                    ret[i0] = Integer.parseInt(pos[i0].trim());
                } else {
                    ret[i0] = 0;
                }
            }
        }
        return ret;
    }
    
    /**
     * 取对应格子的值
     * @param sheet
     * @param rowNo 行
     * @param cellNo 列
     * @return
     * @throws IOException
     */
    public static String getCellValue(Sheet sheet,int rowNo,int cellNo) {
        String cellValue = null;
        Row row = sheet.getRow(rowNo);
        Cell cell = row.getCell(cellNo);
        if (cell != null) {
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                DecimalFormat df = new DecimalFormat("0");
                cellValue = getCutDotStr(df.format(cell.getNumericCellValue()));
            } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                cellValue = cell.getStringCellValue();
            }
            if (cellValue != null) {
                cellValue = cellValue.trim();
            }           
        } else {
            cellValue = null;
        }
        return cellValue;
    }
    
    /**
     * 取整数
     * @param srcString
     * @return
     */
    private static String getCutDotStr(String srcString) {
        String newString = "";
        if (srcString != null && srcString.endsWith(".0")) {
            newString = srcString.substring(0,srcString.length()-2);
        } else {
            newString = srcString;
        }
        return newString;
    } 
    
    /**
     * 读数据模板
     * @param 模板地址
     * @throws IOException
     */
    public static HashMap[] getTemplateFile(String templateFileName) throws IOException {    
        FileInputStream fis = new FileInputStream(templateFileName);
         
        Workbook wbPartModule = null;
        if(templateFileName.endsWith(".xlsx")){
            wbPartModule = new XSSFWorkbook(fis);
        }else if(templateFileName.endsWith(".xls")){
            wbPartModule = new HSSFWorkbook(fis);
        }
        int numOfSheet = wbPartModule.getNumberOfSheets();
        HashMap[] templateMap = new HashMap[numOfSheet];
        for(int i = 0; i < numOfSheet; i++){
            Sheet sheet = wbPartModule.getSheetAt(i);
            templateMap[i] = new HashMap();
            readSheet(templateMap[i], sheet);
        }
        fis.close();
        return templateMap;
    }
    
    /**
     * 读模板数据的样式值置等信息
     * @param keyMap
     * @param sheet
     */
    private static void readSheet(HashMap keyMap, Sheet sheet){
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
          
        for (int j = firstRowNum; j <= lastRowNum; j++) {
            Row rowIn = sheet.getRow(j);
            if(rowIn == null) {
                continue;
            }
            int firstCellNum = rowIn.getFirstCellNum();
            int lastCellNum = rowIn.getLastCellNum();
            for (int k = firstCellNum; k <= lastCellNum; k++) {
//              Cell cellIn = rowIn.getCell((short) k);
                Cell cellIn = rowIn.getCell(k);
                if(cellIn == null) {
                    continue;
                }
                  
                int cellType = cellIn.getCellType();
                if(Cell.CELL_TYPE_STRING != cellType) {
                    continue;
                }
                String cellValue = cellIn.getStringCellValue();
                if(cellValue == null) {
                    continue;
                }
                cellValue = cellValue.trim();
                if(cellValue.length() > 2 && cellValue.substring(0,2).equals("<%")) {
                    String key = cellValue.substring(2, cellValue.length());
                    String keyPos = Integer.toString(k)+","+Integer.toString(j);
                    keyMap.put(key, keyPos);
                    keyMap.put(key+"CellStyle", cellIn.getCellStyle());
                } else if(cellValue.length() > 3 && cellValue.substring(0,3).equals("<!%")) {
                    String key = cellValue.substring(3, cellValue.length());
                    keyMap.put("STARTCELL", Integer.toString(j));
                    keyMap.put(key, Integer.toString(k));
                    keyMap.put(key+"CellStyle", cellIn.getCellStyle());
                }
            }
        }
    }
      
    /**
     * 获取格式，不适于循环方法中使用，wb.createCellStyle()次数超过4000将抛异常
     * @param keyMap
     * @param key
     * @return
     */
    public static CellStyle getStyle(HashMap keyMap, String key,Workbook wb) {
        CellStyle cellStyle = null;      
          
        cellStyle = (CellStyle) keyMap.get(key+"CellStyle");
        //当字符超出时换行
        cellStyle.setWrapText(true);
        CellStyle newStyle = wb.createCellStyle();
        newStyle.cloneStyleFrom(cellStyle);
        return newStyle;
    }
    /**
     * Excel单元格输出
     * @param sheet
     * @param row 行
     * @param cell 列
     * @param value 值
     * @param cellStyle 样式
     */
    public static void setValue(Sheet sheet, int row, int cell, Object value, CellStyle cellStyle){
        Row rowIn = sheet.getRow(row);
        if(rowIn == null) {
            rowIn = sheet.createRow(row);
        }
        Cell cellIn = rowIn.getCell(cell);
        if(cellIn == null) {
            cellIn = rowIn.createCell(cell);
        }
        if(cellStyle != null) {  
            //修复产生多超过4000 cellStyle 异常
            //CellStyle newStyle = wb.createCellStyle();
            //newStyle.cloneStyleFrom(cellStyle);
            cellIn.setCellStyle(cellStyle);
        }
        //对时间格式进行单独处理
        if(value==null){
            cellIn.setCellValue("");
        }else{
            if (isCellDateFormatted(cellStyle)) {
                cellIn.setCellValue((Date) value);
            } else {
                cellIn.setCellValue(new XSSFRichTextString(value.toString()));
            }
        }
    }
    
	public static void setPatriarch(Workbook workbook, Sheet sheet, int row, int cell, Object value) {

		String picPath = (String) value;
		//String suffix = picPath.substring(picPath.lastIndexOf("."));
		BufferedImage bufferImg = null;
		try {
			// new一个URL对象
			URL url = new URL(picPath);
			// 打开链接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置请求方式为"GET"
			conn.setRequestMethod("GET");
			// 超时响应时间为5秒
			conn.setConnectTimeout(5 * 1000);
			// 通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();
			// 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

			// 读入图片 可以是输入流 也可以试 本地 File对象
			bufferImg = ImageIO.read(inStream);
			// bufferImg = ImageIO.read(new File("F:/16.jpg"));
			ImageIO.write(bufferImg, "jpg", byteArrayOut);
			// 前四位 是 指定位置的两个单元格 中的偏移量， 横向最大 1023 纵向最大 255，单位不明，大概是一个比例

			Drawing patriarch = sheet.createDrawingPatriarch();
			//HSSFClientAnchor .xls
			XSSFClientAnchor anchor = new XSSFClientAnchor(0, 150, 1000, 210, (short) cell, (short) row,
					(short) (cell + 1), (short) (row + 4));

			/*int PICTURE_TYPE = HSSFWorkbook.PICTURE_TYPE_JPEG;

			if ("jpeg".equals(suffix) || "jpg".equals(suffix) || ".jpeg".equals(suffix) || ".jpg".equals(suffix)) {
				PICTURE_TYPE = HSSFWorkbook.PICTURE_TYPE_JPEG;
			} else if ("png".equals(suffix) || ".png".equals(suffix)) {
				PICTURE_TYPE = HSSFWorkbook.PICTURE_TYPE_PNG;
			} else if ("bmp".equals(suffix) || ".bmp".equals(suffix)) {
				PICTURE_TYPE = HSSFWorkbook.PICTURE_TYPE_EMF;
			}*/

			patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
			byteArrayOut.close();
			inStream.close();
			conn.disconnect();

		} catch (IOException e) {

		} finally {

		}
	}

    /**
     * 根据表格样式判断是否为日期格式
     * @param cellStyle
     * @return
     */
    public static boolean isCellDateFormatted(CellStyle cellStyle){
        if(cellStyle==null){
            return false;
        }
        int i = cellStyle.getDataFormat();
        String f = cellStyle.getDataFormatString();
         
        return org.apache.poi.ss.usermodel.DateUtil.isADateFormat(i, f);
    }
    /**
     * 适用于导出的数据Excel格式样式重复性较少
     * 不适用于循环方法中使用
     * @param wbModule
     * @param sheet
     * @param pos 模板文件信息
     * @param startCell 开始的行
     * @param value 要填充的数据
     * @param cellStyle 表格样式
     */
    public static void createCell(Workbook wbModule, Sheet sheet,HashMap pos, int startCell,Object value,String cellStyle){
        int[] excelPos = getPos(pos, cellStyle);
        setValue(sheet, startCell, excelPos[0], value, getStyle(pos, cellStyle,wbModule));
    }
}
