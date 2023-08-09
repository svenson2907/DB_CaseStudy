package services;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ReadFromXml {

    public static String run(String trainNumber, String waggonNumber) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = builder.parse("src\\main\\resources\\FF_2017-12-01_10-47-17.xml");
        doc.getDocumentElement().normalize();
        String section="";
        boolean trainNumberPasst=false;
        for(int t=0; t<doc.getElementsByTagName("train").getLength(); ++t) {    //iterates indices of descendant elements to train-Tag
            trainNumberPasst = false;
            for(int i=0; i<doc.getElementsByTagName("train").item(t).getChildNodes().getLength(); ++i) {    //iterates indices of descendant elements to train-Tags t-th child
                if(!doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getNodeName().replaceAll("\\s","").equals("#text")) {    //checks i-th element for redundant "#text"-element
                    if(doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getTextContent().replaceAll("\\s", "").equals(trainNumber)) {    //check i-th element whether it is the searched train number
                        trainNumberPasst = true;    //to remember that trainNumber got found
                    }
                    if(doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getNodeName().replaceAll("\\s", "").equals("waggons")) {    //checks i-th element for "waggons"-tag
                        for(int w=0; w<doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().getLength(); ++w) {    //iterates indices of descendant elements to train-Tags t-th and of them i-th children
                            if(!doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().item(w).getNodeName().replaceAll("\\s", "").equals("#text")) {    //checks w-th element for redundant "#text"-element
                                for(int j=0; j<doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().item(w).getChildNodes().getLength(); ++j) {    //iterates indices of descendant elements to train-Tags t-th and of them i-th and of them w-th children
                                    if(!doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().item(w).getChildNodes().item(j).getNodeName().equals("#text")) {    //checks j-th elements nodeName for redundant "#text"-element
                                        if(doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().item(w).getChildNodes().item(j).getNodeName().replaceAll("\\s", "").equals("sections")) {    //checks j-th element for "sections"-tag
                                            if (!doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().item(w).getChildNodes().item(j).getTextContent().equals("#text")) {    //checks j-th element for redundant "#text"-element
                                                section = doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().item(w).getChildNodes().item(j).getTextContent().replaceAll("\\s", "");    //section got found
                                            }
                                        }
                                    }
                                    if(doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().item(w).getChildNodes().item(j).getNodeName().replaceAll("\\s", "").equals("number") &&           //checks if j-th NodeName equals to "number" ANS
                                       doc.getElementsByTagName("train").item(t).getChildNodes().item(i).getChildNodes().item(w).getChildNodes().item(j).getTextContent().replaceAll("\\s", "").equals(waggonNumber)) {    //j-th text equals to waggonNumber
                                        if(trainNumberPasst) {
                                            return "{ \"sections\": [\""+section+"\"]}";    //if so section got found
                                        }
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
}
