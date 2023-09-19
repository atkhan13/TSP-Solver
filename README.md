# TSP-Solver

The following repository contains scripts that solver the Travelling Salesman Problem (TSP) using two methode: (1) brute force, and (2) hill climbing.

## The Travelling Salesman Problem

The travelling salesman problem is a very widely known computational problem in which, given a set of cities, the problem asks to find the shortest route that visits every city exactly once starting and ending at the same point. We can interpret this problem as a complete graph in which every node represents a city and the edges between two nodes represents the distance between the two cities. This problem is a famous NP-hard problem and cannot be solved in polynomial time. Here, we generate a random TSP instance in the TSP class. The program randomly generates 7 cities on a 2D plane with x and y coordinates between 0 and 1. We then solve for this problem in the TSPSolver class which uses two methods.

## Brute Force Algorithm

In the brute force method, a straightforward approach is implemented in which we iterate through every permutation of the cities and calculate the total distance. The permutation with the shortest distance is returned as the solution. Although this algorithm ensures that the optimal solution is found, its exponential time complexity means that as we reach large instances of the TSP it becomes more and more impractical to use. 

## Hill Climbin Algorithm

We can compromise finding the optimal solution - which is time consuming and computationally extensive - by instead finding the best possible solution using optimization techniques. One such widely used technique is the hill climbing algorithm. 
