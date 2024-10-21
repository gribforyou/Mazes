package SolverTests;

import backend.academy.Solvers.BFSMazeSolver;
import backend.academy.Solvers.MazeSolver;
import org.junit.jupiter.api.BeforeAll;

public class BFSMazeSolverTest extends BasicSolverTest{
    @BeforeAll
    public static void initSolver() {
        solver = new BFSMazeSolver();
    }
}
