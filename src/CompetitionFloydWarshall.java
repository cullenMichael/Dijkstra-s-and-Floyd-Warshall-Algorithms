import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestantsâ€™
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *    ï‚· Each contestant walks at a given estimated speed.
 *    ï‚· The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {
	private int[] players = new int[3];
	private ArrayList<Double> inner;
	private int intersections = 0;
	private ArrayList<Double>[] outer;
	private double[][] r;
	private double[][] finish;
	private int count[];
	private double max = 0;
	int routes = 0;
	private boolean wrong = false;
	

	/**
	 * @param filename:
	 *            A filename containing the details of the city road network
	 * @param sA,
	 *            sB, sC: speeds for 3 contestants
	 */
	CompetitionFloydWarshall(String filename, int sA, int sB, int sC) {
		
		
		 if (filename == null) {
			wrong = true;
		} 
		else if (filename.length() == 0) {
			wrong = true;
		} 
		else {
			players[0] = sA;
			players[1] = sB;
			players[2] = sC;
			getSmallest();
			if(players[0] < 1) {
				wrong = true;
			}
			
				getInfo(filename);
			
		}
	}

	private void getSmallest() {
		int t = 0;
		for (int k = 0; k < players.length; k++) {
			for (int i = k; i > 0; i--) {
				if (players[i] < players[i - 1]) {
					t = players[i];
					players[i] = players[i - 1];
					players[i - 1] = t;
				}
			}
		}
	}

	private void getInfo(String file2) {

		int origion = 0;
		int dest = 0;
		double time = 0;
		File file = new File(file2);
		Scanner fileInput = null;
		try {
			fileInput = new Scanner(file);
		} catch (FileNotFoundException e) {
			wrong = true;
		}
		if(wrong == false) {
		intersections = fileInput.nextInt();
		routes = fileInput.nextInt();
		if (intersections < 1) {
			wrong = true;
		}
		if(routes < 2) {
			wrong = true;
		}
		r = new double[intersections][intersections];
		finish = new double[4][intersections];
		count = new int[intersections];
		initGraph();
		int cont = 0;
		double ori = 0;
		double des = 0;
		while (fileInput.hasNextDouble()) {
			ori = fileInput.nextDouble();
			des = fileInput.nextDouble();
			origion = (int) ori;
			dest = (int) des;
			time = fileInput.nextDouble();
			if((time < 0)) {
				wrong = true;
				break;
			}
			r[origion][dest] = time;
			cont++;
		}
		if((cont != routes)) {
			wrong = true;
		}
		}
	}

	private void initGraph() {
		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < r.length; j++) {
				if (i == j) {
					r[i][j] = 0;
				} else {
					r[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
	}

	/**
	 * @return int: minimum minutes that will pass before the three contestants can
	 *         meet
	 */
	public int timeRequiredforCompetition() {
		
		if((wrong == true)) {
			return -1;
		}
		double val = 0;
		for (int k = 0; k < intersections; k++) {
			for (int i = 0; i < intersections; i++) {
				for (int j = 0; j < intersections; j++) {
					if (r[i][j] > r[i][k] + r[k][j]) {
						r[i][j] = r[i][k] + r[k][j];		
						count[i] = count[i] + 1;
					}
				}
			}
		}

		for (int i = 0; i < intersections; i++) {
			for (int j = 0; j < intersections; j++) {
				val = r[j][i];
				if (val != Double.POSITIVE_INFINITY) {
					if((val > max)) {
						max = val;
						System.out.println("count " + max);
					}
				}
				else {
					return -1;
				}
			}
		}
		System.out.println("CFW MAX: " + max);
		int finalval = (int) Math.ceil((max*1000) / players[0]);
		System.out.println(Math.ceil((max*1000) / players[0]));
		
		return finalval;
	}
}