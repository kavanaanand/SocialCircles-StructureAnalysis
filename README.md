## README
This project is developed as part of CSE592 - Social Networks Course Project at SBU

## Summary
- Project aimed to cluster the individual’s ego network into possible social circles and analyze the structure of each circle and abstract the common parameters and derive why a particular circle was formed and label the circle and verify with the ground truth
- Project was developed in two modules, 
	* first module programmed in MATLAB involved mapping of ego network and clustering using kMeans clustering 
	* second module programmed in Java invloved constructing algorithm which runs series of comparisons with different samples and finally label the circle appropriately based 	  on its results

For more details [Click Here](https://github.com/kavanaanand09/SocialCircles-StructureAnalysis/blob/master/projectSocialNetworks.pdf)

## Source Code Files: 
1. getAdjacencyMatrix.java - Program that takes mutual links as input and outputs the corresponding adjacency matrix
2. kMeansClustering/kMeans.m - Program that takes adjacency matrix as input and produce output file which has kMeansResults
3. Cluster_labelling/src/data/logic/mainlogic.java - Program that gives the final output of the project

## Supporting Files:
1. Cluster_labelling/src/data/logic/Process.java
2. Cluster_labelling/src/data/logic/datastorage.java  

## Model Files:
1. Cluster_labelling/src/data/logic/ClusterModel.java 
2. Cluster_labelling/src/data/logic/PersonModel.java 

## How to run the program,
Run the following java file and you should be able to see 5 different names which indicates the table of each cluster.  
Cluster_labelling/src/data/logic/mainlogic.java

## Team
- Kavana Anand
- Swijal Patil