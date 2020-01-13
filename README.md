# Evolutionary-Algorithm

This algorithms was developed by me for a University assignment. The algorithm is based on the biological evolutionary process. 

The algorithm starts with a random collection of individual. Each individual has its fitness, which is the measure of how close It is to the desired result. Calculation of fitness varies from case to case. 

Mutation is then applied to the whole population with a probability. Mutation involves changing the value of a character. Of course, its new value must come from the legal set of values.

After Mutation, some of the most fit individual are taken to produce children using crossover with a probability. The crossover operation is a simple array copying exercise. Suppose we have two parents, now a random index is chosen, using that index the first part of parent1 is combined with the second part of parent2 or vice versa to produce new children.

To maintain the population size, we remove the worst individual in the population and add the children. However, this only happens if the children are more with than the worst individual.


These steps are repeated until either the result is obtained or number of times the algorithm is ran on a population exceeds the given limit.

We run three scenario, this is project. Firstly, Binary Maximizer which takes a user input to produce a maximise binary string of arbitrary length. 

Second, String Generation (Weasel Class), which takes a string as an input from the user. Then produces that string using the algorithm. 

Lastly, The mathematical equation generator (Math Class), generates a mathematical equation o length entered by the user which equates to a. Particular target that is also set by the user.

Marks Obtained: 100/100
