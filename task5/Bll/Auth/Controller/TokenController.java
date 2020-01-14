package oss.transaction.Bll.Auth.Controller;

import org.springframework.web.bind.annotation.*;
import oss.transaction.Bll.Auth.Model.JwtUser;
import oss.transaction.Bll.Auth.Security.JwtGenerator;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/token")
public class TokenController {


    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public String generate(@RequestBody final JwtUser jwtUser) {

        String token = jwtGenerator.generate(jwtUser);
        return "{\"token\": \""+token+"\"}";

    }
}
