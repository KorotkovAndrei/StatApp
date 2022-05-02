import org.example.stat.app.service.PoliticianService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PoliticianServiceTest {
    private PoliticianService service;
    private Map<String, Long> emptySpeakersMap;

    @Before
    public void setUp() {
        String file = "testFiles\\test.csv";
        try {
            service = new PoliticianService(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        emptySpeakersMap = new HashMap<>();
    }

    @Test
    public void whenPassEmptyParameterThanReturnNull() {
        assertNull(service.findMostSpeechesPolitician(emptySpeakersMap));
    }

    @Test
    public void whenPassNullThanReturnNull() {
        assertNull(service.findMostSpeechesPolitician(null));
    }

    @Test
    public void whenPassFullMapByYearThanReturnString() {
        assertNotNull(service.findMostSpeechesPolitician(service.groupPoliticiansByFullNameByYear("2012")));
    }

    @Test
    public void whenPassRightYearThanReturnMap() {
        assertNotNull(service.groupPoliticiansByFullNameByYear("2012"));
    }

    @Test
    public void whenPassZeroYearThanReturnEmptyMap() {
        assertEquals(service.groupPoliticiansByFullNameByYear("0"), emptySpeakersMap);
    }

    @Test
    public void whenPassEmptyTopicThanReturnEmptyMap() {
        assertEquals(service.groupPoliticiansByFullNameByTopic(""), emptySpeakersMap);
    }
}
