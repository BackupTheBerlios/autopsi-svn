package autopsi.javaspace;

public class SpaceThread extends Thread {

	
	protected ObjectSpaceSharer oss;
	
	public SpaceThread(ObjectSpaceSharer oss){
		this.oss = oss;
		this.start();
	}
	
	public void run(){
		oss.shareObjects();
	}
}
