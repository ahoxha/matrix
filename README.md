# matrix

This program solves the problem of the matrix-chain multiplication. We are given a sequence (chain) 
(A<sub>1</sub>, A<sub>2</sub>, ... , A<sub>n</sub>) of n matrices to be multiplied, and we wish to compute the product:

A<sub>1</sub>A<sub>2</sub>  ...  A<sub>n<sub>
 
Matrix multiplication is associative, and so all parenthesizations yield the same product. 
A product of matrices is **fully parenthesized** if it is either a single matrix or the product 
of two fully parenthesized matrix products, surrounded by parentheses.

Let's consider the following chain of matrices (A<sub>1</sub>, A<sub>2</sub>, A<sub>3</sub>, 
A<sub>4</sub>). Then we can fully parenthesize
the product A<sub>1</sub>A<sub>2</sub>A<sub>3</sub>A<sub>4</sub> in 5 different ways:
* (A<sub>1</sub>(A<sub>2</sub>(A<sub>3</sub>A<sub>4</sub>)))
* (A<sub>1</sub>((A<sub>2</sub>A<sub>3</sub>)A<sub>4</sub>))
* ((A<sub>1</sub>A<sub>2</sub>)(A<sub>3</sub>A<sub>4</sub>))
* ((A<sub>1</sub>(A<sub>2</sub>A<sub>3</sub>))A<sub>4</sub>)
* (((A<sub>1</sub>A<sub>2</sub>)A<sub>3</sub>)A<sub>4</sub>)

How we parenthesize a chain of matrices can have a dramatic impact on the cost of evaluating the product.
We can multiply two matrices A and B only if they are compatible: the number of columns of A must equal 
the number of rows of B. If A is a `p*q` matrix and B is a `q*r` matrix, the resulting matrix C is a 
`p*r` matrix. The time to compute C is dominated by the number of scalar multiplications, which is 
`p*q*r`. We express costs in terms of the number of scalar multiplications.

To illustrate the different costs incurred by different parenthesizations of a matrix product, consider 
the problem of a chain (A<sub>1</sub>, A<sub>2</sub>, A<sub>3</sub>) of three matrices. Suppose that 
the dimensions of the matrices are `10*100`, `100*5`, and `5*50`, respectively. If we multiply according 
to the parenthesization ((A<sub>1</sub>A<sub>2</sub>)A<sub>3</sub>), we perform `10*100*5 = 5000` scalar 
multiplications to compute the `10*5` matrix product A<sub>1</sub>A<sub>2</sub>, plus another 
`10*5*50 = 2500` scalar multiplications to multiply this matrix by A<sub>3</sub>, for a total of 7500 
scalar multiplications. If instead we multiply according to the parenthesization 
(A<sub>1</sub>(A<sub>2</sub>A<sub>3</sub>)), we perform `100*5*50 = 25000` scalar multiplications to 
compute the `100*50` matrix product A<sub>2</sub>A<sub>3</sub>, plus another `10*100*50 = 50000` scalar 
multiplications to multiply A<sub>1</sub> by this matrix, for a total of 75000 scalar multiplications.
Thus, computing the product according to the first parenthesization is 10 times faster.

This program fully parenthesizes a chain of matrices in a way that minimizes the number of multiplications.
Note that it does not multiply the matrices, but instead determines the order for multiplying 
them that has the lowest cost. Typically, the time invested in determining this optimal order is more 
than paid for by the time saved later on when actually performing the matrix multiplications (such as 
performing only 7500 scalar multiplications instead of 75000).

## Building the project

This software is developed in Java and is shipped with gradle wrapper. You only need to have JDK installed,
version 1.8 or later.

After cloning the repository, using a command line tool, `CD` into the project folder.
- To build the project, type:
```shell script
./gradlew build
```
- To generate the IntelliJ IDEA files, type:
```shell script
./gradlew idea
```
- To generate the Eclipse files, type: 
```shell script
./gradlew eclipse
```
Then you can directly import it into IntelliJ IDEA or Eclipse.

- Ton run the program from the command line (after you have built it `./gradlew build`), type:
```shell script
./gradlew run
```

## How to use it?
Run the program and click on the `Help` button.
