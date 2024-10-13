package backend.academy;

import java.util.List;

public interface MazeRenderer {
    void render(Maze maze);

    void renderSolution(Maze maze, List<Edge> solution);
}
