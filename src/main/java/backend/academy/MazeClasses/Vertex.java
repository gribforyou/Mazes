package backend.academy.MazeClasses;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;

@Getter
public class Vertex {
    private final int x;
    private final int y;

    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Set<Vertex> getNeighbors() {
        Set<Vertex> neighbors = new HashSet<>();
        neighbors.add(new Vertex(x - 1, y));
        neighbors.add(new Vertex(x + 1, y));
        neighbors.add(new Vertex(x, y - 1));
        neighbors.add(new Vertex(x, y + 1));
        return neighbors;
    }

    @Override
    public int hashCode() {
        return x * 1000 + y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vertex v) {
            return x == v.x && y == v.y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Vertex [x=" + x + ", y=" + y + "]";
    }
}
