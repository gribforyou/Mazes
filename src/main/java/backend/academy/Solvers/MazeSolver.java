package backend.academy.Solvers;

import backend.academy.MazeClasses.Edge;
import backend.academy.MazeClasses.Maze;
import java.util.Set;

public interface MazeSolver {
    Set<Edge> solve(Maze maze);
}
