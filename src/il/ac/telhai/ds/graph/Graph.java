package il.ac.telhai.ds.graph;

import java.util.*;

public class Graph<V extends Comparable<V>, E> implements IGraph<V, E>{

    private final Map<V, List<Edge>> vertexToIncidences;

    public Graph() {
        vertexToIncidences = new TreeMap<>();
    }

    /**
     * Edge is a connection between twe vertices: <i>v (self)</i> and <i>u (neighbor)</i>
     * It can hold a weight if <i>label</i> is type of Weighted or a type of Number
     */
    private class Edge{
        V v; // Person1, City
        V u; // Person2, City

        E label; // Friendship, Double
        // Label is the value of the friendship

        /**
         * Constructs an Edge object
         * @param v vertex1
         * @param u vertex2
         * @param label connection type, can have height
         *
         */
        public Edge(V v, V u, E label) {
            this.v = v;
            this.u = u;
            this.label = label;
        }

        public V getV() {
            return v;
        }

        @SuppressWarnings("unused")
        public void setV(V v) {
            this.v = v;
        }

        public V getU() {
            return u;
        }

        @SuppressWarnings("unused")
        public void setU(V u) {
            this.u = u;
        }

        public E getLabel() {
            return label;
        }

        public void setLabel(E label) {
            this.label = label;
        }

        /**
         * Checks if the connection can be measured
         * @return true if the label is a number, or implements Weighted, otherwise false
         */
        public boolean isWeighted() {
            return label instanceof Number || label instanceof Weighted;
        }

        /**
         * Returns the edge's weight.
         * @return the weight if the label is a number, or implements Weighted.
         * @throws RuntimeException if the label is not a valid weight.
         */
        public double getWeight() {
            if (label instanceof Number) {
                return ((Number) label).doubleValue(); // Handle numeric labels
            } else if (label instanceof Weighted) {
                return ((Weighted) label).getWeight(); // Handle labels that implement Weighted
            } else {
                throw new RuntimeException("Edge label cannot be evaluated as a weight: " + label);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{" + v + "," + u + "}");
            if (isWeighted()) {
                sb.append("(").append(getWeight()).append(")");
            }
            return sb.toString();
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Edge edge = (Edge) obj;
            return (u.equals(edge.u) && v.equals(edge.v) || u.equals(edge.v) && v.equals(edge.u));
        }

        @Override
        public int hashCode() {
            return u.hashCode() + v.hashCode() + label.hashCode();
        }

    }

    @Override
    public void add(V v) {
        if (!vertexToIncidences.containsKey(v)) {
            vertexToIncidences.put(v, new ArrayList<>());
        }
    }

    @Override
    public E getEdge(V u, V v) {
         try {
             List<Edge> connections = vertexToIncidences.get(u);
             for (Edge edge : connections) {
                 if (edge.getV().equals(u) && edge.getU().equals(v)) {
                     return edge.getLabel();
                 }
             }
         } catch (Exception e) {
             System.err.println(e.getMessage());
         }
         return null;
    }

    @Override
    public E putEdge(V u, V v, E edgeLabel) {
        // No u or v
        if (!vertexToIncidences.containsKey(u) || !vertexToIncidences.containsKey(v)) {
            // Only v
            if (!vertexToIncidences.containsKey(u) && vertexToIncidences.containsKey(v)) {
                add(u);

            }
            // Only u
            else {
                add(v);
            }
        }

        Edge edge = new Edge(u, v, edgeLabel);

        for (Edge e : vertexToIncidences.get(u)) {
            // Change label for existing edge
            if (e.getU().equals(v)) {
                e.setLabel(edgeLabel);
                return e.getLabel();
            }
        }
        // If edge doesn't exist
        vertexToIncidences.get(u).add(edge);
        vertexToIncidences.get(v).add(edge);

        return edge.getLabel();
    }

    @Override
    public boolean containsVertex(V v) {
        return vertexToIncidences.containsKey(v);
    }

