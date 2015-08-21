import java.io.*;
import java.util.*;
public class adjmat {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashMap<String,Integer> hm = new HashMap<String,Integer>();
		int index = 1;
		try {
			
			int[][] adjmatrix = new int[760][760];
			
			for(int i=1;i<=750;i++)
			{
				for(int j=1;j<=750;j++)
				{
					adjmatrix[i][j] = 0;
				}
			}
			
			File file2 = new File("/home/swijal/Desktop/social/kavanamutual.txt");
			FileReader fileReader2 = new FileReader(file2);
			BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
			
			File fout = new File("/home/swijal/Desktop/social/kavana_mapping_out.txt");
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			File fout1 = new File("/home/swijal/Desktop/social/adjmat_kavana.txt");
			FileOutputStream fos1 = new FileOutputStream(fout1);
			BufferedWriter bw1 = new BufferedWriter(new OutputStreamWriter(fos1));
			
			File file = new File("/home/swijal/Desktop/social/numbering_kavana.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			
			
			String line;
			String friend1 = "";
			String friend2 = "";

			//numbering
			while ((line = bufferedReader.readLine()) != null) {
				if(!hm.containsKey(line))
				{
					hm.put(line, index);
					bw.write(line+", "+index);
					bw.newLine();
					index++;
				}
			}
			//System.out.println(hm.size());
			//adj matrix
			while ((line = bufferedReader2.readLine()) != null) {
				friend1 = "";
				friend2 = "";
				
				String[] ar=line.split(",");
				
				friend1 = ar[0];
				friend2 = ar[1];
				System.out.println(friend1 +" : "+ friend2);
				int p=0,q=0;
				
				if(hm.containsKey(friend1))
					p = hm.get(friend1);
				
				if(hm.containsKey(friend2))
					q = hm.get(friend2);
				
				adjmatrix[p][q] = 1;
				adjmatrix[q][p] = 1;
			}
			
			for(int i=1;i<=750;i++)
			{
				bw1.newLine();
				for(int j=1;j<=750;j++)
				{
					bw1.write(adjmatrix[i][j]+",");
					
				}
			}
			
			/*for(int i=0;i<750;i++)
			{
				System.out.println();
				for(int j=0;j<750;j++)
				{
					System.out.print(adjmatrix[i][j]+ " ");
				}
			}*/
			fileReader2.close();
			fileReader.close();
			bw.close();
			bw1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
