package NetworkProject;

public class data {
	private String id, name;

	public data(){

	}

	public data(String name,String id){
		this.id = id;
		this.name = name;
	}

	public String getId(){
		return id;
	}
	public void SetId(String id){
		this.id = id;
	}

	public String getName(){
		return name;
	}
	public void SetName(String name){
		this.name = name;
	}
}
