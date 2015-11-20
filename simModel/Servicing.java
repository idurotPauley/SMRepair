import simulationModeling.ExtSequelActivity;

public class Servicing extends ExtSequelActivity{

private Employee rcEmployee;
	private Customer icCurrentCall;
	SMRepair model; / for referencing the model
	int classId;
	int EmployeeId;

public Servicing(SMRepair model){
	this.model = model;
}

public abstract int interruptionPreCond(){
	int days = model.getClock()/1440;
	int timeCompleted = icCurrentCall.arrivalTime + RVP.DuServiceTime(icCurrentCall.equipmentType) - days*1440
	if (icCurrentCall.contractType == "Basic" and timeCompleted > 570){
		return 1;
	}
	return 0;
}

public abstract void interruptionSCS(int interruptID){}
	if(interruptID == 1){
		model.rEmployee[classId][employeeId].numOvertimeHours += 0.5;
		model.rEmployee[classId][employeeId].status = "Available";
	}

}
public void startingEvent(){
	String id = UDP.getArrivedEmployee();
		String[] splitId = id.split(" ");
		classId = Integer.parseInt(splitId[0]);
		employeeId = Integer.parseInt(splitId[1]);
	model.rEmployee[classId][employeeId].status = "Servicing"
	icCurrentCall = model.rEmployee[classId][employeeId].assignedTo;
}

@Override
protected double duration(){
	return model.rvp.DuServiceTime(icCurrentCall.equipmentType);
}

	@Override
	protected void terminatingEvent(){
		int days = model.getClock();
		int timeCompleted = icCurrentCall.arrivalTime + RVP.DuServiceTime(icCurrentCall.EquipmentType) - days*1440;
		if (icCurrentCall.contractType == Basic){
			if(540 < timeCompleted < 570){
				model.rEmployee[classId].[employeeId].numOvertimeHours += (timeCompleted - 540)/60;
			}
		}
		else{
			if(timecompleted > 540){
				model.rEmployee[classId][employeeId].numOvertimeHours += (timeCompleted - 540)/60;
			}
		}
		model.rEmployee[classId][employeeId].status = "Available";
		model.rEmployee[classId][employeeId].assignedTo == null;
	}

}