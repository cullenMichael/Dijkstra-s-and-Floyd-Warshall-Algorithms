import static org.junit.Assert.*;




/*
 * Q1.  Dijkstra algorithm: Array of array lists. This setup allows for varying lenghts of routes.The index of the array 
 * 		Correspond to the origin node of the route. in its array list, the destination node and the time are stored. 
 * 		This allows for varying lengths of routes out of each node. It also makes it easier to get the values for that node.
 * 
 * 		Floyd : 2D array, this makes the cycle through the elements of the routes easier and able to update the minimum route
 * 	    to that node if its less then the route time already stored in the 2d array. The drawback is there will be many unused
 * 		spaces in the array which makes it memory complexity bad.
 * 
 * Q2.  Dijkstra algorithm: worst case - O( |E|+|V|log|V|) . This algorithm needs fixed values - ie values that do not change. 
 * 		It computes the shortest routes to each node from a specific node on the graph. This needs to be done for all
 * 		the node in the graph. when picking a route, an array list is used to find the minimum timed route of all 
 * 		the nodes connected to the route made by the algorithm. By doing this, it calculates the minimum times to each node 
 * 		from the start node. It can only deal with positively weighted edges. Dijkstras is suitable for just calculating
 * 		 a specific route in the graph from a specific node. This would be useful for routing packets over the Internet 
 * 		(with constant speeds).
 * 
 * 		Floyd: worst case O(N^3) Can use positively and negatively weighted edges (but with no negative cycles).
 * 		This algorithm does not construct a route, but rather by computing the shortest distances between every pair of vertices
 * 		in the graph. ie it gives the fastest routes to every vertex rather then Dijkstras algorithm that on calculates a specific
 * 		route in the graph. Floyd is useful for calculating the overall fastest routes of the graph if the routes are not needed.
 * 		This will be useful for networking too where the times vary over time and for finding the fastest route in the overall system.  
 * 
 */

import org.junit.Test;

public class CompetitionTests {
	
	//works
//	@Test
//	public void insert1() {
//		
//		CompetitionDijkstra d = new CompetitionDijkstra("input-B.txt",60,80,50);
//		assertEquals(10000, d.timeRequiredforCompetition());
//		CompetitionFloydWarshall f = new CompetitionFloydWarshall("input-B.txt",60,80,50);
//		assertEquals(10000, f.timeRequiredforCompetition());//
//	}
	
	@Test
	public void insertLarge() {
		CompetitionDijkstra d = new CompetitionDijkstra("input-G.txt",432,7,67281);
		assertEquals(-1, d.timeRequiredforCompetition());//
		CompetitionFloydWarshall f = new CompetitionFloydWarshall("input-G.txt",432,7,67281);
		assertEquals(-1, f.timeRequiredforCompetition());
	}
	
	
	//works
	@Test
	public void TestnullFile() {
		
		CompetitionDijkstra d = new CompetitionDijkstra("aaaaa",3233,7,2368726);
		assertEquals(-1, d.timeRequiredforCompetition());
		CompetitionFloydWarshall f = new CompetitionFloydWarshall("aaaaa",3233,7,2368726);
		assertEquals(-1, f.timeRequiredforCompetition());	
	}
	@Test	
	public void Testi(){
		
		CompetitionDijkstra d = new CompetitionDijkstra("input-I.txt",3233,7,2368726);
		assertEquals(1715, d.timeRequiredforCompetition());
		CompetitionFloydWarshall f = new CompetitionFloydWarshall("input-I.txt",3233,7,2368726);
		assertEquals(1715, f.timeRequiredforCompetition());	
	}
	
	@Test
	public void TeskK() {
		CompetitionDijkstra d = new CompetitionDijkstra("input-K.txt",51,7,2266262);
		assertEquals(2286, d.timeRequiredforCompetition());
		CompetitionFloydWarshall f = new CompetitionFloydWarshall("input-K.txt",51,7,2266262);
		assertEquals(2286, f.timeRequiredforCompetition());			
	}
	
	@Test
	public void insertFalse() {
		CompetitionDijkstra d = new CompetitionDijkstra("input-C.txt",100,100,100);
		assertEquals(-1, d.timeRequiredforCompetition());
		CompetitionFloydWarshall f = new CompetitionFloydWarshall("input-C.txt",100,100,100);
		assertEquals(-1, f.timeRequiredforCompetition());
	}
	
	
//	@Test
//	public void insert2() {
//		CompetitionDijkstra d = new CompetitionDijkstra("C:\\Users\\Michael\\Documents\\route1.txt",100,100,100);
//		assertEquals(14, d.timeRequiredforCompetition());
//		CompetitionFloydWarshall f = new CompetitionFloydWarshall("C:\\Users\\Michael\\Documents\\route1.txt",100,100,100);
//		assertEquals(14, f.timeRequiredforCompetition());
//	}
	
	@Test
	public void empty() {
		CompetitionDijkstra d = new CompetitionDijkstra("",100,100,100);
		assertEquals(-1, d.timeRequiredforCompetition());
		CompetitionFloydWarshall f = new CompetitionFloydWarshall("",100,100,100);
		assertEquals(-1, f.timeRequiredforCompetition());
	}
	@Test
	public void testnull() {
		CompetitionDijkstra d = new CompetitionDijkstra(null,100,100,100);
		assertEquals(-1, d.timeRequiredforCompetition());
		CompetitionFloydWarshall f = new CompetitionFloydWarshall(null,100,100,100);
		assertEquals(-1, f.timeRequiredforCompetition());	
	}
	
	@Test
	public void testnegitive() {
		CompetitionDijkstra d = new CompetitionDijkstra("input-K.txt",-100,-100,-100);
		assertEquals(-1, d.timeRequiredforCompetition());
		CompetitionFloydWarshall f = new CompetitionFloydWarshall("input-K.txt",-100,-100,-100);
		assertEquals(-1, f.timeRequiredforCompetition());	
	}	
	@Test
	public void testwrong() {
		CompetitionDijkstra d = new CompetitionDijkstra("wrong.txt",100,100,100);
		assertEquals(-1, d.timeRequiredforCompetition());
		CompetitionFloydWarshall f = new CompetitionFloydWarshall("wrong.txt",100,100,100);
		assertEquals(-1, f.timeRequiredforCompetition());	
	}	
	@Test
	public void testless() {
		CompetitionDijkstra d = new CompetitionDijkstra("less.txt",100,100,100);
		assertEquals(-1, d.timeRequiredforCompetition());
		CompetitionFloydWarshall f = new CompetitionFloydWarshall("less.txt",100,100,100);
		assertEquals(-1, f.timeRequiredforCompetition());	
	}	

}
