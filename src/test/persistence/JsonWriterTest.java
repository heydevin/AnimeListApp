package persistence;

import model.Anime;
import model.AnimeList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterAnimeList() {
        try {
            Anime newAnimeA = new Anime("Amber", "a", "09/18");
            Anime newAnimeB = new Anime("Jean", "b", "04/09");
            Anime newAnimeC = new Anime("Mona", "c", "10/04");
            AnimeList animeList = new AnimeList();
            animeList.addAnime(newAnimeA);
            animeList.addAnime(newAnimeB);
            animeList.addAnime(newAnimeC);
            JsonWriter writer = new JsonWriter("./data/testWriterAnimeList.json");
            writer.open();
            writer.write(animeList);
            writer.close();

            AnimeList testAnimeList;
            JsonReader reader = new JsonReader("./data/testWriterAnimeList.json");
            testAnimeList = reader.read();
            List<Anime> pullAnimeList = testAnimeList.getAnimeList();
            checkAnime("Amber", Anime.statusA, "09/18", pullAnimeList.get(0));
            checkAnime("Jean", Anime.statusB, "04/09", pullAnimeList.get(1));
            checkAnime("Mona", Anime.statusC, "10/04", pullAnimeList.get(2));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}