package data.logic;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Process {

	public ArrayList<PersonModel> readMapping() {
		ArrayList<PersonModel> pm = new ArrayList<PersonModel>();
		try {
			// reading the file
			BufferedReader br = new BufferedReader(new FileReader("swijal_mapping_out.txt"));
			//BufferedReader br = new BufferedReader(new FileReader("kavana_mapping_out.txt"));
			// Read the first line
			String line = br.readLine();

			// Loop till the end of file
			while (line != null) {
				addToArray(line, pm);
				line = br.readLine();
			}
			// release
			br.close();
			return pm;
		} catch (Exception e) {
			// Most probable reason would be File not found, in that case make
			// sure to give correct file path

			// Another reason may be your system ran out of memory, so
			// OutOfMemory because of really really large (GBs) input text file,
			// if this happens call me , we will figure out something
			System.out.println("File reading failed");
			e.printStackTrace();
		}

		return null;

	}

	private static void addToArray(String line, ArrayList<PersonModel> pm) {
		// Split the line with comma
		String idNgroup[] = line.split(",");

		String name = idNgroup[0]; // yeah redundant variable ;)

		String numWithoutSpace = idNgroup[1].trim(); // in the input file, there
														// is leading space

		int id = Integer.parseInt(numWithoutSpace); // convert string to integer

		// You could just print , i added to the array
		pm.add(new PersonModel(name, id));

	}

	public ArrayList<ClusterModel> readCluster() {

		ArrayList<ClusterModel> cm = new ArrayList<ClusterModel>();
		try {
			// reading the file
			BufferedReader br = new BufferedReader(new FileReader("swijal_kMeansResults.txt"));
			//BufferedReader br = new BufferedReader(new FileReader("kavana_kMeansResults.txt"));
			// Read the first line
			String line = br.readLine();

			// Loop till the end of file
			while (line != null) {
				addToArrayCluster(line, cm);
				line = br.readLine();
			}
			// release
			br.close();

			return cm;
		} catch (Exception e) {
			// Most probable reason would be File not found, in that case make
			// sure to give correct file path

			// Another reason may be your system ran out of memory, so
			// OutOfMemory because of really really large (GBs) input text file,
			// if this happens call me , we will figure out something
			System.out.println("File reading failed");
			e.printStackTrace();
		}

		return null;

	}

	// made this static , just for the demo purpose
	private void addToArrayCluster(String line, ArrayList<ClusterModel> cm) {
		// Split the line with space
		String idNcluster[] = line.split(" ");
		// [0] is id
		int id = Integer.parseInt(idNcluster[0]); // Convert string to int
		// [1] is cluster number
		int clusterNum = Integer.parseInt(idNcluster[1]);

		cm.add(new ClusterModel(id, clusterNum));

	}

	public ArrayList<String> getRandomFriends(int count, int clusterNum,
			ArrayList<PersonModel> pm, ArrayList<ClusterModel> cm) {

		ArrayList<ClusterModel> sCm = new ArrayList<ClusterModel>();
		for (ClusterModel c : cm) {
			if (c.getClusterNum() == clusterNum) {
				sCm.add(c);
			}
		}

		List<ClusterModel> randomList;
		if (sCm.size() > count) {
			randomList = pickNRandom(sCm, count);
		} else {
			randomList = sCm;
		}

		ArrayList<String> names = new ArrayList<String>();

		for (ClusterModel c : randomList) {

			names.add(getNameFromId(c.getId(), pm));

		}
		return names;
	}

	private String getNameFromId(int id, ArrayList<PersonModel> pm) {
		for (PersonModel p : pm) {
			if (p.getId() == id) {
				return p.getName();
			}
		}
		return "";
	}

	public java.util.List<ClusterModel> pickNRandom(ArrayList<ClusterModel> lst,
			int n) {
		ArrayList<ClusterModel> copy = new ArrayList<ClusterModel>(lst);
		Collections.shuffle(copy);
		return copy.subList(0, n);
	}

}
