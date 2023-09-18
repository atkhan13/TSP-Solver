/* The following method is used to generate a city
 * It has an x and y coordinate which can be used to 
 * determine the distance between two cities.
 */
public class City {
	
	
	private double x;
	private double y;
	private String cityName;
	
	public City()
	{
		this.x =  Math.random();
		this.y = Math.random();
	}
	
	public static double distanceBetween (City city1, City city2)
	{
		double xcoord = Math.abs(city1.x-city2.x);
		double ycoord = Math.abs(city1.y-city2.y);
		double xsquared = xcoord * xcoord;
		double ysquared = ycoord * ycoord;
		return Math.sqrt(xsquared + ysquared);
	}
	
	public void setName(String name)
	{
		this.cityName = name;
	}
	
	
	public static double getDistance(City c1, City c2)
	{
		return distanceBetween(c1, c2);
	}
	
	public double getX()
	{
		return this.x;
	}
	public double getY()
	{
		return this.y;
	}
	public String getName()
	{
		return this.cityName;
	}
}
