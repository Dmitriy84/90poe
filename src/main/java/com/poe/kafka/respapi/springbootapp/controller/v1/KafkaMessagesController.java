package com.poe.kafka.respapi.springbootapp.controller.v1;

import com.poe.kafka.respapi.springbootapp.dto.KafkaMessageModel;
import com.poe.kafka.respapi.springbootapp.dto.ResponseTransfer;
import com.poe.kafka.respapi.springbootapp.service.IKafkaMessageService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@OpenAPIDefinition(servers = {@Server(url = "http://localhost:7001")}, info = @Info(title = "90poe test task", version = "v1", description = "A completed test task for sdet position for 90poe", license = @License(name = "MIT License", url = "https://github.com/Dmitriy84"), contact = @Contact(url = "https://www.linkedin.com/in/dmitriymalohlovets/", name = "Dmytro Malohlovets")))
@RestController
@RequestMapping("v1/kafka")
public class KafkaMessagesController {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMessagesController.class);

    @Autowired
    public KafkaMessagesController(KafkaTemplate producer) {
        this.producer = producer;
    }

    @Autowired
    private final KafkaTemplate producer;

    @Autowired
    private IKafkaMessageService service;

    @Value("${spring.kafka.topic}")
    private String topic;

    @Operation(summary = "Put message to the topic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(type = "object"))),
            @ApiResponse(responseCode = "500", description = "internal server error")})
    @PostMapping(value = "/publish", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseTransfer> sendMessageToKafkaTopic(@RequestBody KafkaMessageModel message) {
        try {
            System.out.println("Message received: " + message);
            service.save(message);
            producer.send(topic, message);

            return ResponseEntity.ok(new ResponseTransfer("pushed to kafka"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get message from the topic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(type = "object"))),
            @ApiResponse(responseCode = "500", description = "internal server error")})
    @GetMapping(value = "/consume", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<KafkaMessageModel>> consumeMessageFromKafkaTopic() {
        try {
            var consumed = new ArrayList<>(service.getConsumedMessages());
            service.cleanConsumedMessages();
            return ResponseEntity.ok(consumed);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
