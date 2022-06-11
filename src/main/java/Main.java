import JsonClass.NasaImage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static final ObjectMapper mapper = new ObjectMapper();
    public static final String SERVER_NASA_URL = "https://api.nasa.gov/planetary/apod?api_key=LOVNidEwYZwbMajb0SHTTnOLJeTSmxr0GTBoupPQ";

    public static void main(String[] args) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное врем€ ожидание подключени€ к серверу
                        .setSocketTimeout(30000)    // максимальное врем€ ожидани€ получени€ данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        HttpGet request = new HttpGet(SERVER_NASA_URL);
        CloseableHttpResponse response = httpClient.execute(request);

        NasaImage infoImage = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
        });

        System.out.println(infoImage);
        System.out.println();

        String nameFile = infoImage.getUrl().substring(infoImage.getUrl().lastIndexOf("/") + 1);
        System.out.println(nameFile);
        response = httpClient.execute(new HttpGet(infoImage.getUrl()));

        //«апись ответа сервера в файл
        // ¬ариант 1
        File file = new File("c:\\Users\\Nikolay\\Downloads\\" + nameFile);
        byte[] bytes = response.getEntity().getContent().readAllBytes();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)){
            fileOutputStream.write(bytes);
        } catch (IOException exception){
            System.out.println(exception.getMessage());
        }

        // ¬ариант 2
//        URL url = new URL(infoImage.getUrl());
//        InputStream input = url.openStream();
//
//        Path path = Path.of("c:\\Users\\Nikolay\\Downloads\\" + nameFile);
//        Files.copy(input, path);


    }
}
