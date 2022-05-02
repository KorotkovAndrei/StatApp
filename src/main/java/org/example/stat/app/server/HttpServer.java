package org.example.stat.app.server;

import org.example.stat.app.service.PoliticianService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static spark.Spark.*;

public class HttpServer {
    private static final String YEAR = "2013";
    private static final String TOPIC = "Internal Security";
    private static final String URL = "url";

    public static void main(String[] args) {
        get("/evaluation", "application/json", (request, response) -> {
            List<String> files = Arrays.asList(request.queryParamsValues(URL));
            List<JsonWrapper> wrappers = new ArrayList<>();
            files.forEach(file -> {
                List<String> data = null;
                try {
                    data = getDataForResponse(file);
                    wrappers.add(new JsonWrapper(data.get(0), data.get(1), data.get(2)));
                } catch (FileNotFoundException ex) {
                    wrappers.add(new JsonWrapper("File " + file + " is not found!"));
                } catch (IOException e) {
                    wrappers.add(new JsonWrapper("Exception while read from file " + file));
                }
            });
            return wrappers;
        }, new JsonTransformer());

    }

    private static List<String> getDataForResponse(String file) throws IOException {
        List<String> data = new ArrayList<>();
        PoliticianService service = new PoliticianService(file);
        Map<String, Long> speakersMapYear = service.groupPoliticiansByFullNameByYear(YEAR);
        Map<String, Long> speakersMapTopic = service.groupPoliticiansByFullNameByTopic(TOPIC);
        data.add(0, service.findMostSpeechesPolitician(speakersMapYear));
        data.add(1, service.findMostSpeechesPolitician(speakersMapTopic));
        data.add(2, service.findLeastWordyPolitician());
        return data;
    }
}
