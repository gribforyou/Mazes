package EntityGeneratorsTests;

import backend.academy.Generators.EntityGenerators.BasicEntityGenerator;
import backend.academy.Generators.EntityGenerators.EntityGenerator;
import backend.academy.MazeClasses.Entity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasicEntityGeneratorTest {
    private static EntityGenerator entityGenerator;

    @BeforeAll
    static void init(){
        entityGenerator = new BasicEntityGenerator();
    }

    @ParameterizedTest
    @CsvSource({
        "1, 2",
        "2, 1",
        "-3, -3"
    })
    void testException(int height, int width){
        assertThrows(IllegalArgumentException.class, () ->entityGenerator.generate(height, width));
    }

    @ParameterizedTest
    @CsvSource({
        "3, 3",
        "5, 5",
        "100, 150"
    })
    void testNotNull(int height, int width){
        Entity[][] entities = entityGenerator.generate(height, width);
        assertNotNull(entities);
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                assertNotNull(entities[i][j]);
            }
        }
    }
}
