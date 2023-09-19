# TSP-Solver

The following repository contains scripts that solver the Travelling Salesman Problem (TSP) using two methode: (1) brute force, and (2) hill climbing.

## The Travelling Salesman Problem

The travelling salesman problem is a very widely known computational problem in which, given a set of cities, the problem asks to find the shortest route that visits every city exactly once starting and ending at the same point. We can interpret this problem as a complete graph in which every node represents a city and the edges between two nodes represents the distance between the two cities. This problem is a famous NP-hard problem and cannot be solved in polynomial time. Here, we generate a random TSP instance in the TSP class. The program randomly generates 7 cities on a 2D plane with x and y coordinates between 0 and 1. We then solve for this problem in the TSPSolver class which uses two methods.

## Brute Force 

