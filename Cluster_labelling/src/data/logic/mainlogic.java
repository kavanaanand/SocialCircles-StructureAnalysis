package data.logic;

import java.io.*;
import java.util.*;

import data.storage.*;

public class mainlogic {
	
	
	public HashMap<String,ArrayList<String>> flushhashedmap(HashMap<String,ArrayList<String>> buckmap)
	{
		for (Map.Entry<String, ArrayList<String>> entry : buckmap.entrySet()) 
		{
			String key = entry.getKey();
			ArrayList<String> temp = new ArrayList<String>();
			buckmap.put(key, temp);
		}
		return buckmap;
		
	}
	public static void main(String[] args) {
		try{
		
			HashMap<String,Friendsdata> Frnddatamap = new HashMap<String,Friendsdata>();
			HashMap<String,Groupsdata> Grpdatamap = new HashMap<String,Groupsdata>();
			HashMap<String,String> Mydatamap = new HashMap<String,String>();
			ArrayList<String> bucketmap = new ArrayList<String>();
			ArrayList<String> randomvalues = new ArrayList<String>();
			HashMap<String,ArrayList<String>> Bucketmapping = new HashMap<String,ArrayList<String>>();
			HashMap<String,ArrayList<String>> Buckethashing = new HashMap<String,ArrayList<String>>();
			HashMap<String,ArrayList<String>> Buckethashtemp = new HashMap<String,ArrayList<String>>();
			HashMap<String,String> Groupmap = new HashMap<String,String>();
			HashMap<String, Integer> Percommmap = new HashMap<String, Integer>();
			
			File file = new File("SwijData.txt");
			//File file = new File("KavanaData.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			File file1 = new File("swijal_groups_names.txt");
			//File file1 = new File("kavana_groups_names.txt");
			FileReader fileReader1 = new FileReader(file1);
			BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
			
			File file2 = new File("SwijFriendsData.txt");
			//File file2 = new File("KavanaFriendsData.txt");
			FileReader fileReader2 = new FileReader(file2);
			BufferedReader bufferedReader2 = new BufferedReader(fileReader2);
			
			File fout = new File("swijal_results.txt");
			//File fout = new File("kavana_results.txt");
			FileOutputStream fos = new FileOutputStream(fout);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			
			String line = "";
			
			while ((line = bufferedReader.readLine()) != null) //Read and fill my data
			{	
				String[] str = line.split(":");
				//System.out.println(str[0] + str[1]);
				Mydatamap.put(str[0], str[1]);
			}
			while((line = bufferedReader1.readLine()) != null)  // Read and fill my group data
			{
				String[] str = line.split(",");
				ArrayList<String> temp = new ArrayList<String>();
				for(int i=1;i<str.length;i++)
					temp.add(str[i]);
				Groupsdata g = new Groupsdata();
				g.setMembers(temp);
				g.setDescription("");
				Grpdatamap.put(str[0], g);
			}
			/*for (Map.Entry<String, Groupsdata> entry : Grpdatamap.entrySet()) 
			{
				String key = entry.getKey();
				Groupsdata value = entry.getValue();
				System.out.println(value.getMembers());
			}*/
			while((line = bufferedReader2.readLine()) != null)  // Read and fill my friends data
			{
				String[] str = line.split(",");
				ArrayList<String> temp = new ArrayList<String>();
				for(int i=4;i<str.length;i++)
					temp.add(str[i]);
				Friendsdata fd = new Friendsdata();
				fd.setCurrent_location(str[1]);
				fd.setHometown(str[2]);
				fd.setNumberofcolleges(Integer.parseInt(str[3]));
				fd.setColleges(temp);
				Frnddatamap.put(str[0], fd);
				//System.out.println(temp);
			}
			
			//Put all my data values in buckets in buckets mapping map
			
			for (Map.Entry<String, String> entry : Mydatamap.entrySet()) 
			{
				String value = entry.getValue();
				ArrayList<String> temp = new ArrayList<String>();
				Bucketmapping.put(value, temp);
				Buckethashing.put(value,temp);
				bucketmap.add(value);
			}
			
			//Map all group names to bucketmapping buckets
			
			for (Map.Entry<String, Groupsdata> entry : Grpdatamap.entrySet()) 
			{
				String key = entry.getKey();
				Groupsdata value = entry.getValue();
				//System.out.println("swijal");
				for(int i=0;i<bucketmap.size();i++)
				{
					//System.out.println(i);
					if((bucketmap.get(i)).contains(key) || key.contains(bucketmap.get(i)))
					{
						ArrayList<String> t = Bucketmapping.get(bucketmap.get(i));
						t.add(key);
						Bucketmapping.put(bucketmap.get(i),t);
						Groupmap.put(key, bucketmap.get(i));
						break;
					}
				}
			}
			
			/*for(Map.Entry<String,ArrayList<String>> entry : Bucketmapping.entrySet())
			{
				ArrayList temp = entry.getValue();
				System.out.println(temp);
			}*/
			
			mainlogic ml = new mainlogic();
			Buckethashtemp = ml.flushhashedmap(Buckethashing);
			Buckethashing = Buckethashtemp;
			
			//fetching n random values for testing
			
			Process p = new Process();
			ArrayList<PersonModel> pm = p.readMapping();
			ArrayList<ClusterModel> cm = p.readCluster();
			
			//6 for kavana and 5 for swijal
			for(int m=1;m<=5;m++)
			{
				for(int x=0;x<5;x++)
				{
					int n = 10;
					int clusterNum = m;
					
					randomvalues = p.getRandomFriends(n, clusterNum, pm, cm);
					
					/*for(int i=0; i<randomvalues.size() ;i++)
						System.out.println("name:" + randomvalues.get(i));*/
					
					/*for (Map.Entry<String, Groupsdata> entry : Grpdatamap.entrySet()) 
					{
						String key = entry.getKey();
						Groupsdata value = entry.getValue();
						System.out.println(key);
					}*/
					
					/*for (Map.Entry<String, String> entry : Groupmap.entrySet()) 
					{
						String key = entry.getKey();
						String value = entry.getValue();
						System.out.println(key+":"+value);
					}*/
					
					for(int i=0;i<randomvalues.size();i++)
					{
						if(Frnddatamap.containsKey(randomvalues.get(i)))
						{
							for (Map.Entry<String, Groupsdata> entry : Grpdatamap.entrySet()) 
							{
								String key = entry.getKey();
								Groupsdata value = entry.getValue();
								ArrayList<String> temp = value.getMembers();
								if(temp.contains(randomvalues.get(i)))
								{
									String bucket = Groupmap.get(key);
									ArrayList<String> temp1 = Buckethashing.get(bucket);
									if(!(temp1.contains(randomvalues.get(i))))
									{
										temp1.add(randomvalues.get(i));
										Buckethashing.put(bucket, temp1);
									}
								}
							}
							Friendsdata f = Frnddatamap.get(randomvalues.get(i));
							
							//current location check
							String bucket_name = ml.bucketchecking(f.getCurrent_location(),bucketmap);
							if(!(bucket_name.equals("unknown")))
							{}
							else
								bucket_name = ml.groupnameschecking(f.getCurrent_location(),Groupmap);
							//put it in bucket hash for the same friend
							if(!(bucket_name.equalsIgnoreCase("unknown")))
							{
								ArrayList<String> temp = Buckethashing.get(bucket_name);
								if(!temp.contains(randomvalues.get(i)))
								{
									temp.add(randomvalues.get(i));
									Buckethashing.put(bucket_name, temp);
								}
							}
							
							//hometown check
							bucket_name = ml.bucketchecking(f.getHometown(),bucketmap);
							if(!(bucket_name.equals("unknown")))
							{}
							else
								bucket_name = ml.groupnameschecking(f.getHometown(),Groupmap);
							//put it in bucket hash for the selected friend
							if(!(bucket_name.equalsIgnoreCase("unknown")))
							{
								ArrayList<String> temp = Buckethashing.get(bucket_name);
								if(!temp.contains(randomvalues.get(i)))
								{
									temp.add(randomvalues.get(i));
									Buckethashing.put(bucket_name, temp);
								}
							}
							
							//Colleges check
							for(int k=0;i<f.getColleges().size();i++)
							{
								bucket_name = ml.bucketchecking(f.getColleges().get(k),bucketmap);
								if(!(bucket_name.equals("unknown")))
								{}
								else
									bucket_name = ml.groupnameschecking(f.getHometown(),Groupmap);
								//put it in bucket hash for the selected friend
								if(!(bucket_name.equalsIgnoreCase("unknown")))
								{
									ArrayList<String> temp = Buckethashing.get(bucket_name);
									if(!temp.contains(randomvalues.get(i)))
									{
										temp.add(randomvalues.get(i));
										Buckethashing.put(bucket_name, temp);
									}
								}
							}
							
						}
					}
					
				}
				//Find max comm and label it for m comm and then flush hashmap hashing
				int max=0;
				String max_comm = "";
				for(Map.Entry<String,ArrayList<String>> entry : Buckethashing.entrySet())
				{
					//System.out.println(entry.getKey() + ":" + entry.getValue());
					if(entry.getValue().size() > max)
					{
						max = entry.getValue().size();
						max_comm = entry.getKey();
					}
				}
				System.out.println(m + ":" + max_comm);
				//bw.write(m + ":" + max_comm);
				Buckethashtemp = ml.flushhashedmap(Buckethashing);
				Buckethashing = Buckethashtemp;
			}
			
			
	} catch (IOException e) {
		e.printStackTrace();
	}		
	}
	
	
	private String groupnameschecking(String current_value, HashMap<String, String> groupmap) 
	{
		for (Map.Entry<String, String> entry : groupmap.entrySet()) 
		{
			String key = entry.getKey();
			//System.out.print(key + ",");
			String value = entry.getValue();
			String[] str = key.split(" ");
			for(int j=0;j<str.length;j++)
			{
				if(current_value.contains(str[j]))
				{
					return value;
				}
			}
		}
		return "unknown";
	}
	
	public String bucketchecking(String current_value, ArrayList<String> bucketmap) 
	{
		//System.out.println(bucketmap);
		for(int i=0;i<bucketmap.size();i++)
		{
			String[] str = bucketmap.get(i).split(" ");
			for(int j=0;j<str.length;j++)
			{
				//System.out.println(str[j]);
				if(current_value.contains(str[j]))
				{
					return bucketmap.get(i);
				}
			}
		}
		return "unknown";
	}

}
