package edu.escuelaing.arem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class connect implements connection{
    @Override
    public String conectar(String url) throws Exception {
        StringBuilder resultado = new StringBuilder ();
        URL url1 = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) url1.openConnection ();
        connection.setRequestMethod ("GET");
        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (connection.getInputStream ()));
        String inputline;
        while((inputline = bufferedReader.readLine ()) != null){
            resultado.append (inputline);
        }
        bufferedReader.close ();
        return resultado.toString ();
    }
}
