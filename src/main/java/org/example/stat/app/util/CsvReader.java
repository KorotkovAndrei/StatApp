package org.example.stat.app.util;

import org.example.stat.app.pojo.Politician;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CsvReader {
    private static final String COMMA_SPACE = ", ";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private String line;
    private Scanner scanner;
    private int index;
    private int lineIndex;
    private List<Politician> politicians;

    public List<Politician> readFromCsvToList(String file) throws IOException {
        try (FileReader fileReader = new FileReader(file);
             BufferedReader reader = new BufferedReader(fileReader)) {
            initializeVariables();
            while ((line = reader.readLine()) != null) {
                Politician politician = new Politician();
                scanner = new Scanner(line);
                scanner.useDelimiter(COMMA_SPACE);
                while (scanner.hasNext()) {
                    populateData(scanner, politician, index, lineIndex);
                    index++;
                }
                index = 0;
                lineIndex++;
                politicians.add(politician);
            }
            return politicians;
        }
//        } catch (FileNotFoundException ex) {
//            System.out.println("file = " + file + " is not found!");
//            ex.printStackTrace();
//        } catch (IOException e) {
//            System.out.println("Exception of readFromCsvToList");
//            e.printStackTrace();
//        }
      //  return politicians;
    }

    private void initializeVariables() {
        line = null;
        scanner = null;
        index = 0;
        lineIndex = 1;
        politicians = new ArrayList<>();
    }

    private Date parseDate(String dateString) {
        Date date = null;
        try {
            date = new SimpleDateFormat(DATE_FORMAT).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private void populateData(Scanner scanner, Politician politician, int index, int lineIndex) {
        String data = scanner.next();
        if (index == 0) {
            politician.setFullName(data);
        } else if (index == 1) {
            politician.setTopic(data);
        } else if (index == 2) {
            politician.setDate(parseDate(data));
        } else if (index == 3) {
            politician.setWordsCount(Integer.parseInt(data));
        } else {
            System.out.println("Incorrect amount of data on line " + lineIndex);
        }
    }

}
