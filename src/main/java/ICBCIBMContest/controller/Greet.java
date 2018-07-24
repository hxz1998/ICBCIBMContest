package ICBCIBMContest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Greet {
    @RequestMapping("/")
    public String index() {
        return "/index.html";
    }
}
