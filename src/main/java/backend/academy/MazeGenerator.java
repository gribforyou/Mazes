package backend.academy;

import java.util.Set;

public interface MazeGenerator {
    Set<Edge> generate(int height, int width);
}
