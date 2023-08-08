package db.caseStudy;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import readFromXml.Service;

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
        Service service = new Service();
        return service.run(""+trainNumber, ""+number);
    }
/*
    @RequestMapping(value="/trackSections", method = RequestMethod.GET)
    String getParams(@RequestParam Map<String, String> params ) {
        // http://localhost:8080/trackSections?station=ril100&train=1&waggon=2
        return params.toString();
    }*/
}
