import db.jdbc.DbClient;
import db.jdbc.DbService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.MethodSource;
import ru.fintech.qa.homework.utils.BeforeUtils;

import java.sql.SQLException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static db.jdbc.DbService.executeQueryGetColumn;
import static db.jdbc.DbService.executeUpdate;

public class TestJdbc {
    private static int size;

    @BeforeAll
    public static void beforeAll() {
        BeforeUtils.createData();
        size = DbService.executeQueryGetTable("Select * from animal").size();
    }

    @AfterAll
    public static void afterAll() {
        try {
            DbService.getResultSet().close();
            DbService.getStatement().close();
            DbClient.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void firstTest() {
        Assertions.assertEquals(10,
                DbService.executeQueryGetTable("Select * from animal").size());

        DbService.printTable(DbService.executeQueryGetTable("Select * from animal"));
    }

    public static Stream<Integer> param() {
        return IntStream.range(1, size + 1).boxed();
    }

    @ParameterizedTest
    //@ValueSource(strings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"})
    @MethodSource("param")
    public final void secondTest(final int str) {
        Assertions.assertFalse(executeUpdate("Insert into animal (id) values (" + str + ")"));
        Assertions.assertEquals(size,
                DbService.executeQueryGetTable("Select * from animal").size());

        //DbService.printTable(DbService.executeQueryGetTable("Select * from animal"));
    }

    @Test
    public void thirstTest() {
        int sizeWork = DbService.executeQueryGetTable("Select * from workman").size();
        Assertions.assertFalse(executeUpdate("Insert into workman (id, \"name\")"
                + " values (" + sizeWork + 1 + ", NULL)"));
        Assertions.assertEquals(sizeWork,
                DbService.executeQueryGetTable("Select * from workman").size());

        DbService.printTable(DbService.executeQueryGetTable("Select * from workman"));
    }

    @Test
    public void fourthTest() {
        int sizePlaces = DbService.executeQueryGetTable("Select * from places").size();
        Assertions.assertTrue(executeUpdate("Insert into places (id) values (" + (sizePlaces + 1) + ")"));
        Assertions.assertEquals(6,
                DbService.executeQueryGetTable("Select * from places").size());

        DbService.printTable(DbService.executeQueryGetTable("Select * from places"));
        executeUpdate("DELETE from places where id = " + (sizePlaces + 1));
    }

    @Test
    public void fifthTest() {
        Assertions.assertEquals(3,
                DbService.executeQueryGetTable("Select * from zoo").size());

        Assertions.assertEquals("Центральный",
                executeQueryGetColumn("name", "Select * from zoo where id = 1 and \"name\" = 'Центральный'"));

        Assertions.assertEquals("Северный",
                executeQueryGetColumn("name", "Select * from zoo where id = 2 and \"name\" = 'Северный'"));

        Assertions.assertEquals("Западный",
                executeQueryGetColumn("name", "Select * from zoo where id = 3 and \"name\" = 'Западный'"));

        DbService.printTable(DbService.executeQueryGetTable("Select * from zoo"));

    }
}
