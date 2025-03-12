package edu.uclm.esi.circuits.services;

import java.io.IOException;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

public class ProxyBEUsuarios {

    private static ProxyBEUsuarios yo;
    private final String url = "http://localhost:8081/tokens/";

    //Constructor privado
    private ProxyBEUsuarios() {
    }

    public void checkToken(String token) throws IOException {

        HttpGet httpGet = new HttpGet(url + "checkToken?token=" + token);
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpGet)) {
                int code = response.getCode();
                if (code != 200)
                    throw new Exception("El servicio solicitado requiere pago3");
            }
        } catch (Exception e) {
        }
    }

    //Metodo publico que devuelve una instancia de la clase
    public static ProxyBEUsuarios get() {
        if (yo == null)
            yo = new ProxyBEUsuarios();

        return yo;
    }
    
}
