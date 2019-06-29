package requisicoes;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ClienteJava {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        ClienteJava http = new ClienteJava();

        System.out.println("Teste 1 - Http GET request");
        http.sendGetJava();

        System.out.println("\nTeste 2 - Http POST request");
        http.sendPostJava();

    }

    // HTTP GET request
    private void sendGetJava() throws Exception {

        String url = "http://localhost:8000/exemplo?parametro1=5&parametro2=6";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //definição de que iremos utilizar o método GET
        con.setRequestMethod("GET");

        //adicionamos o cabeçalho da requisição, definindo o "user agent", ou seja, o perfil de navegação
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nEnviando requisição 'GET' para a URL : " + url);
        System.out.println("Código de resposta : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String linha;

        //mostramos o resultado
        while ((linha = in.readLine()) != null) {
            System.out.println(linha);
            //response.append(inputLine);
        }
        in.close();

    }
    
    // HTTP GET request
    private void sendGetPHP() throws Exception {

        String url = "http://localhost/exemplos/servidor.php?parametro1=5&parametro2=6";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //definição de que iremos utilizar o método GET
        con.setRequestMethod("GET");

        //adicionamos o cabeçalho da requisição, definindo o "user agent", ou seja, o perfil de navegação
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nEnviando requisição 'GET' para a URL : " + url);
        System.out.println("Código de resposta : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String linha;

        //mostramos o resultado
        while ((linha = in.readLine()) != null) {
            System.out.println(linha);
            //response.append(inputLine);
        }
        in.close();

    }

    //HTTP POST request
    private void sendPostJava() throws Exception {

        String url = "http://localhost:8000/exemplo";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //adicionamos o cabeçalho da requisição
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        //definição dos parâmetros
        String urlParameters = "parametro1=5&parametro2=4";

        // Envio da requisição
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nEnviando requisição 'POST' para a URL : " + url);
        System.out.println("Parâmetros : " + urlParameters);
        System.out.println("Código de resposta : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String linha;

        //mostramos o resultado
        while ((linha = in.readLine()) != null) {
            System.out.println(linha);
            //response.append(inputLine);
        }
        in.close();
    }
    
    //HTTP POST request
    private void sendPostPHP() throws Exception {

        String url = "http://localhost/exemplos/servidor.php";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //adicionamos o cabeçalho da requisição
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        //definição dos parâmetros
        String urlParameters = "parametro1=5&parametro2=4";

        // Envio da requisição
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nEnviando requisição 'POST' para a URL : " + url);
        System.out.println("Parâmetros : " + urlParameters);
        System.out.println("Código de resposta : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String linha;

        //mostramos o resultado
        while ((linha = in.readLine()) != null) {
            System.out.println(linha);
            //response.append(inputLine);
        }
        in.close();
    }

}
