package dao;

import com.github.tisv2000.video_library.dao.GenreDao;
import com.github.tisv2000.video_library.entity.Genre;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.List;
import java.util.Optional;

public class GenreDaoTest {

    private final GenreDao genreDAO = GenreDao.getInstance();

    public static final Integer GENRE_ID = 6;
    public static final String GENRE_TITLE = "Drama";

    @Test
    public void findGenreByIdPositiveTest() {
        Optional<Genre> maybeGenre = genreDAO.findById(GENRE_ID);

        assertTrue(maybeGenre.isPresent(), "Genre is missing!");
        assertEquals(maybeGenre.get().getTitle(), GENRE_TITLE, "Genre title is wrong!");
    }

    @Test
    public void findAllGenres() {
        List<Genre> genres = genreDAO.findAll();

        assertEquals(9, genres.size(), "Amount of genre titles is not correct!");
    }
}
