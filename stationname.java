package NetworkProject;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class stationname {
	public static ArrayList<data> stationnames(String text)throws Exception {


		ArrayList<data> station_name = new ArrayList<data>();
		data d ;
		String stationname = "";
		byte[] b;


		b = text.getBytes("UTF-8");
		for(int i = 0 ; i < b.length ; i++){
			if(Integer.toHexString(b[i]).length()>3){
				stationname += "%" + Integer.toHexString(b[i]).substring(6,8);
			}
		}
		String station = "http://openapi.gbis.go.kr/ws/rest/busstationservice?serviceKey=oGmTWhTTIhSbltYV6nx4eyDIrw%2F2tNnL8UKN%2Ff9XoKW8md2tTa2zffMMT8fx%2BTKDZwDc8e%2Buh4yO30%2BVTtKIaw%3D%3D&keyword=" + stationname;
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(station);
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList cols = (NodeList)xpath.evaluate("//msgBody/busStationList/stationId", document, XPathConstants.NODESET);
		NodeList cols2 = (NodeList)xpath.evaluate("//msgBody/busStationList/stationName", document, XPathConstants.NODESET);
		for( int i=0; i<cols.getLength();i++){
			d = new data("정류장 이름: " + cols2.item(i).getTextContent(),cols.item(i).getTextContent());
			station_name.add(d);
		}
		return station_name;
	}
}