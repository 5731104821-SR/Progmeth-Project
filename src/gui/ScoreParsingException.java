package gui;

public class ScoreParsingException /* fill code */extends Exception {

	private int errorType;
	
	public ScoreParsingException(int errorType){
		/* fill code */
		this.errorType = errorType;
	}
	
	@Override
	public String getMessage(){
		/* fill code */
		String error = "";
		if(this.errorType==0){
			error += "No record score";
		}
		if(this.errorType==1){
			error += "Wrong record format";
		}
		return error;
	}
}

