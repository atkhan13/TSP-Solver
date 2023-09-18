import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/* The following class corresponds to the solutions. Running the algorithm returns the 
 * mean max min and standard deviation for brute solver, a random tour and for the hill climbing
 * algorithm. The ArrayLists in the main method stores the distances as well as best tours when 
 * using the three different methods.
 */
public class TSPSolver {
	
	public static void main(String[] args)
	{
		ArrayList<ArrayList<City>> bruteResults = new ArrayList<>();
		ArrayList<ArrayList<City>> baselineResult = new ArrayList<>();
		ArrayList<Double> distancesBrute = new ArrayList<Double>();
		ArrayList<Double> distanceBaseline = new ArrayList<Double>();
		ArrayList<Double> distanceHill = new ArrayList<Double>();
		ArrayList<ArrayList<City>> hillResults = new ArrayList<>();
		for(int i = 0; i < 100; i++)
		{
			TSP myTSP = new TSP();
			bruteResults.add(bruteSolver(myTSP));
			baselineResult.add(randomTourGenerator(myTSP.getCities()));
			hillResults.add(hillClimbing(baselineResult.get(i)));
			distancesBrute.add(totalDistance(bruteResults.get(i)));
			distanceBaseline.add(totalDistance(baselineResult.get(i)));
			distanceHill.add(totalDistance(hillResults.get(i)));
		}
		System.out.println("The mean for the optimal lenth of the 100 TSP instances using brute solver is: " + mean(distancesBrute) );
		System.out.println("The min optimal lenth of the 100 TSP instances using brute solver is: " + min(distancesBrute) );
		System.out.println("The max optimal lenth of the 100 TSP instances using brute solver is: " + max(distancesBrute) );
		System.out.println("The standard deviation of the optimal lenths of the 100 TSP instances using brute solver is: " + standev(distancesBrute) );
		System.out.println();
		System.out.println("The mean for the optimal lenth of the 100 random TSP tours is: " + mean(distanceBaseline));
		System.out.println("The min optimal lenth of the 100 random TSP tours is: " + min(distanceBaseline));
		System.out.println("The max optimal lenth of the 100 random TSP tours is: " + max(distanceBaseline));
		System.out.println("The standard deviation of the optimal lenth of the 100 random TSP tours is: " + standev(distanceBaseline));
		System.out.println();
		System.out.println("The mean for the optimal lenth of the 100 instances using hill climbing is: " + mean(distanceHill));
		System.out.println("The min optimal lenth of the 100 instances using hill climbing is: " + min(distanceHill));
		System.out.println("The max optimal lenth of the 100 instances using hill climbing is: " + max(distanceHill));
		System.out.println("The standard deviation of the optimal lenths of the 100 instances using hill climbing is: " + standev(distanceHill));
		System.out.println();
		int sameBrute = numSame(bruteResults, baselineResult);
		
		System.out.println("Number of same results between brute solver and randomized tour generator is: " + sameBrute);
		
		int sameHill = numSame(bruteResults, hillResults);
		
		System.out.println("Number of same results between brtue solver and hill climbing algorithm is: " + sameHill);
		

	
	}
	
	/* The following method takes an instance of TSP and re orders the cities to generate a tour
	 * for the given TSP problem that is completely random.
	 */
	
	public static ArrayList<City> randomTourGenerator(ArrayList<City> cities)
	{
		City start = cities.get(0);
		ArrayList<City> alter = (ArrayList<City>) cities.clone();
		ArrayList<City> tour = new ArrayList<City>();
		for(int i = cities.size()-1; i > -1; i--)
		{
			int randomNum = ThreadLocalRandom.current().nextInt(0, i + 1);
			City nextCity = alter.get(randomNum);
			alter.remove(randomNum);
			tour.add(nextCity);
		}
		return tour;
	}
	/* The following method finds the optimal solution to an instance of the TSP problem
	 * using brute force. The generatePerm method is a helper to generate all the permutations
	 * of a given tour.
	 */
	public static ArrayList<City> bruteSolver(TSP problem)
	{
		ArrayList<City> toPermute = (ArrayList<City>) problem.getCities().clone();
		ArrayList<ArrayList<City>> tours = generatePerm(toPermute);
		ArrayList<City> bestTour = new ArrayList<City>();
		double minDist = 10.0;
		for(int i = 0; i < tours.size(); i++)
		{
			double dist = 0.0;
			dist = totalDistance(tours.get(i));
			if(dist < minDist)
			{
				minDist = dist;
				bestTour = tours.get(i);
			}
		}
		return bestTour;
		
	}
	
