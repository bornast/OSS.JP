package oss.transaction.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import oss.transaction.Bll.Dtos.MqttMessageDto;
import oss.transaction.Bll.Interfaces.IMqttService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@Controller
@RequestMapping(path="/mqtt")
public class MqttController {

    @Autowired
    private IMqttService mqttService;

    @PostMapping(path = "/publish")
    @ResponseBody
    public ResponseEntity createTransaction(@RequestBody MqttMessageDto mqttMessage) {
        try {
            mqttService.validateMqttMessage(mqttMessage);
            mqttService.publish(mqttMessage);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
