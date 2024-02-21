package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimeListTest {
    private Anime anime;
    private AnimeList animeList = new AnimeList();


    @BeforeEach
    void runBefore() {
        anime = new Anime("Kazuha", "a", "11/21");
        animeList.addAnime(anime);
        anime = new Anime("Ayaka", "b", "10/28");
        animeList.addAnime(anime);
        anime = new Anime("Yoimiya", "c", "06/20");
        animeList.addAnime(anime);
    }

    @Test
    void testAdd() {
        anime = new Anime("Yelan", "c", "03/22");
        animeList.addAnime(anime);
        assertTrue(animeList.getAnimeList().contains(anime));
    }

    @Test
    void testRemove() {
        animeList.remove("Ayaka");
        anime = new Anime("Ayaka", "b", "10/28");
        assertFalse(animeList.getAnimeList().contains(anime));
    }

    @Test
    void testMark() {
        animeList.mark("Kazuha");
        assertEquals(Anime.statusB, animeList.getAnimeList().get(1).getStatus());
        animeList.mark("Ayaka");
        assertEquals(Anime.statusC, animeList.getAnimeList().get(1).getStatus());
    }

    @Test
    void testGetAnimeList() {
        List<Anime> pullAnimeList = animeList.getAnimeList();
        assertEquals("Kazuha", pullAnimeList.get(0).getName());
        assertEquals(Anime.statusA, pullAnimeList.get(0).getStatus());
        assertEquals("11/21", pullAnimeList.get(0).getDate());
        assertEquals("Ayaka", pullAnimeList.get(1).getName());
        assertEquals(Anime.statusB, pullAnimeList.get(1).getStatus());
        assertEquals("10/28", pullAnimeList.get(1).getDate());
        assertEquals("Yoimiya", pullAnimeList.get(2).getName());
        assertEquals(Anime.statusC, pullAnimeList.get(2).getStatus());
        assertEquals("06/20", pullAnimeList.get(2).getDate());

    }

    @Test
    void testToJson() {
        JSONObject json = animeList.toJson();
        assertFalse(json.isEmpty());
    }
}