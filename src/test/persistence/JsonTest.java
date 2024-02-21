package persistence;

import model.Anime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkAnime(String name, String status, String date, Anime testAnime) {
        assertEquals(name, testAnime.getName());
        assertEquals(status, testAnime.getStatus());
        assertEquals(date, testAnime.getDate());
    }
}
