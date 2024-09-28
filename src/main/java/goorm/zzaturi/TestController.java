package goorm.zzaturi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/callback")
    public String index(@RequestParam String code) {
        return code;
    }
}
