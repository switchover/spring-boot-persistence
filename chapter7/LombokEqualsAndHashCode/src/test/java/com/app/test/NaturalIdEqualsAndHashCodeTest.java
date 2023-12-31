package com.app.test;

import com.app.NaturalIdBook;
import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@Rollback(false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NaturalIdEqualsAndHashCodeTest {
    @Autowired
    private TestEntityManager entityManager;

    private static final NaturalIdBook book = new NaturalIdBook();
    private static final Set<NaturalIdBook> books = new HashSet<>();

    @BeforeClass
    public static void setUp() {
        book.setTitle("Modern History");
        book.setIsbn("001-100-000-111");

        books.add(book);
    }

    @Test
    // Find in Set the book that has never been persisted
    // State transition at assertion point: NEW
    public void A_givenBookInSetWhenContainsThenTrue() throws Exception {
        assertTrue(books.contains(book));
    }

    @Test
    // Find in Set the book after it was persisted
    // State transition at first assertion point: NEW
    // State transition at second and third assertion point: MANAGED
    public void B_givenBookWhenPersistThenSuccess() throws Exception {
        assertNull(book.getId());

        entityManager.persistAndFlush(book);
        assertNotNull(book.getId());

        assertTrue(books.contains(book));
    }

    @Test
    // Find in Set the book after merge() was called - SELECT and UPDATE statement  
    // State transition at assertion point: MANAGED
    public void C_givenBookWhenMergeThenSuccess() throws Exception {
        book.setTitle("New Modern History");
        NaturalIdBook mergedBook = entityManager.merge(book);
        entityManager.flush();

        assertTrue(books.contains(mergedBook));
    }

    @Test
    // Find in Set the book after find() was called - SELECT statement    
    // State transition at assertion point: MANAGED
    public void D_givenBookWhenFindThenSuccess() throws Exception {
        // NaturalIdBook foundBook = entityManager.find(NaturalIdBook.class, book.getId());
        NaturalIdBook foundBook = entityManager
                .getEntityManager().unwrap(Session.class)
                .bySimpleNaturalId(NaturalIdBook.class).load("001-100-000-111");
        entityManager.flush();

        assertTrue(books.contains(foundBook));
    }

    @Test
    // Find in Set the book after detach() was called
    // State transition at assertion point: DETACHED    
    public void E_givenBookWhenFindAndDetachThenSuccess() throws Exception {
        // NaturalIdBook foundBook = entityManager.find(NaturalIdBook.class, book.getId());
        NaturalIdBook foundBook = entityManager
                .getEntityManager().unwrap(Session.class)
                .bySimpleNaturalId(NaturalIdBook.class).load("001-100-000-111");
        entityManager.detach(foundBook);

        assertTrue(books.contains(foundBook));
    }

    @Test
    // Find in Set the book after remove() was called - DELETE statement
    // State transition at assertion points: REMOVED    
    public void F_givenBookWhenFindAndRemoveThenSuccess() throws Exception {
        // NaturalIdBook foundBook = entityManager.find(NaturalIdBook.class, book.getId());
        NaturalIdBook foundBook = entityManager
                .getEntityManager().unwrap(Session.class)
                .bySimpleNaturalId(NaturalIdBook.class).load("001-100-000-111");
        entityManager.remove(foundBook);
        entityManager.flush();

        assertTrue(books.contains(foundBook));

        books.remove(foundBook);

        assertFalse(books.contains(foundBook));
    }
}
