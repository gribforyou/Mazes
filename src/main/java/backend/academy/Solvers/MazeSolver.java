package backend.academy.Solvers;

import backend.academy.MazeClasses.Edge;
import backend.academy.MazeClasses.Maze;
import java.util.Set;

public abstract class MazeSolver {
    public abstract Set<Edge> solve(Maze maze);

    protected void validateMaze(Maze maze) {
        if (maze == null || maze.edges().isEmpty() || maze.end() == null || maze.start() == null) {
            throw new IllegalArgumentException("Maze params are not correct!");
        }
    }
}
