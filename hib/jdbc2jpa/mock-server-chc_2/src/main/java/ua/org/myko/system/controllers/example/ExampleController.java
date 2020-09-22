package ua.org.myko.system.controllers.example;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.org.myko.system.controllers.AbstractController;

/**
 * @author Myko Bova
 */
@Controller
@RequestMapping("/example")
public class ExampleController extends AbstractController {

    /**
     * Read a file by path that contains parameter
     * http://localhost:8080/api/example/account/12
     */
    @RequestMapping(path = "/account/{accountId}", method = RequestMethod.GET)
    @ResponseBody
    public String getAccount(@PathVariable String accountId) {
        return getJsonFile("repositories/example/account", accountId, "account");
    }

    /**
     * Read a file by constant path
     * http://localhost:8080/api/example/account/types
     */
    @RequestMapping(path = "/account/types", method = RequestMethod.GET)
    @ResponseBody
    public String getOperationTypes() {
        return getJsonFile("repositories/example/account/types");
    }

    /**
     * Read pdf file
     * http://localhost:8080/api/example/11/reports/2016-08-05
     */
    @RequestMapping(path = "{profileId}/reports/{reportDate}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> getReportByDate(@PathVariable String profileId, @PathVariable String reportDate){

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/pdf");
        return new ResponseEntity<>(getRawFile("repositories/example/profiles/report.pdf"), headers, HttpStatus.OK);
    }

    /**
     * Read cvs file
     * http://localhost:8080/api/example/reports2
     */
    @RequestMapping(path = "reports2", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<byte[]> defaultResponse() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.TEXT_PLAIN_VALUE);
        return new ResponseEntity<>(getRawFile("repositories/example/profiles/report.cvs"), headers, HttpStatus.OK);
    }
}
