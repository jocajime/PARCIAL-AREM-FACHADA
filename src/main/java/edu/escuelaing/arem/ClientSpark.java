package edu.escuelaing.arem;

import spark.Request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static spark.Spark.*;

public class ClientSpark {
    public static String url = "https://fierce-oasis-57440.herokuapp.com/";

    public static void main(String[] args) {
        port(getPort());
        get("/",((request, response) -> {
            response.redirect("/Calculadora");
            return null;
        }));
        get("/Calculadora", (request, response) -> basePage());
        get("/Results",(request, response) -> resultado(request));
    }

    private static String basePage(){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Calculadora COS,SIN,TAN</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<center>\n" +
                "<h1 style=\"color: red\">BIENVENIDOS A MI SOLUCION</h1>\n" +
                "<h4>Escriba numero y unda de las tres opciones (cos,sin,tan)</h4>\n" +
                "<form action=\"/Results\"><input type=\"text\" name=\"numero\"></br></br><input type=\"text\" name=\"tipo\"></br><input type=\"submit\" value=\"Calcular\"></form>\n" +
                "</center>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    private static String resultado(Request request) throws Exception {
        connect connect = new connect ();
        String numero = request.queryParams ("numero");
        String tipo = request.queryParams ("tipo");
        String resultado = connect.conectar (url+"/Respuesta?numero="+numero+"&tipo="+tipo);
        System.out.println (resultado);
        return  basePage ()+"<center><h3>Resultado: "+ resultado +"<h3/></center>";
    }


    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
