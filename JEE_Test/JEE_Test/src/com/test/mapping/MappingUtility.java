package com.test.mapping;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class MappingUtility {

  private Properties prop = new Properties();
  private DocumentBuilder builder = null;
  private static final String PROP_FILENAME = "PerformanceGatewayMasterConfig.properties";
  private XPath xpath = XPathFactory.newInstance().newXPath();
  private Document document = null;
  private String scorecardName = null;
  private HashMap<String, String> hashMap = new HashMap<String, String>();

  public MappingUtility() {
    try {
      loadPropertyFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    loadDocumentBuilder();

  }

  public MappingUtility(String scorecardName) {
    this.scorecardName = scorecardName;
    loadDocumentBuilder();
    readFromExcel(scorecardName);
  }

  private void loadDocumentBuilder() {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      builder = factory.newDocumentBuilder();
      InputStream inputStream = getClass().getResourceAsStream("Task_With_DamageLines.xml");
      try {
        document = builder.parse(inputStream);
      } catch (SAXException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
  }

  public String mappedValueFromTask(String fieldName) {
    String pathInSaxif = getProperty(fieldName);
    return getXPathValueFromTask(pathInSaxif);
  }

  public String getMappedValueFromTask(String fieldName) {
    String pathInSaxif = hashMap.get(fieldName);
    return getXPathValueFromTask(pathInSaxif);
  }

  private void loadPropertyFile() throws IOException {
    InputStream inputStream = getClass().getResourceAsStream(PROP_FILENAME);
    prop.load(inputStream);
  }

  private String getProperty(String name) {
    if (StringUtils.isNotBlank(prop.getProperty(name))) {
      return prop.getProperty(name);
    }
    return null;
  }

  private String getXPathValueFromTask(String pathInSaxif) {
    if (StringUtils.isNotBlank(pathInSaxif)) {
      try {
        return xpath.compile(pathInSaxif).evaluate(document);
      } catch (XPathExpressionException e) {
        e.printStackTrace();
      }
    }
    return StringUtils.EMPTY;
  }

  private String readFromExcel(String fieldName) {
    InputStream inputStream = getClass().getResourceAsStream("PerformanceGatewayConfig.xlsx");
    try {
      XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
      XSSFSheet ws = workbook.getSheetAt(0);
      int rowNum = ws.getLastRowNum() + 1;
      for (int i = 1; i < rowNum; i++) {
        XSSFRow row = ws.getRow(i);
        String value = row.getCell(3).toString();
        if ("TRUE".equals(value)) {
          String key = row.getCell(1).toString();
          String xpath = row.getCell(2).toString();
          if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(xpath)) {
            hashMap.put(key, xpath);
          }
        }

      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }
}
