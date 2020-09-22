package ua.org.myko.system.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import ua.org.myko.system.util.FileUtil;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author Myko Bova
 */
@Controller
public abstract class AbstractController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

    @Autowired
    ServletContext context;

    @ModelAttribute
    public void setDefaultContentType(HttpServletResponse response) {
        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    public byte[] getRawFile(String... filePath) {
        String uploadPath = getFileNameInternal(filePath);
        LOGGER.info("---> Call " + uploadPath);
        byte[] rawile =  FileUtil.getRawFile(uploadPath);
        return rawile;
    }

    protected String getJsonFile(String... filePath) {
        String uploadPath = getFileNameInternal(filePath);
        LOGGER.info("---> Call " + uploadPath);
        String jsonFile = FileUtil.readJsonFile(uploadPath);
        return jsonFile;
    }

    protected String getFileNameInternal(String... filePath) {
        String pathRoot =  context.getRealPath("");
        pathRoot = getCorrectPath(pathRoot);
        ////System.out.println("****" + pathRoot);
        return pathRoot + File.separator + FileUtil.buildResourceName(filePath);
    }

    // Dirty hach - probably could be fixed later
    // If local server -  mock-server-chc\src\main\webapp\WEB-INF/classes
    //    real path in this case -
    //                    mock-server-chc\src\main\resources\
    // If "global" - apache-tomcat-7.0.70\webapps\mock\WEB-INF\classes\
    private boolean isLocalServer(String pathRoot) {
        return pathRoot.toLowerCase().contains("main".toLowerCase());
    }

    private String getCorrectPath(String pathRoot) {
        String realRoot;
        String  classesPath = "WEB-INF/classes";

        if(isLocalServer(pathRoot)) {
            int indexToTrim = pathRoot.indexOf("webapp");
            realRoot = pathRoot.substring(0, indexToTrim-1);
            realRoot = realRoot + File.separator + "resources";
        }
        else {
            pathRoot = StringUtils.trimTrailingCharacter(pathRoot, File.separatorChar);
            realRoot = pathRoot + File.separator + classesPath;
        }
        return realRoot;
    }

    public ServletContext getContext() {
        return context;
    }

    public void setContext(ServletContext context) {
        this.context = context;
    }
}
