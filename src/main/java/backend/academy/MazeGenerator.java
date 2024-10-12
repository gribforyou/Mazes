package backend.academy;

import java.util.List;

public interface MazeGenerator {
    List<Edge> generate(int height, int width);
}
