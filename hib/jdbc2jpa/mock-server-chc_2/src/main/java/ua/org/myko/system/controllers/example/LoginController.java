package ua.org.myko.system.controllers.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.org.myko.system.controllers.AbstractController;

/**
 * Created by Myko Bova
 */
@Controller
@RequestMapping("example/login")
public class LoginController extends AbstractController {

    /**
     * http://localhost:8080/api/example/login/oauth/token?grant_type=client_credentials&client_id=client007&client_secret=abc&scope=ALL
     */
    @RequestMapping(path = "/oauth/token", method = RequestMethod.GET)
    @ResponseBody
    public String getToken(@RequestParam("grant_type") String grant_type, @RequestParam("client_id") String client_id, @RequestParam("client_secret") String client_secret, @RequestParam(value = "refresh_token", required = false) String refresh_token ){
        return getJsonFile("repositories/example/login/loginTokenResponse");
    }
}
