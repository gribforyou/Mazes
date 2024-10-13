package backend.academy;

public record Edge(int x1, int y1, int x2, int y2) {
    @Override
    public boolean equals(Object o) {
        if(o instanceof Edge) {
            Edge e = (Edge) o;
            if((x1 == e.x1 && y1 == e.y1 && x2 == e.x2 && y2 == e.y2) ||
                (x1 == e.x2 && y1 == e.y2 && x2 == e.x1 && y2 == e.y2)) return true;
        }
        return false;
    }
}
