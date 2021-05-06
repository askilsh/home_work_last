import db.hibernate.HibernateSession;
import db.hibernate.models.Animal;
import db.hibernate.models.Places;
import db.hibernate.models.Workman;
import db.hibernate.models.Zoo;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.fintech.qa.homework.utils.BeforeUtils;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestHibernate {
    private static int size;

    @BeforeAll
    public static void beforeAll() {
        BeforeUtils.createData();
        size = HibernateSession.getSession(Animal.class).
                createNativeQuery("select * from animal", Animal.class).getResultList().size();
    }

    @AfterAll
    public static void afterAll() {
        HibernateSession.getSession(Object.class).close();
    }

    @Test
    public void firstTest() {
        Session session = HibernateSession.getSession(Animal.class);
        Assertions.assertEquals(10, session.
                createNativeQuery("select * from animal", Animal.class).getResultList().size());
    }

    public static Stream<Integer> param() {
        return IntStream.range(1, size + 1).boxed();
    }

    @ParameterizedTest
    //@ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"})
    @MethodSource("param")
    public final void secondTest(final int str) {
        Session session = HibernateSession.getSession(Animal.class);
        Animal an = new Animal();
        an.setId(str);
        Assertions.assertFalse(HibernateSession.saveObj(session, an));
        Assertions.assertEquals(size, session.
                createNativeQuery("select * from animal", Animal.class).getResultList().size());
    }

    @Test
    public void thirstTest() {
        Session session = HibernateSession.getSession(Workman.class);
        int sizeWork = session.
                createNativeQuery("select * from workman", Workman.class).getResultList().size();
        Workman workman = new Workman();
        workman.setId(sizeWork + 1);
        workman.setName(null);
        Assertions.assertFalse(HibernateSession.saveObj(session, workman));
        Assertions.assertEquals(sizeWork, session.
                createNativeQuery("select * from workman", Workman.class).getResultList().size());
    }

    @Test
    public void fourthTest() {
        Session session = HibernateSession.getSession(Places.class);
        int sizePlaces = session.
                createNativeQuery("select * from places", Places.class).getResultList().size();
        Places places = new Places();
        places.setId(sizePlaces + 1);
        Assertions.assertTrue(HibernateSession.saveObj(session, places));
        Assertions.assertEquals(6, session.
                createNativeQuery("select * from places", Places.class).getResultList().size());
    }

    @Test
    public void fifthTest() {
        Session session = HibernateSession.getSession(Zoo.class);
        List<Zoo> table = session.createNativeQuery("select * from zoo", Zoo.class).getResultList();
        Assertions.assertEquals(3, table.size());
        Assertions.assertEquals("Центральный", table.get(0).getName());
        Assertions.assertEquals("Северный", table.get(1).getName());
        Assertions.assertEquals("Западный", table.get(2).getName());
    }
}
