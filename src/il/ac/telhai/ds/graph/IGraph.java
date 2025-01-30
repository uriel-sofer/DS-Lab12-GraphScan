package il.ac.telhai.ds.graph;

public interface IGraph<V extends Comparable<V>, E> {

	/**
	 * Add a new vertex if none exists.
	 */
	public void add(V v);

	/**
	 * @param u
	 * @param v
	 * @return the label of the edge (u,v)
	 */
	public E getEdge(V u, V v);

	/**
	 * Add a new edge if none exists between the two vertices Otherwise retain the
	 * existing edge and replace the item with the given one. If the vertices u or v
	 * do not exist, add them to the graph.
	 */
	public E putEdge(V u, V v, E edgeLabel);

	/**
	 * @return If the graph contains the vertex v.
	 */
	public boolean containsVertex(V v);

	/**
	 * Remove the vertex and its edges from the graph.
	 */
	public void removeVertex(V v);

	/**
	 * @return The label of the edge between the two vertices. Null if the edge does
	 *         not exist Throws an exception if one of the vertices does not exist.
	 */
	public E removeEdge(V u, V v);

	/**
	 * @return If the edge (u,v) exists, return the weight of edge (u,v). Otherwise,
	 *         return 0. Note that if the labels are not of type Number or Weighted,
	 *         a RuntimeException will be thrown.
	 */
	public double getWeight(V u, V v);

	/**
	 * @return The concatenation of the vertices separated by commas.
	 */
	public String toString();

	/**
	 * @returns The concatenation of the vertices separated by newlines Every vertex
	 *          is printed with a comma separated list of its incident edges. The
	 *          list is separated from the vertex with a colon. Each edge is printed
	 *          as a pair of its edged inside {} brackets, followed by its weight.
	 *          For example:
	 *          "A:{A,B}(2.5)\nB:{A,B}(2.5)\nC:{C,D}(4.5)\nD:{C,D}(4.5)".
	 */
	public String toStringExtended();

	/**
	 * @return If the the edge (u,v) exists.
	 */
	public boolean areAdjacent(V u, V v);

}