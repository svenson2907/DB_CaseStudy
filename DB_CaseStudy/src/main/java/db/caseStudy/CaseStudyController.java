package db.caseStudy;

import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import services.ReadXml;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
public class CaseStudyController {
    private final Repository repository;

    public CaseStudyController(Repository repository) {
        this.repository = repository;
    }

    @RequestMapping(path = "/trackSections/{ril100}/{trainNumber}/{number}", method = RequestMethod.GET)
    String getTrackSection(@PathVariable String ril100, @PathVariable Integer trainNumber, @PathVariable Integer number) throws IOException, ParserConfigurationException, SAXException {
        // http://localhost:8080/trackSections/ril100/1/2
        ReadXml readXml = new ReadXml();
        return readXml.run(""+trainNumber, ""+number);
    }
/*
    @RequestMapping(value="/trackSections", method = RequestMethod.GET)
    String getParams(@RequestParam Map<String, String> params ) {
        // http://localhost:8080/trackSections?station=ril100&train=1&waggon=2
        return params.toString();
    }*/
}
