package data.logic;
public class ClusterModel {

	private int id;
	private int clusterNum;

	public ClusterModel(int id, int clusterNum) {
		super();
		this.id = id;
		this.clusterNum = clusterNum;
	}

	public int getClusterNum() {
		return clusterNum;
	}

	public void setClusterNum(int clusterNum) {
		this.clusterNum = clusterNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
