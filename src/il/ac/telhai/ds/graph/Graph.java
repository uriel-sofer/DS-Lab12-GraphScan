package il.ac.telhai.ds.graph;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Graph<V extends Comparable<V>, E> implements IGraph<V, E>{

    /**
     * vertex : list of edges
     * each edge is (v, v1), (v, v2)...
     */
    private Map<V, List<Edge>> vertexToIncidences = new TreeMap<V, List<Edge>>();

    public Graph() {
        vertexToIncidences = new TreeMap<>();
    }

    /**
     * Edge is a connection between twe vertices
     * It can hold a weight if <i>label</i> is type of Weighted or a type of Number
     */
    private class Edge{
        V u; // Person1, City
        V e; // Person2, City

        E label; // Friendship, Double
        // Label is the value of the friendship

        /**
         * Constructs an Edge object
         * @param u vertex1
         * @param e vertex2
         * @param label connection type, can have height
         *
         */
        public Edge(V u, V e, E label) {
            this.u = u;
            this.e = e;
            this.label = label;
        }

        public V getU() {
            return u;
        }

        public void setU(V u) {
            this.u = u;
        }

        public V getE() {
            return e;
        }

        public void setE(V e) {
            this.e = e;
        }

        public E getLabel() {
            return label;
        }

        public void setLabel(E label) {
            this.label = label;
        }

        /**
         * Checks if the connection can be measured
         * @return true if weighted, otherwise false
         */
        public boolean isWeighted() {
            try {
                ((Number) label).doubleValue();
            }
            catch (ClassCastException e) {
                return false;
            }
            return true;
        }

        /**
         *
         * @return the edge's weight
         * @throws UnsupportedOperationException when can't be evaluated
         */
        public double getWeight() {
            if (isWeighted()) {
                return ((Number) label).doubleValue();
            }
            throw new UnsupportedOperationException("Can't evaluate weight");
        }
    }

    @Override
    public void add(V v) {

    }

    @Override
    public E getEdge(V u, V v) {
        return null;
    }

    @Override
    public E putEdge(V u, V v, E edgeLabel) {
        return null;
    }

    @Override
    public boolean containsVertex(V v) {
        return false;
    }

    @Override
    public void removeVertex(V v) {

    }

    @Override
    public E removeEdge(V u, V v) {
        return null;
    }

    @Override
    public double getWeight(V u, V v) {
        return 0;
    }

    @Override
    public String toStringExtended() {
        return "";
    }

    @Override
    public boolean areAdjacent(V u, V v) {
        return false;
    }
}
