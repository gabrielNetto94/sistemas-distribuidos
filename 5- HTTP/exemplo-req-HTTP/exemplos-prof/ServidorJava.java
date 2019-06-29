/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requisicoes;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServidorJava {

    //http://localhost:8000/test 
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/exemplo", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange t) throws IOException {
            //retira os parâmetros
            System.out.println("Requisição do tipo: " + t.getRequestMethod());
            String parametros = "";
            //se for GET
            if (t.getRequestMethod().equals("GET")) {
                parametros = t.getRequestURI().getQuery();
                System.out.println(parametros);
            }
            //se for POST
            else if (t.getRequestMethod().equals("POST")) {
                InputStreamReader isr = new InputStreamReader(t.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                int b;
                StringBuilder buf = new StringBuilder(512);
                while ((b = br.read()) != -1) {
                    buf.append((char) b);
                }
                parametros = buf.toString();
                System.out.println(parametros);
                br.close();
                isr.close();
            }

            //envia uma resposta
            String resposta = "Hello World, recebi os seguintes parametros: "+parametros;
            t.sendResponseHeaders(200, resposta.length());
            OutputStream os = t.getResponseBody();
            os.write(resposta.getBytes());
            os.close();
        }
    }

}
