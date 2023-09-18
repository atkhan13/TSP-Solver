import java.util.ArrayList;
/* Generates a random TSP problem by creating an ArrayList of randomly generates 7
 * cities from the City class. 
 */
public class TSP {
	
	private ArrayList<City> cities = new ArrayList<City>();
	
	public TSP()
	{
		for (int i = 0; i < 7; i++)
		{
			cities.add(new City());
			String cityNumber = String.valueOf(i+1);
			cities.get(i).setName(cityNumber);
		}
		this.cities = cities;
	}
	
	public ArrayList<City> getCities()
	{
		return cities;
	}
	

}