    @Override
    public void removeVertex(V v) {
        if (!containsVertex(v)) {
            return;
        }
        // Go over all the vertices, and remove any trace of v
        for (V vertex : vertexToIncidences.keySet()) {
            vertexToIncidences.get(vertex).removeIf(edge -> edge.getV().equals(v) || edge.getU().equals(v));
        }

        vertexToIncidences.remove(v);
    }

    @Override
    public E removeEdge(V u, V v) {
        if (!vertexToIncidences.containsKey(u) || !vertexToIncidences.containsKey(v)) {
            throw new IllegalArgumentException("Can't remove edge, u or v is missing");
        }

        E edgeLabel = getEdge(u, v);

        if (edgeLabel != null) {
            vertexToIncidences.get(u).removeIf(edge ->
                    (edge.getU().equals(u) && edge.getV().equals(v)) || (edge.getU().equals(v) && edge.getV().equals(u))
            );

            vertexToIncidences.get(v).removeIf(edge ->
                    (edge.getU().equals(u) && edge.getV().equals(v)) || (edge.getU().equals(v) && edge.getV().equals(u))
            );
        }

        return edgeLabel;
    }

    @Override
    public double getWeight(V u, V v) {
        double weight = 0;
        for (Edge edge : vertexToIncidences.get(u)) {
            if (edge.getV().equals(v) && edge.getU().equals(u) || edge.getV().equals(u) && edge.getU().equals(v)) {
                weight = edge.getWeight(); // Throws an exception if unsupported
            }
        }
        return weight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (V v : vertexToIncidences.keySet()) {
            sb.append(v).append(",");
        }
        try {
            sb.deleteCharAt(sb.length() - 1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return sb.toString();
    }

    @Override
    public String toStringExtended() {
        // "A:{A,B}(2.5)\nB:{A,B}(2.5)\nC:{C,D}(4.5)\nD:{C,D}(4.5)".
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<V, List<Edge>> entry : vertexToIncidences.entrySet()) {
            V vertex = entry.getKey();
            List<Edge> connections = entry.getValue();

            sb.append(vertex).append(":");
            if (!connections.isEmpty()) {
                for (Edge edge : connections) {
                    sb.append(edge.toString()).append(",");
                }
                sb.deleteCharAt(sb.length() - 1); // Remove the last comma
            }
            sb.append(System.lineSeparator());
        }
        sb.deleteCharAt(sb.length() - 1); // Remove last '\n'
        return sb.toString();
    }

    @Override
    public boolean areAdjacent(V u, V v) {
        try {
            for (Edge edge : vertexToIncidences.get(u)) {
                if (edge.getV().equals(v) && edge.getU().equals(u)) {
                    return true;
                }
                else if (edge.getV().equals(u) && edge.getU().equals(v)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    /**
     * Perform a BFS on the graph.<br>
     * Can miss vertices of not connected.
     * @param source source vertex, to start the search from
     * @return ArrayList of the vertices
     */
    public List<V> bfs(V source) {
        HashSet<V> visited = new HashSet<>();
        visited.add(source);

        Queue<V> queue = new LinkedList<>();
        queue.add(source);

        List<V> result = new ArrayList<>();
        result.add(source);

        while (!queue.isEmpty()) {
            V u = queue.poll();
            for (Edge edge : vertexToIncidences.get(u)) {
                V v = (edge.getU().equals(u)) ? edge.getV() : edge.getU(); // Get the other vertex

                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                    result.add(v);
                }
            }
        }
        return result;
    }

    /**
     * Perform a DFS search on the graph
     * @return ArrayList of all the vertices
     */
    public List<V> dfs() {
        HashSet<V> visited = new HashSet<>();
        List<V> result = new ArrayList<>();

        for (V v : vertexToIncidences.keySet()) {
            if (!visited.contains(v)) {
                dfs_visit(v, visited, result);
            }
        }
        return result;
    }

    private void dfs_visit(V u, HashSet<V> visited, List<V> result) {
        visited.add(u);
        result.add(u);

        for (Edge edge : vertexToIncidences.get(u)) {
            V v = (edge.getU().equals(u)) ? edge.getV() : edge.getU();
            if (!visited.contains(v)) {
                dfs_visit(v, visited, result);
            }
        }
    }
}
