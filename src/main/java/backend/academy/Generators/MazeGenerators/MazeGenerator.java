package backend.academy.Generators.MazeGenerators;

import backend.academy.MazeClasses.Edge;
import java.util.Set;

public interface MazeGenerator {
    Set<Edge> generate(int height, int width);
}
