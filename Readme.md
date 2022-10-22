# **Readme** 

This project is for demonstrating minmax and alpha beta pruning in artificial intelligence. 

Few things about this project - 
1. inputFile.txt, shows the sample test case. Please give input in that format only and input file should be in the root directory of the project.
2. The execution arguments can be :  [-v] [-ab] [min/max] [inputFile.txt] <br>
        2.1. [-v] : Is verbose mode i.e. you'll get proper output with sentences <br>
        2.2. [-ab] : To apply alpha beta pruning <br>
        2.3. [min/max] : Is your player max or min <br>
3. This can also show any error in your inputFile i.e Multiple roots, no roots or cyclic graph (with showing which nodes)

Attaching few snapshots of output <br> 
Sample 1 <br> <br>
![img.png](img.png) <br> <br>
Sample 2 <br> <br>
![img_1.png](img_1.png)
 <br> 


To execute the project do run the following commands in the project root directory :

1. mvn clean install
2. java -jar target/AILab-1.0-SNAPSHOT.jar [-v] [-ab] min/max inputFile.txt


If you find any discrepancy contact me on - rs8375@nyu.edu