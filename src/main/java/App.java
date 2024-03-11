import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    double demo(int[] x_pos,int[] y_pos, int targetX, int targetY){

        if(x_pos == null || y_pos == null){
            return -1;
        }

        if(x_pos.length != y_pos.length || x_pos.length <= 0 || y_pos.length <= 0 ){
            return -1;
        }



        double minLength=Math.sqrt(Math.pow(Math.abs(x_pos[0]-targetX),2)+Math.pow(Math.abs(y_pos[0]-targetY),2));

        for(int i=1; i<x_pos.length; i++){

            if(minLength > Math.sqrt(Math.pow(Math.abs(x_pos[i]-targetX),2)+Math.pow(Math.abs(y_pos[i]-targetY),2))){
                minLength = Math.sqrt(Math.pow(Math.abs(x_pos[i]-targetX),2)+Math.pow(Math.abs(y_pos[i]-targetY),2));
            }
        }
        return minLength;

    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        // Basit bir anasayfa rotası
        get("/", (req, res) -> "Welcome to the Distance Calculator!");

        // Mesafe hesaplama işlemi için bir POST rotası
        post("/calculateDistance", (req, res) -> {
            // İstek parametrelerini al
            String[] xPositions = req.queryParams("x_pos").split(",");
            String[] yPositions = req.queryParams("y_pos").split(",");
            int targetX = Integer.parseInt(req.queryParams("targetX"));
            int targetY = Integer.parseInt(req.queryParams("targetY"));

            // String dizilerini int dizilerine dönüştür
            int[] x_pos = new int[xPositions.length];
            int[] y_pos = new int[yPositions.length];
            for (int i = 0; i < xPositions.length; i++) {
                x_pos[i] = Integer.parseInt(xPositions[i].trim());
                y_pos[i] = Integer.parseInt(yPositions[i].trim());
            }

            // demo metodunu çağır
            double minLength = new App().demo(x_pos, y_pos, targetX, targetY);

            // Sonucu haritaya koy
            Map<String, Object> model = new HashMap<>();
            model.put("distance", minLength);

            // Sonucu göstermek için Mustache template kullan
            return new ModelAndView(model, "result.mustache");
        }, new MustacheTemplateEngine());
    }


    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }



}
