package SolverTests;

import backend.academy.Solvers.AStarMazeSolver;
import backend.academy.Solvers.DijkstraMazeSolver;
import org.junit.jupiter.api.BeforeAll;

public class AStarMazeSolverTest extends DijkstraMazeSolverTest {
    @BeforeAll
    public static void initSolver() {
        solver = new AStarMazeSolver();
    }
}
