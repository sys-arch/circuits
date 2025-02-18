package edu.uclm.esi.circuits.http;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.uclm.esi.circuits.model.Circuit;
import edu.uclm.esi.circuits.services.CircuitService;

@RestController
@RequestMapping("/circuits")
public class CircuitsController {

    @Autowired
    private CircuitService service;

    @PostMapping("/createCircuit")
    public String createCircuit(@RequestBody Map<String, Object> body) {
        if (!body.containsKey("table") || !body.containsKey("outputQubits")) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "La petición debe contener los campos qubits y outputQubits");
        }
        /*if (qubits < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The number of qubits must be greater than 0");
        }*/
        
        return this.service.createCircuit(body);
    }

    @PostMapping("/generateCode")
    public String generateCode(@RequestBody Circuit circuit) {
        /*if (!body.containsKey("table") || !body.containsKey("outputQubits")) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "La petición debe contener los campos qubits y outputQubits");
        }
        /*if (qubits < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The number of qubits must be greater than 0");
        }*/
        
        return this.service.generateCode(circuit);
    }
}