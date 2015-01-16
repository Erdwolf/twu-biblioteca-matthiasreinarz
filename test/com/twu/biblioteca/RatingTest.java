package com.twu.biblioteca;


import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class RatingTest {
    @Test
    public void testRatingNotNull() {
        String msg = "rating(int) should create non-null Rating!";
        assertNotNull(msg, Rating.rating(1));
        assertNotNull(msg, Rating.rating(2));
        assertNotNull(msg, Rating.rating(3));
        assertNotNull(msg, Rating.rating(4));
        assertNotNull(msg, Rating.rating(5));
        assertNotNull(msg, Rating.rating(6));
        assertNotNull(msg, Rating.rating(7));
        assertNotNull(msg, Rating.rating(8));
        assertNotNull(msg, Rating.rating(9));
        assertNotNull(msg, Rating.rating(10));

        assertNotNull("Rating.best should be non-null!",    Rating.best);
        assertNotNull("Rating.worst should be non-null!",   Rating.worst);
        assertNotNull("Rating.unrated should be non-null!", Rating.unrated);
    }

    @Test
    public void testScale() {
        assertEquals("rating(10) should be the best!", Rating.rating(10), Rating.best);
        assertEquals("rating(1) should be the worst!", Rating.rating(1),  Rating.worst);
    }

    @Test
    public void testEquality() {
        String msg = "Same ratings should be equal!";

        assertEquals(msg, Rating.unrated, Rating.unrated);
        assertEquals(msg, Rating.best, Rating.best);
        assertEquals(msg, Rating.worst, Rating.worst);

        for (int i=1; i<=10; i++) {
            assertEquals(msg, Rating.rating(i), Rating.rating(i));
        }
    }

    @Test
    public void testInequality() {
        List<Rating> ratings = Arrays.asList(
                Rating.rating(1),
                Rating.rating(2),
                Rating.rating(3),
                Rating.rating(4),
                Rating.rating(5),
                Rating.rating(6),
                Rating.rating(7),
                Rating.rating(8),
                Rating.rating(9),
                Rating.rating(10));

        assertEquals("Ratings 1-10 should all be different. Unique elements:", 10, new HashSet<Rating>(ratings).size());
    }

}
