package SolverTests;

import backend.academy.Generators.MazeGenerators.KrascalMazeGenerator;
import backend.academy.Generators.MazeGenerators.MazeGenerator;
import backend.academy.MazeClasses.Edge;
import backend.academy.MazeClasses.Maze;
import backend.academy.MazeClasses.Vertex;
import backend.academy.Solvers.MazeSolver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class BasicSolverTest {
    protected static MazeSolver solver;

    @RepeatedTest(10)
    void checkPathCorrection() {
        //Given
        Maze maze = new Maze(25, 25);
        MazeGenerator generator = new KrascalMazeGenerator();
        maze.generateEdges(generator);
        maze.setStart(new Vertex(0, 0));
        maze.setEnd(new Vertex(24, 24));
        Set<Vertex> vertices = new HashSet<>();
        Set<Edge> result;

        //When
        result = solver.solve(maze);
        for (Edge edge : result) {
            vertices.add(edge.v1());
            vertices.add(edge.v2());
        }

        //Then
        assertEquals(vertices.size(), result.size() + 1);
        for (Edge edge : result) {
            assertTrue(maze.edges().contains(edge));
        }
    }
}
