package org.example;


import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String Base_URL = "https://yesno.wtf/api";
        System.out.println("Ведите вопрос на который можно ответить да или нет:");
        String t = scan.nextLine();
        try {
            FileWriter fileWriter = new FileWriter("api.txt", false);
            URL url = new URL(Base_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder responce = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                responce.append(inputLine);
            }
            in.close();
            conn.disconnect();

            JSONObject jsonResponce = new JSONObject(responce.toString());
            String answer = jsonResponce.getString("answer");
            System.out.println(answer);
            String image = jsonResponce.getString("image");
            System.out.println(image);
            fileWriter.write(t + "\n");
            fileWriter.write(answer + "\n");
            fileWriter.write(image + "\n");
            fileWriter.close();
        }
        catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}