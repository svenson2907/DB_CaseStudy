package db.caseStudy;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import services.ReadFromXml;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
public class CaseStudyController {

    @RequestMapping(path = "/trackSections/{ril100}/{trainNumber}/{number}", method = RequestMethod.GET)
    String getTrackSection(@PathVariable String ril100, @PathVariable Integer trainNumber, @PathVariable Integer number) throws IOException, ParserConfigurationException, SAXException {
        ReadFromXml readFromXml = new ReadFromXml();
        return readFromXml.run(""+trainNumber, ""+number);
    }
}
