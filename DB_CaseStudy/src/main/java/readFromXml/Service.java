package readFromXml;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Service {

    public static String run(String trainNumber, String waggonNumber) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse("src\\main\\resources\\FF_2017-12-01_10-47-17.xml");
        doc.getDocumentElement().normalize();
//        String trainNumber="2310", waggonNumber="10";
//        String trainNumber="2278", waggonNumber="12";
//        String trainNumber="12", waggonNumber="29";
//        String trainNumber="820", waggonNumber="39", section="";
//        String trainNumber="1088", waggonNumber="7", section="";
        String section="";
        boolean trainNumberPasst=false, waggonNumberPasst=false;
        for(int t=0; t<doc.getElementsByTagName("train").getLength(); ++t) {
            trainNumberPasst = false;
            for(int i=0; i<doc.getElementsByTagName("train").item(t).getChildNodes().getLength(); ++i) {
                if(!doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getNodeName().replaceAll("\\s","").equals("#text")) {
                    if(doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getTextContent().replaceAll("\\s", "").equals(trainNumber)) {
                        trainNumberPasst = true;
                    }
                    if(doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getNodeName().replaceAll("\\s", "").equals("waggons")) {
                        for(int w=0; w<doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().getLength(); ++w) {
                            if(!doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().item(w).getNodeName().replaceAll("\\s", "").equals("#text")) {
                                for(int j=0; j<doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().item(w).getChildNodes().getLength(); ++j) {
                                    if(!doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().item(w).getChildNodes().item(j).getNodeName().equals("#text")) {
                                        if(doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().item(w).getChildNodes().item(j).getNodeName().replaceAll("\\s", "").equals("sections")) {
                                            if (!doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().item(w).getChildNodes().item(j).getTextContent().equals("#text")) {
                                                section = doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().item(w).getChildNodes().item(j).getTextContent().replaceAll("\\s", "");
                                            }
                                        }
                                    }
                                    if(doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().item(w).getChildNodes().item(j).getNodeName().replaceAll("\\s", "").equals("number") &&
                                       doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().item(w).getChildNodes().item(j).getTextContent().replaceAll("\\s", "").equals(waggonNumber)) {
                                        waggonNumberPasst = true;
                                        if(trainNumberPasst) {
                                            return "{ \"sections\": [\""+section+"\"]}";
                                        }
                                    }else {
                                        waggonNumberPasst = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return "Data not found";
    }
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
//        System.out.println(run(new File("src\\main\\resources\\FF_2017-12-01_10-47-17.xml")));
        System.out.println(run("2278","12"));
    }
}