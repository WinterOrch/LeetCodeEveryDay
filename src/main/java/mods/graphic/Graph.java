package mods.graphic;

class Graph {
    int vertex;
    Edge[] edges;
}


class Edge {
    int begin;
    int end;

    int weight;

    Edge (int begin, int end, int weight) {
        this.begin = begin;
        this.end = end;
        this.weight = weight;
    }
}
