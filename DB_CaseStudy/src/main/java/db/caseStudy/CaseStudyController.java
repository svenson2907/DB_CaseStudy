package db.caseStudy;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class CaseStudyController {
    private final Repository repository;

    public CaseStudyController(Repository repository) {
        this.repository = repository;
    }

    @RequestMapping(path = "/trackSectionx/{ril100}/{trainNumber}/{number}", method = RequestMethod.GET)
    String getTrackSection(@PathVariable String ril100, @PathVariable Integer trainNumber, @PathVariable Integer number) {
        // http://localhost:8080/trackSectionx/ril100/1/2
        return ("It Works! ril100:="+ril100+" trainNumber:="+trainNumber+" waggon:="+number);
    }

    @RequestMapping(value="/trackSections", method = RequestMethod.GET)
    String getParams(@RequestParam Map<String, String> params ) {
        // http://localhost:8080/trackSections?station=ril100&train=1&waggon=2
        return params.toString();
    }
}
