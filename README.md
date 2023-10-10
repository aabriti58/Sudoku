This is a Sudoku project which combines mostly the knowledge of 2 dimensional array to solve the Sudoku puzzle board. Sudoku is 9x9 puzzle in which we should find the missing values in either row or column.
In this project, we are given the input in the form of matrix which should be inputed through console and display the output. We have three type: Type 1, Type 2 and Type 3.
In Type 1, one value is missing in 9x9 board so, we have to find the missing value using either row or column.
In Type 2, 2 values are missing which are always side-by-side. If two values are in the same row then, we have to use Type1 column method to find the missing value, whereas, if two missing values are in the same column, we have to use Type1 row method.
In Type3, we also have to use the concept of 3x3 small boxes inside the Sudoku board which makes it a bit more complicated. There are three missing values which are always in the "L" shape. Two of the values are always in same row and two are always in the same column. 3x3 box should be checked first to find the first missing value. After it is found, rest is just the Type2 problem.

