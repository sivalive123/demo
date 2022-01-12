package com.soapui;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;

public class AppTest {
    /**
     * Rigorous Test :-)
     */
    public static void shouldAnswerWithTrue(String countrycode) {
        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<String> response = Unirest
                    .post("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso")
                    .header("Content-Type", "text/xml; charset=\"UTF-8\"")
                    .body("<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">\n    <Body>\n        <CapitalCity xmlns=\"http://www.oorsprong.org/websamples.countryinfo\">\n            <sCountryISOCode>"
                            + countrycode + "</sCountryISOCode>\n        </CapitalCity>\n    </Body>\n</Envelope>")
                    .asString();

            System.out.println(response.getStatus());
            System.out.println(response.getBody());

            XmlPath xmlPath = new XmlPath(response.getBody()).using(XmlPathConfig.xmlPathConfig().declaredNamespace("m",
                    "http://www.oorsprong.org/websamples.countryinfo"));

        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void getListOfCountries() {
        Unirest.setTimeouts(0, 0);
        try {
            HttpResponse<String> response = Unirest
                    .post("http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso")
                    .header("Content-Type", "text/xml; charset=\"UTF-8\"")
                    .body("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://www.oorsprong.org/websamples.countryinfo\">\n   <soapenv:Header/>\n   <soapenv:Body>\n      <web:ListOfContinentsByCode/>\n   </soapenv:Body>\n</soapenv:Envelope>")
                    .asString();
            System.out.println(response.getBody());
            JSONObject xmlJSONObj = XML.toJSONObject(response.getBody());
            JSONArray jsonArray = xmlJSONObj.getJSONObject("soap:Envelope").getJSONObject("soap:Body")
                    .getJSONObject("m:ListOfContinentsByCodeResponse").getJSONObject("m:ListOfContinentsByCodeResult")
                    .getJSONArray("m:tContinent");
            System.out.println(jsonArray.length());

            for (int i = 0; i < jsonArray.length(); i++) {
                System.out.println(jsonArray.getJSONObject(i).get("m:sCode"));
            }
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void coverstion() {
        Unirest.setTimeouts(0, 0);
        try {

            HttpResponse<String> response = Unirest
                    .post("https://www.dataaccess.com/webservicesserver/numberconversion.wso")
                    .header("Content-Type", "text/xml; charset=\"UTF-8\"")
                    .body("<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">\n    <Body>\n        <NumberToDollars xmlns=\"http://www.dataaccess.com/webservicesserver/\">\n            <dNum>1.1</dNum>\n        </NumberToDollars>\n    </Body>\n</Envelope>")
                    .asString();

            System.out.println(response.getBody());

            Document document;
            try {
                document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .parse(new InputSource(new StringReader(response.getBody().toString())));
                NodeList nodeList = document.getDocumentElement().getChildNodes();
                System.out.println(nodeList.getLength());
                System.out.println("Root element: " + document.getDocumentElement().getNodeName());
                System.out
                        .println("Root element attribute: " + document.getDocumentElement().getAttribute("xmlns:soap"));
                NodeList nList = document.getElementsByTagName("soap:Body");
                System.out.println(nList.getLength());

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element elem = (Element) node;
                        // Get the value of all sub-elements.
                        String firstname = elem.getElementsByTagName("m:NumberToDollarsResponse").item(0)
                                .getTextContent();

                        System.out.println(firstname);

                    }
                }
            } catch (SAXException | IOException | ParserConfigurationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            JSONObject xmlJSONObj = XML.toJSONObject(response.getBody());
            // String jsonArray =
            // xmlJSONObj.getJSONObject("soap:Envelope").getJSONObject("soap:Body")
            // .getJSONObject("m:NumberToDollarsResponse").get("m:NumberToDollarsResult").toString();
            // System.out.println(jsonArray);
        } catch (UnirestException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void nodes(){
String test=""

    }

    public static void main(String[] args) {
        // shouldAnswerWithTrue("IN");
        // getListOfCountries();
        // shouldAnswerWithTrue("US");
        coverstion();
    }
}
