package backend.academy;

import lombok.Getter;

@Getter
public class Vertex {
    private final int x;
    private final int y;

    public Vertex(int x, int y) {
        this.x = x;
        this.y = y;
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
}
