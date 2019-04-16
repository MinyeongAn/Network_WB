package NetworkProject;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class bus{
	public static ArrayList<data2> bus_s(String text) throws Exception {  
		ArrayList<data2> bus_num = new ArrayList<data2>();
		data2 d2 ;
		String stationnum;

		stationnum = text;
		String routeId = null;
		String station = "http://openapi.gbis.go.kr/ws/rest/busarrivalservice/station?serviceKey=oGmTWhTTIhSbltYV6nx4eyDIrw%2F2tNnL8UKN%2Ff9XoKW8md2tTa2zffMMT8fx%2BTKDZwDc8e%2Buh4yO30%2BVTtKIaw%3D%3D&stationId="+stationnum;
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(station);
		XPath xpath = XPathFactory.newInstance().newXPath();

		// NodeList 가져오기 : row 아래에 있는 모든 col1 을 선택
		NodeList cols = (NodeList)xpath.evaluate("//msgBody/busArrivalList/routeId", document, XPathConstants.NODESET);
		NodeList cols2 = (NodeList)xpath.evaluate("//msgBody/busArrivalList/predictTime1", document, XPathConstants.NODESET);
		NodeList cols3 = (NodeList)xpath.evaluate("//msgBody/busArrivalList/predictTime2", document, XPathConstants.NODESET);

		for( int i=0; i<cols.getLength(); i++ )	{
			routeId = cols.item(i).getTextContent();
			String busname = "http://openapi.gbis.go.kr/ws/rest/busrouteservice/info?serviceKey=oGmTWhTTIhSbltYV6nx4eyDIrw%2F2tNnL8UKN%2Ff9XoKW8md2tTa2zffMMT8fx%2BTKDZwDc8e%2Buh4yO30%2BVTtKIaw%3D%3D&routeId="+ routeId;
			Document document2 = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(busname);
			NodeList route = (NodeList)xpath.evaluate("//msgBody/busRouteInfoItem/routeName", document2, XPathConstants.NODESET);
			d2 = new data2("버스 번호: " + route.item(0).getTextContent(),cols2.item(i).getTextContent(),cols3.item(i).getTextContent());
			bus_num.add(d2);
		}	
		return bus_num;
	}
}
