package NetworkProject;

public class data2 {
	private String num,clock1,clock2;

	public data2(){

	}

	public data2(String num,String clock1,String clock2){
		this.num = num;
		this.clock1 = clock1;
		this.clock2 = clock2;
	}

	public String getNum(){
		return num;
	}
	public void SetNum(String num){
		this.num = num;
	}

	public String getClock1(){
		return clock1;
	}
	public void SetClock1(String clock1){
		this.clock1 = clock1;
	}

	public String getClock2(){
		return clock2;
	}
	public void SetClock2(String clock2){
		this.clock2 = clock2;
	}
}
