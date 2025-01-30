package edu.uclm.esi.circuits.http;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.uclm.esi.circuits.services.CircuitService;

@RestController
@RequestMapping("/circuits")
public class CircuitsController {

    @Autowired
    private CircuitService service;

    @GetMapping("/createCircuit")
    public Map<String,Object> postCircuit(@RequestParam int qubits) {
        if (qubits < 1) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The number of qubits must be greater than 0");
        }
        
        return this.service.createCircuit(qubits);
    }
}