	public static ArrayList<ArrayList<City>> generatePerm(ArrayList<City> original)
	{
		if(original.isEmpty())
		{
			ArrayList<ArrayList<City>> result = new ArrayList<>();
			result.add(new ArrayList<>());
			return result;
			
		}
		City firstCity = original.remove(0);
		ArrayList<ArrayList<City>> myPermutations = new ArrayList<>();
		ArrayList<ArrayList<City>> newPermutation = generatePerm(original);
		for(ArrayList<City> cityArray: newPermutation)
		{
			for(int i = 0; i <= cityArray.size(); i++)
			{
				ArrayList<City> temp = new ArrayList<>(cityArray);
				temp.add(i, firstCity);
				myPermutations.add(temp);
			}
		}
		return myPermutations;
		
	}
	
	/* The following method uses Hill Climbing to find a solution to a TSP instance when
	 * given a random starting tour. The method swap below is a helper method used to 
	 * execute the 2-change neighbourhood function
	 */
	
	public static ArrayList<City> hillClimbing(ArrayList<City> randomTour)
	{
		ArrayList<City> initialTour = (ArrayList<City>) randomTour.clone();
		boolean satisfied = false;
		ArrayList<City> bestTour = initialTour;
		while(!satisfied)
		{
			double distanceValueOG = totalDistance(bestTour);
			double min = 110.0;
			ArrayList<City> bestSuccessor = new ArrayList<City>();
			for(int i = 0; i < bestTour.size()-1; i++)
			{
				for(int k = i + 1; k < bestTour.size(); k++)
				{
					ArrayList<City> swap = (ArrayList<City>) bestTour.clone();
					swap = swap(swap, i, k);
					if(totalDistance(swap) < min)
					{
						min = totalDistance(swap);
						bestSuccessor = swap;
					}
				}
			}
			if(min < distanceValueOG)
			{
				bestTour = bestSuccessor;
			}
			else
			{
				satisfied = true;
			}
			
		}
		return bestTour; 
		
	}
	
	
	public static ArrayList<City> swap(ArrayList<City> tour, int first, int second)
	{
		ArrayList<City> newTour = new ArrayList<City>();
		for(int i = 0; i < first; i++)
		{
			newTour.add(tour.get(i));
		}
		for(int i = second; i >= first; i--)
		{
			newTour.add(tour.get(i));
		}
		for(int i = second+1; i < tour.size(); i++)
		{
			newTour.add(tour.get(i));
		}
		return newTour;
	}
	
	/* Calculates the total distance from a given tour */
	
	public static double totalDistance(ArrayList<City> tour)
	{
		double total = 0;
		for(int i = 0; i < tour.size()-1; i++)
		{
			total += City.distanceBetween(tour.get(i), tour.get(i+1));
		}
		total+= City.distanceBetween(tour.get(0), tour.get(tour.size()-1));
		return total;
	}
	
	/* Helper methods to determine the mean, min, max and standard deviation */
	
	public static double mean(ArrayList<Double> distances)
	{
		double sum = 0;
		for(int i = 0; i < distances.size(); i++)
		{
			sum += distances.get(i);
		}
		sum = sum/distances.size();
		return sum;
	}
	
	public static double max(ArrayList<Double> distances)
	{
		double max = 0.0;
		for(int i = 0; i < distances.size(); i++)
		{
			if(distances.get(i) > max)
			{
				max = distances.get(i);
			}
		}
		return max;
	}
	public static double min(ArrayList<Double> distances)
	{
		double min = 10.0;
		for(int i = 0; i < distances.size(); i++)
		{
			if(distances.get(i) < min)
			{
				min = distances.get(i);
			}
		}
		return min;
	}
	public static double standev(ArrayList<Double> distances)
	{
		double mean = mean(distances);
		ArrayList<Double> newMean = new ArrayList<Double>();
		for(int i = 0; i < distances.size(); i++)
		{
			double calculate = distances.get(i);
			calculate = calculate - mean;
			calculate = Math.pow(calculate, 2.0);
			newMean.add(calculate);
		}
		double sum = mean(newMean);
		return Math.sqrt(sum);	
	}
	
	public static int numSame(ArrayList<ArrayList<City>> first, ArrayList<ArrayList<City>> second)
	{
		int num = 0;
		for(int i = 0; i < first.size(); i++)
		{
			boolean same = true;
			for(int j = 0; j < first.get(i).size(); j++)
			{
				if((first.get(i).get(j).getX() != second.get(i).get(j).getX()) && (first.get(i).get(j).getY() != second.get(i).get(j).getY()) && !(first.get(i).get(j).getName().equals(second.get(i).get(j).getName())))
				{
					same = false;
				}
			}
			if(same)
			{
				num++;
			}
		}
		return num;
	}

}
