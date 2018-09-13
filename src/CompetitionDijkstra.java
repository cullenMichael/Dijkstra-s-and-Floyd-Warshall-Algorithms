
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CompetitionDijkstra {

	private int[] players = new int[3];
	private int intersections = 0;
	private int routes = 0;
	double max = 0;
	private ArrayList<Double>[] outer;
	private double[] route;
	private double[] from;
	private boolean[] hit;
	private int count[];
	private ArrayList<Double> inner;
	boolean wrong = false;
	boolean [] pick;
	

	CompetitionDijkstra(String filename, int sA, int sB, int sC) {
		
		
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
			inner = new ArrayList<>();
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

	private void getInfo(String file2)  {
		int origion = 0;
		int dest = 0;
		double time = 0;
		File file = new File(file2);
		
		FileReader f = null;
		try {
			f = new FileReader(file);
		} catch (FileNotFoundException e) {
			wrong = true;	
		}
		if(wrong == false) {
		final Scanner fileInput = new Scanner(f);
		intersections = fileInput.nextInt();
		if (intersections < 1) {
			wrong = true;
		}
		route = new double[intersections];
		from = new double[intersections];
		hit = new boolean[intersections];
		pick = new boolean[intersections];
		count = new int[intersections];
		routes = fileInput.nextInt();
		if(routes < 2) {
			wrong = true;
		}
		createGrid();
		ArrayList<Double> temp;
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
			temp = outer[origion];
			temp.add((double) dest);
			temp.add(time);	
			cont++;
		}
		
		if((cont != routes)) {
			wrong = true;
		}
		}
	}

	@SuppressWarnings("unchecked")
	private void createGrid() {
		
		outer = new ArrayList[intersections];
		for (int i = 0; i < intersections; i++) {
			outer[i] = new ArrayList<>();
		}
	}

	public int timeRequiredforCompetition() {
		
		if((wrong == true)) {
			return -1;
		}
		
		for (int i = 0; i < intersections; i++) {
			calc(i);
			route = new double[intersections];
			from = new double[intersections];
			hit = new boolean[routes];
		}
		for(int i = 0; i < intersections; i++) {
			if(pick[i] == false) {
				return -1;
			}
		}
		System.out.println("MAX IS :" + max);
		System.out.println(Math.ceil(max * 1000 / players[0]));
		int finalval = (int) Math.ceil(max * 1000 / players[0]);
		System.out.println("last" + finalval);
		
		return finalval;
	}

	private static int minVertex(double[] dist, boolean[] v) {
		double x = Double.MAX_VALUE;
		int y = -1;
		double temp = Double.MAX_VALUE;
		for (int i = 0; i < dist.length; i++) {
			temp = dist[i];
			if (!v[i] && temp < x) {
				y = i;
				x = temp;
			}
		}
		return y;
	}

	private void calc(int travel) {
		double dest = 0;
		double time = 0;
		int value = 0;

		for (int i = 0; i < intersections; i++) {
			route[i] = Double.MAX_VALUE;
		}
		route[travel] = 0;
		for (int i = 0; i < intersections; i++) {
			value = minVertex(route, hit);
			if (value > -1)  {
				hit[value] = true;
				inner = outer[value];
				for (int j = 0; j < inner.size() - 1; j++) {
					dest = inner.get(j);
					time = inner.get(j = j + 1);
					double val = time + route[value];
					if (val < route[(int) dest])  {
						count[(int)dest] = count[(int)dest] + 1;
						route[(int) dest] = val;
						from[(int) dest] = value;
					}
				}
			}
			else {
				break;
			}
		}
		
		for (int i = 0; i < intersections; i++) {
			
			if((route[i] != Double.MAX_VALUE)&&(route[i] != 0)) {
				pick[i] = true;
				if((route[i] > max)&&(count[i] >= 2)) {
					max = route[i];	
					System.out.println("here");
					System.out.println("max: " + max + " count: " + count[i] + " @ " + i);
				}
			}
		}
		System.out.println(Arrays.toString(route));
	}
}
