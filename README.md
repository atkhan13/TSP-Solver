# TSP-Solver

The following repository contains scripts that solver the Travelling Salesman Problem (TSP) using two methode: (1) brute force, and (2) hill climbing.

## The Travelling Salesman Problem

The travelling salesman problem is a very widely known computational problem in which, given a set of cities, the problem asks to find the shortest route that visits every city exactly once starting and ending at the same point. We can interpret this problem as a complete graph in which every node represents a city and the edges between two nodes represents the distance between the two cities. This problem is a famous NP-hard problem and cannot be solved in polynomial time. Here, we generate a random TSP instance in the TSP class. The program randomly generates 7 cities on a 2D plane with x and y coordinates between 0 and 1. We then solve for this problem in the TSPSolver class which uses two methods.

## Brute Force Algorithm

In the brute force method, a straightforward approach is implemented in which we iterate through every permutation of the cities and calculate the total distance. The permutation with the shortest distance is returned as the solution. Although this algorithm ensures that the optimal solution is found, its exponential time complexity means that, as we reach large instances of the TSP, it becomes more and more impractical to use. 

## Hill Climbing Algorithm

We can compromise finding the optimal solution - which is time consuming and computationally extensive - by instead finding the best possible solution using optimization techniques. One such widely used technique is the hill climbing algorithm. In this algorithm we start with a random route and make small changes to the route during each iteration. To do this we use 2-opt algorithm. In this algorithm, we go through every pair of cities and use the following algorithm to define the new route:

Let v1 and v2 be the indices of the cities in the original route you want to swap.

Let the original route be p

The procedure is as follows:

1. Take p[0] to p[v1] and add them in the same order to the new route
2. Take p[v1 + 1] to p[v2] and add them in reverse order to the new route
3. Take p[v2 + 1] to p[start] and add them in the same order to the new route

For each successor of the original route using this 2-opt algorithm, we store which new route gave us the shortest distance. If this distance is smaller than the distance of the original route, then we use the best successor route as our new original route and repeat the algoirthm. If the distances is larger than the original distance then we keep the original distance as our final solution.

Hill climbing is an efficient algorithm for finding local optima. A drawback of this approach is that it may get stuck in these local optima meaning that it does not find the global optimum solution. Furthermore, a poor choice of initial solution could greatly hinder the time of the algorithm as well as the solution found. In fact, running the TSPSolver file will compare the brute force solution to the hill climbing solution for 100 instances of the TSP problem. Because each instance is randomized, the results will vary for each run of the program. Overall, however, you should find that there are a low number of cases where the hill climbing algorithm finds the same solution as the brute force algorithm, which reinforces that the hill climbing algorithm does not necessarily find the optimal solution.

