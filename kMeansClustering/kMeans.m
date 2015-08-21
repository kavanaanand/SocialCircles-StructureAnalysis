
% Reading Adjacency Matrix from file 'lidata.csv'
filename = 'Files/adjmat_kavana.txt';
A = csvread(filename);

% Writing raw data file using Adjacency matrix csv
noOfNodes = size(A,1);              % Get no of nodes in the network
upperTriangle = triu(A);            % Get only upper triangle of A to calcuate edges once
[i,j,val] = find (upperTriangle);   % Get all the non zero edge pair (i,j)
Edges = [i,j];
EdgeTranspose = transpose(Edges);   % Getting transpose as fprintf prints the matrix column wise
noOfEdges = nnz(upperTriangle);     % Number of non zero elements in upper traingle gives number of edges

filename2 = 'rawdata.txt';          % Creating and opening raw data file
fileID = fopen(filename2,'w');
fprintf(fileID,'%d\n%d\n',noOfNodes,noOfEdges);    % Writing Number of nodes and Number of edges in the network
fclose(fileID);
fileID = fopen(filename2,'a');
fprintf(fileID,'(%d,%d)\n',EdgeTranspose);             % Writing edges to the file     
fclose(fileID);



XY = rand(noOfNodes, 2);


% --------- Spectral Clustering ----------

nodeDegree = sum(A,2);                  % Get degree of each node as a list
D = diag(nodeDegree);                   % Get Diagonal matrix which is degree matrix

L = D - A;                              % Compute Laplacian matrix

D = pinv(D);                            % Get inverse of D
L = D * L;                              % Compute normalized Laplacian


% ------ k means clustering --------


 k = 5;                                          % Number of Clusters
 
 % Compute Eigen Vectors and Eigen Values - k smallest vectors
 diff = eps;                                     % eps returns the distance from 1.0 to the next largest double-precision number, that is eps = 2^(-52)
 [eigenVectors, eigenValues] = eigs(L, k, diff); %diff should be close to zero and hence eps is used
 
 % Plot second third and fourth eigen vectors
 figure;
 plot3(eigenVectors(:,2),eigenVectors(:,3),eigenVectors(:,4),'*');
 hold on
 title '2nd and 3rd Eigen Vectors - k means CLUSTERING';
 hold off
 
 % Apply k means algorithm to divide the netwrok into k clusters, C will have the cluster number corresponding to that index - n by 1 matrix
 C = kmeans(eigenVectors, k);
  
 n1 = size(XY(C==1,:));       % Number of nodes in Cluster 1
 n2 = size(XY(C==2,:));       % Number of nodes in Cluster 2
 n3 = size(XY(C==3,:));       % Number of nodes in Cluster 3
 n4 = size(XY(C==4,:));       % Number of nodes in Cluster 4
 n5 = size(XY(C==5,:));
%  n6 = size(XY(C==6,:));
 
% Writing the results to kMeansResults.txt
 filename4 = 'kMeansResults.txt';         
 fileID3 = fopen(filename4,'w');
     for i = 1:length(C);
         fprintf(fileID3,'%d %d\n',i,C(i));
     end
 fclose(fileID3);
 
 
 fprintf('k means Clustering Count Results\n');
 fprintf('Cluster 1 : %d\n',n1(:,1));
 fprintf('Cluster 2 : %d\n',n2(:,1));
 fprintf('Cluster 3 : %d\n',n3(:,1));
 fprintf('Cluster 4 : %d\n',n4(:,1));
 fprintf('Cluster 5 : %d\n',n5(:,1));
%  fprintf('Cluster 6 : %d\n',n6(:,1));

 
 %  k means Clustered Eigen Vector 3D
 figure;
 plot3(eigenVectors(C==1,2),eigenVectors(C==1,3),eigenVectors(C==1,4),'r*')
 hold on;
 plot3(eigenVectors(C==2,2),eigenVectors(C==2,3),eigenVectors(C==2,4),'bo')
 plot3(eigenVectors(C==3,2),eigenVectors(C==3,3),eigenVectors(C==3,4),'c.','MarkerSize',18)
 plot3(eigenVectors(C==4,2),eigenVectors(C==4,3),eigenVectors(C==4,4),'m+')
 plot3(eigenVectors(C==5,2),eigenVectors(C==5,3),eigenVectors(C==5,4),'gs')
%  plot3(eigenVectors(C==6,2),eigenVectors(C==6,3),eigenVectors(C==6,4),'yd')
 legend('Cluster 1','Cluster 2','Cluster 3','Cluster 4','Cluster 5')
 title 'k means CLUSTERING using 2nd 3rd and 4th eigen Vectors'
 hold off;
