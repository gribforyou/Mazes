package backend.academy.MazeClasses;

import backend.academy.Generators.EntityGenerators.EntityGenerator;
import backend.academy.Generators.MazeGenerators.MazeGenerator;
import backend.academy.Renderers.MazeRenderer;
import backend.academy.Solvers.MazeSolver;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

@Getter
public class Maze {
    private final int height;
    private final int width;
    private Entity[][] matrix;
    private Set<Edge> edges;
    private Set<Edge> solution;
    private Vertex start;
    private Vertex end;
    private boolean isSolved;

    public Maze(int width, int height) {
        if (height < 2 || width < 2) {
            throw new IllegalArgumentException("Not correct sizes or coordinates");
        }
        this.height = height;
        this.width = width;
        solution = null;
        edges = new HashSet<>();
        matrix = new Entity[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrix[i][j] = Entity.Default;
            }
        }
        isSolved = false;
    }

    public void setStart(Vertex v) {
        if (v.x() < 0 || v.y() < 0 || v.x() >= width || v.y() >= height) {
            throw new IllegalArgumentException("Not correct coordinates of start ceil!");
        }
        if (start != null) {
            matrix[start.y()][start.x()] = Entity.Default;
        }
        matrix[v.y()][v.x()] = Entity.Entrance;
        this.start = v;
        isSolved = false;
    }

    public void setEnd(Vertex v) {
        if (v.x() < 0 || v.y() < 0 || v.x() >= width || v.y() >= height) {
            throw new IllegalArgumentException("Not correct coordinates of end ceil!");
        }
        if (end != null) {
            matrix[end.y()][end.x()] = Entity.Default;
        }
        matrix[v.y()][v.x()] = Entity.Exit;
        this.end = v;
        isSolved = false;
    }

    public void generateEdges(MazeGenerator generator) {
        edges = generator.generate(width, height);
    }

    private void generateEntities(EntityGenerator generator) {
        matrix = generator.generate(height, width);
    }

    public void solve(MazeSolver solver) {
        solution = solver.solve(this);
        isSolved = true;
    }

    public void showMaze(MazeRenderer renderer) {
        renderer.render(this, false);
    }

    public void showSolution(MazeRenderer renderer) {
        if (!isSolved) {
            throw new IllegalStateException("Maze has not been solved with these start and end ceils");
        }
        renderer.render(this, true);
    }
}
