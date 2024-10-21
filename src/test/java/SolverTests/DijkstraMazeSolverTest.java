package SolverTests;

import backend.academy.MazeClasses.Edge;
import backend.academy.MazeClasses.Entity;
import backend.academy.MazeClasses.Maze;
import backend.academy.MazeClasses.Vertex;
import backend.academy.Solvers.DijkstraMazeSolver;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DijkstraMazeSolverTest extends BasicSolverTest {
    @BeforeAll
    public static void initSolver() {
        solver = new DijkstraMazeSolver();
    }

    @Test
    public void test1() {
        //Given
        Maze maze = new Maze(3, 3);
        maze.setStart(new Vertex(0, 0));
        maze.setEnd(new Vertex(2, 2));
        maze.matrix()[0][2] = Entity.Coin;
        maze.matrix()[1][1] = Entity.Forest;
        maze.matrix()[2][0] = Entity.Sea;
        maze.edges().add(new Edge(new Vertex(0, 0), new Vertex(1, 0)));
        maze.edges().add(new Edge(new Vertex(0, 0), new Vertex(0, 1)));
        maze.edges().add(new Edge(new Vertex(1, 0), new Vertex(1, 1)));
        maze.edges().add(new Edge(new Vertex(1, 0), new Vertex(2, 0)));
        maze.edges().add(new Edge(new Vertex(0, 1), new Vertex(0, 2)));
        maze.edges().add(new Edge(new Vertex(0, 1), new Vertex(1, 1)));
        maze.edges().add(new Edge(new Vertex(1, 1), new Vertex(1, 2)));
        maze.edges().add(new Edge(new Vertex(1, 1), new Vertex(2, 1)));
        maze.edges().add(new Edge(new Vertex(2, 0), new Vertex(2, 1)));
        maze.edges().add(new Edge(new Vertex(0, 2), new Vertex(1, 2)));
        maze.edges().add(new Edge(new Vertex(1, 2), new Vertex(2, 2)));
        maze.edges().add(new Edge(new Vertex(2, 1), new Vertex(2, 2)));
        Set<Vertex> vertices = new HashSet<>();
        Set<Edge> result;

        //When
        result = solver.solve(maze);
        for (Edge edge : result) {
            vertices.add(edge.v1());
            vertices.add(edge.v2());
        }

        //Then
        assertEquals(result.size(), 4);
        assertTrue(vertices.contains(new Vertex(2, 0)));
    }

    @Test
    public void test2() {
        //Given
        Maze maze = new Maze(4, 4);
        maze.setStart(new Vertex(0, 0));
        maze.setEnd(new Vertex(3, 3));
        maze.matrix()[2][1] = Entity.Coin;
        maze.matrix()[2][3] = Entity.Forest;
        maze.edges().add(new Edge(new Vertex(0, 0), new Vertex(1, 0)));
        maze.edges().add(new Edge(new Vertex(1, 0), new Vertex(2, 0)));
        maze.edges().add(new Edge(new Vertex(2, 0), new Vertex(3, 0)));
        maze.edges().add(new Edge(new Vertex(3, 0), new Vertex(3, 1)));
        maze.edges().add(new Edge(new Vertex(3, 1), new Vertex(3, 2)));
        maze.edges().add(new Edge(new Vertex(3, 2), new Vertex(3, 3)));
        maze.edges().add(new Edge(new Vertex(0, 0), new Vertex(0, 1)));
        maze.edges().add(new Edge(new Vertex(0, 1), new Vertex(0, 2)));
        maze.edges().add(new Edge(new Vertex(0, 2), new Vertex(0, 3)));
        maze.edges().add(new Edge(new Vertex(0, 3), new Vertex(1, 3)));
        maze.edges().add(new Edge(new Vertex(1, 3), new Vertex(1, 2)));
        maze.edges().add(new Edge(new Vertex(1, 2), new Vertex(2, 2)));
        maze.edges().add(new Edge(new Vertex(2, 2), new Vertex(2, 3)));
        maze.edges().add(new Edge(new Vertex(2, 3), new Vertex(3, 3)));
        Set<Vertex> vertices = new HashSet<>();
        Set<Edge> result;

        //When
        result = solver.solve(maze);
        for (Edge edge : result) {
            vertices.add(edge.v1());
            vertices.add(edge.v2());
        }

        //Then
        assertEquals(result.size(), 8);
        assertTrue(vertices.contains(new Vertex(1, 2)));
    }

    @Test
    public void test3() {
        //Given
        Maze maze = new Maze(4, 4);
        maze.setStart(new Vertex(0, 0));
        maze.setEnd(new Vertex(3, 3));
        maze.matrix()[2][1] = Entity.Coin;
        maze.matrix()[2][3] = Entity.Forest;
        maze.matrix()[1][0] = Entity.Sea;
        maze.edges().add(new Edge(new Vertex(0, 0), new Vertex(1, 0)));
        maze.edges().add(new Edge(new Vertex(1, 0), new Vertex(2, 0)));
        maze.edges().add(new Edge(new Vertex(2, 0), new Vertex(3, 0)));
        maze.edges().add(new Edge(new Vertex(3, 0), new Vertex(3, 1)));
        maze.edges().add(new Edge(new Vertex(3, 1), new Vertex(3, 2)));
        maze.edges().add(new Edge(new Vertex(3, 2), new Vertex(3, 3)));
        maze.edges().add(new Edge(new Vertex(0, 0), new Vertex(0, 1)));
        maze.edges().add(new Edge(new Vertex(0, 1), new Vertex(0, 2)));
        maze.edges().add(new Edge(new Vertex(0, 2), new Vertex(0, 3)));
        maze.edges().add(new Edge(new Vertex(0, 3), new Vertex(1, 3)));
        maze.edges().add(new Edge(new Vertex(1, 3), new Vertex(1, 2)));
        maze.edges().add(new Edge(new Vertex(1, 2), new Vertex(2, 2)));
        maze.edges().add(new Edge(new Vertex(2, 2), new Vertex(2, 3)));
        maze.edges().add(new Edge(new Vertex(2, 3), new Vertex(3, 3)));
        Set<Vertex> vertices = new HashSet<>();
        Set<Edge> result;

        //When
        result = solver.solve(maze);
        for (Edge edge : result) {
            vertices.add(edge.v1());
            vertices.add(edge.v2());
        }

        //Then
        assertEquals(result.size(), 6);
        assertTrue(vertices.contains(new Vertex(3, 2)));
    }
}
