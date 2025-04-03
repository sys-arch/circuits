package edu.uclm.esi.circuits.http;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import edu.uclm.esi.circuits.model.Circuit;
import edu.uclm.esi.circuits.services.CircuitService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/circuits")
public class CircuitsController {

    @Autowired
    private CircuitService service;
<<<<<<< Updated upstream

=======
 
>>>>>>> Stashed changes
    @PostMapping("/createCircuit")
    public Circuit createCircuit(@RequestBody Map<String, Object> body, @RequestHeader("Authorization") String token) {
        if (token == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No se ha proporcionado un token de autenticación");
        } else {
            try {
                this.service.checkToken(token);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No se ha podido validar el token de autenticación");
            }
        }

        if (!body.containsKey("table") || !body.containsKey("outputQubits")) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "La petición debe contener todos los campos necesarios");
        }
        
        return this.service.createCircuit(body);
    }

    @PostMapping("/generateCode")
    public Map<String,Object> generateCode(HttpServletRequest request, @RequestParam(required=false) String name, @RequestBody Circuit circuit) {

       if(name != null) {
           circuit.setName(name);
       }

       String token = request.getHeader("Authorization");
       if(token == null) {
           throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No se ha proporcionado un token de autenticación");
       }

       try {
        return this.service.generateCode(circuit, token);
<<<<<<< Updated upstream
       } catch (Exception e) {
           throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED, "No hay suficientes creditos para generar el circuito");
       }
    
    }
=======
    } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED, "Son necesarios créditos para generar el código del circuito");
    }

    
    }
    
>>>>>>> Stashed changes
}