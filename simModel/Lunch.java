import simulationModelling.ConditionalActivity;

public class Lunch extends ConditionalActivity{

SMRepair model;
int classId;
int employeeId;
public Lunch(SMRepair model){
	this.model = model;
}

protected static boolean precondition(SMRepair model){
	if(UDP.getEmployeeForLunch() != null){
		return true;
		}
	return false;
	}

public void startingEvent(){
	String id = UDP.getEmployeeForLunch();
	String[] splitId = id.split(" ");
	classId = Integer.parseInt(splitId[0]);
	employeeId = Integer.parseInt(splitId[1]);
	model.rEmployee[classId][employeeId].status = "Lunch";
}
protected double duration(){
	return model.const.LunchDuration;
}

protected void terminatingEvent(){
	model.rEmployee[classId][employeeId].status = "Available";
	model.rEmployee[classId][employeeId].hadLunch = True;
}

}