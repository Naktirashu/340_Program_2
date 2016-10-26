# 340_Program_2

Program 2 (75 pts)

Background

This program has you write an iterative deepening depth-first search with cycle checking that uses branch and bound. Your goal is to find all goal nodes that are closest to the start node. You can get a lot of code concerning each of these types of searches, you’ll have to do some work to fit it all together.

Problem

Given a directed graph in the form specified in “File Input”, perform an iterative-deepening depth-first search with branch-and-bound – that uses cycle checking - on that graph. Use a Java Swing or Java FX GUI to allow the user to specify the following three parameters of the search:

The file name (using a method such as JFileChooser or something equally simple).

The initial depth for iterative deepening.

The increment by which the depth increases in each subsequent search. Depth is counted by total distance from the start node.

The graph will contain a single start node, and zero or more goal nodes. Find all goal nodes at the minimum distance from the start node. Stop the program after the first iteration in which you find a goal node, or when you have exhausted the entire graph.

When doing the depth first search, first expand the nearest node; if two nodes are equally close, expand the one whose name is lexicographically first in the alphabet.

Use cycle checking to avoid visiting nodes that you have already visited, unless you found a shorter path to them.

File input:

The input file will contain a set of nodes in adjacency list format. Each node will take up two lines. The first line will contain the name of the node, an “S” or “N” that indicates whether it is a start node and a “g” or “n” that indicates whether it is a goal node. The subsequent line will contain the name of each node adjacent to that node, followed by its distance from the present node. A blank line will separate nodes.

See Appendix 1 for an example.

Output:

The output should start by listing the depth of that search, then within that search it should list the names of nodes expanded, and should indicate when a depth bound is reached or a goal node is found. Write the output to a file. If the input file is named “file1.txt” the output file should be “file1_search.txt”. It should be in the same directory as the input file.

When you exhaust one branch of the search and need to backtrack without finding a goal node, write (X); when you exhaust a branch of the search because you find a goal node write (Goal!) In these cases, start a new line. At the end of the search, mark whether it failed naturally, failed unnaturally, or succeeded. Note that if you find a goal node at a depth less than the maximum depth of the search, you must lower the depth bound.

See Appendix 2 for the output from Appendix 1 file run with initial depth = 2 and increment = 3.

Grading:

Grading will be done via the grading rubric available on the D2L page.

With respect to the “compilation” and “correctness” parts of the rubric:

Compilation is worth 30%. That is 22.5 points.

You get 7.5 points if it runs.

You get another 5 points if it tries to do DFS

You get another 5 points if it tries to do iterative deepening

You get the final 5 points if it tries to do branch and bound.

Correctness is worth 50%. That is 37.5 points.

You get 10 points for reading the input properly.

You get 10 points each for correctness of DFS, iterative deepening, and branch-and-bound.

You get 10 points for proper reporting of output.

Due Date

The program is due Friday, November 4th, 11:59 PM. Late work will be accepted (with penalty) through November 6th.

Rules

These rules will apply to all the programs for this course, unless I say otherwise for a specific program (and I don’t plan to).

You may use any code you can find on the internet (or any other published source) as part of your program. If you take code from the internet, you must acknowledge your sources. You may not use code from your classmates or other unpublished sources. Two programs or program fragments that are unduly similar and are not acknowledged as having come from a published source will be considered as being incidences of plagiarism, with a 0 for the assignment and a possible writeup and report to the university’s academic integrity committee. Note that a “published source” is not somebody else’s program unless it’s on a publicly accessible, free site (thus, for instance, stackoverflow.com is fine, but Chegg (which requires signup) is not).

Your program must run on the university’s Windows computers “as is” – without having to import any packages that are not part of the Java 8 SE installed on the university systems. I will update my computer to run Java 8. If your program doesn’t run on my computer, or if you think it runs incorrectly on my computer, I will try it on a university computer with the standard image. The results of running it on that platform will be final.

There are two situations in which I have seen numerous problems with this:

People who write their programs on Macs sometimes have trouble, especially with I/O. If you are planning to write programs on a Mac this semester, I strongly advise you to test your Program 0 on the university systems before submitting it. Since we are at Normandale this semester, not at a Metro State campus, if you write a Mac program that looks like it should work properly, we can negotiate what to do about it.

People who import other packages, most commonly imports from eclipse.org or netbeans.org. Do not import anything outside of the Java 8 SE to do problems in this class, and if you do so, this may make your program not compile and/or run properly on the university system (or mine). This will severely impact your grade, since a non-running program can get at best a D grade. There is no reason for you to make the mistake of importing any package not in the Java 8 SE. If you want to use code from the internet that uses such packages, you’ll need to strip out those imports and rewrite the equivalent functionality yourself.

Appendix

For file input example, here is a file from a recent homework and how it would look as input. Note that the node names are strings, not single characters. Note that for nodes with outdegree zero, the line of adjacent nodes is non-existent.

S S n
B 1 E 2

B N n
C 2 F 3

C N n
D 2 GA 4

D N n
GA 1

E N n
B 1 F 3 H 6

F N n
I 3 GA 3 C 1

GA N g

H N n
I 2 GB 2 F 1

I N n
GA 2 GB 2

GB N g

Appendix 2 – sample output

For the example above, here is how a program run would look if the initial depth of search was 2 and the increment was 3.

Depth bound = 2

S B (X)

S E (X)

Search failed unnaturally.

Depth bound = 5

S B C D (X)

C (X)

B F (X)

B (X)

S E (X)

Search failed unnaturally.

Depth bound = 8

S B C D GA (Goal!)

Depth bound lowered to 6

D (X)

C (X)

B F (X)
B (X)

S E (X)

Search succeeded. Goal nodes found = GA.

