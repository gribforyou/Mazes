package MazeGeneratorsTests;

import backend.academy.Generators.MazeGenerators.MazeGenerator;
import backend.academy.MazeClasses.Edge;
import backend.academy.MazeClasses.Vertex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class IdealGeneratorTest {
    protected MazeGenerator generator;

    @BeforeEach
    void init() {
        generator = initGenerator();
    }

    protected abstract MazeGenerator initGenerator();

    @ParameterizedTest
    @CsvSource({
        "4, 4",
        "17, 3",
        "5, 44",
        "111, 123"
    })
    public void testSize(int width, int height) {
        assertEquals(generator.generate(width, height).size(), width * height - 1);
    }

    @ParameterizedTest
    @CsvSource({
        "-1, -1",
        "0, 0",
        "1, 2",
        "2, 0",
        "1, 1",
        "-3, -3"
    })
    public void testExceptions(int width, int height) {
        assertThrows(IllegalArgumentException.class, () -> generator.generate(width, height));
    }

    @ParameterizedTest
    @CsvSource({
        "11, 12",
        "5, 55",
        "100, 200",
        "221, 2",
        "2, 2",
        "8, 7"
    })
    public void testConnectivity(int width, int height) {
        Set<Vertex> vertices = new HashSet<>();
        Set<Edge> edges = generator.generate(height, width);
        for (Edge edge : edges) {
            vertices.add(edge.v1());
            vertices.add(edge.v2());
        }
        assertEquals(vertices.size(), height * width);
    }
}
