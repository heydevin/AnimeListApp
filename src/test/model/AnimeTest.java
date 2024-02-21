package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AnimeTest {
    private Anime anime;
    private Anime animeWithStatusB;
    private Anime animeWithStatusC;
    private Anime animeNullStatus;

    @BeforeEach
    void runBefore() {
        anime = new Anime("Kazuha", "a", "11/01");
        animeWithStatusB = new Anime("Kami", "b", "06/10");
        animeWithStatusC = new Anime("Kaito", "c", "09/21");
        animeNullStatus = new Anime("Null", "d", "01/01");
    }

    @Test
    void testConstructor() {
        assertEquals("Kazuha", anime.getName());
        assertEquals("11/01", anime.getDate());
        assertEquals(Anime.statusA, anime.getStatus());
        assertEquals(Anime.statusB, animeWithStatusB.getStatus());
        assertEquals(Anime.statusC, animeWithStatusC.getStatus());
        assertNull(animeNullStatus.getStatus());
    }

    @Test
    void testChangeStatusAtoB() {
        anime.changeStatus();
        assertEquals(Anime.statusB, anime.getStatus());
    }

    @Test
    void testChangeStatusBtoC() {
        animeWithStatusB.changeStatus();
        assertEquals(Anime.statusC, animeWithStatusB.getStatus());
    }

    @Test
    void testChangeStatusCtoNothing() {
        animeWithStatusC.changeStatus();
        assertEquals(Anime.statusC, animeWithStatusC.getStatus());
    }

    @Test
    void testToJsonCaseA() {
        JSONObject json = anime.toJson();
        assertEquals("Kazuha", json.get("Anime Name"));
        assertEquals(Anime.statusA, json.get("Anime Status"));
        assertEquals("11/01", json.get("Anime Date"));
    }

    @Test
    void testToJsonCaseB() {
        JSONObject json = animeWithStatusB.toJson();
        assertEquals("Kami", json.get("Anime Name"));
        assertEquals(Anime.statusB, json.get("Anime Status"));
        assertEquals("06/10", json.get("Anime Date"));
    }

    @Test
    void testToJsonCaseC() {
        JSONObject json = animeWithStatusC.toJson();
        assertEquals("Kaito", json.get("Anime Name"));
        assertEquals(Anime.statusC, json.get("Anime Status"));
        assertEquals("09/21", json.get("Anime Date"));
    }
}