package backend.academy.MazeClasses;

import lombok.Getter;

@Getter
public class Edge {
    private final Vertex v1;
    private final Vertex v2;

    public Edge(Vertex v1, Vertex v2) {
        if (v1 == null || v2 == null) {
            throw new NullPointerException("Null vertex!");
        }
        if (Math.abs(v1.x() - v2.x()) + Math.abs(v1.y() - v2.y()) != 1) {
            throw new IllegalArgumentException("Invalid edge coordinates");
        }
        if (v1.x() + v2.y() > v2.x() + v2.y()) {
            this.v1 = v2;
            this.v2 = v1;
        } else {
            this.v1 = v1;
            this.v2 = v2;
        }
    }

    public boolean incidence(Vertex v) {
        return v1.equals(v) || v2.equals(v);
    }

    @Override
    public int hashCode() {
        return v1.hashCode() + v2.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Edge e) {
            return (v1.equals(e.v1) && v2.equals(e.v2) || v2.equals(e.v1) && v1.equals(e.v2));
        }
        return false;
    }

    @Override
    public String toString() {
        return "[(" + v1.x() + ", " + v1.y() + ")" + " -> (" + v2.x() + ", " + v2.y() + ")]";
    }
}
