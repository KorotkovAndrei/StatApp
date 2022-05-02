package org.example.stat.app.service;

import org.example.stat.app.pojo.Politician;
import org.example.stat.app.util.CsvReader;

import java.io.IOException;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class PoliticianService {
    private static final String ZERO = "0";
    private static final String EMPTY = "";
    private List<Politician> politicians;

    public PoliticianService(String file) throws IOException {
        this.politicians = new CsvReader().readFromCsvToList(file);
    }

    public String findMostSpeechesPolitician(Map<String, Long> speakersMap) {
        String result = null;
        List<String> speakers = new ArrayList<>();
        if (speakersMap != null && !speakersMap.isEmpty()) {
            Long maxValue = speakersMap.values().stream().max(Long::compare).get();
            Collection<String> collection = speakersMap.keySet();

            for (String key : collection) {
                if (speakersMap.get(key).equals(maxValue)) {
                    speakers.add(key);
                }
            }

            if (speakers.size() == 1) {
                result = speakers.get(0);
            }
        }

        return result;
    }

    public String findLeastWordyPolitician() {
        String result = null;
        List<String> speakers = new ArrayList<>();
        int minWordsCount = politicians.stream().map(Politician::getWordsCount)
                .min(Integer::compare).get();
        if (minWordsCount == 0 || minWordsCount > 0) {
            speakers = politicians.stream()
                    .filter(politician -> politician.getWordsCount() == minWordsCount)
                    .map(Politician::getFullName)
                    .collect(Collectors.toList());
            if (speakers.size() == 1) {
                result = speakers.get(0);
            }
        }
        return result;
    }

    public Map<String, Long> groupPoliticiansByFullNameByYear(String year) {
        Map<String, Long> politiciansByFullName = new HashMap<>();
        if (!year.equals(ZERO)) {
            List<Politician> politiciansByYear = politicians.stream()
                    .filter(sp -> getYear(sp) == Integer.parseInt(year))
                    .collect(Collectors.toList());
            politiciansByFullName = politiciansByYear.stream()
                    .collect(Collectors.groupingBy(Politician::getFullName, Collectors.counting()));
        }
        return politiciansByFullName;
    }

    public Map<String, Long> groupPoliticiansByFullNameByTopic(String topic) {
        Map<String, Long> politiciansByFullName = new HashMap<>();
        if (!topic.equals(EMPTY)) {
            List<Politician> politiciansByYear = politicians.stream()
                    .filter(sp -> sp.getTopic().equalsIgnoreCase(topic))
                    .collect(Collectors.toList());
            politiciansByFullName = politiciansByYear.stream()
                    .collect(Collectors.groupingBy(Politician::getFullName, Collectors.counting()));
        }
        return politiciansByFullName;
    }

    private int getYear(Politician politician) {
        return politician.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
    }
}
