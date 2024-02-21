package persistence;

import model.AnimeList;
import model.Anime;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            AnimeList animeList = reader.read();
            List<Anime> pullAnimeList = animeList.getAnimeList();
            assertEquals("", pullAnimeList.get(0).getName());
            assertEquals(0, pullAnimeList.size());
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderAnimeList() {
        JsonReader reader = new JsonReader("./data/testReaderAnimeList.json");
        try {
            AnimeList animeList = reader.read();
            List<Anime> pullAnimeList = animeList.getAnimeList();
            checkAnime("Amber", Anime.statusA, "10/09", pullAnimeList.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}