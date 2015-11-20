import simulationModelling.ConditionalActivity;

public class Traveling extends ConditionalActivity{
	private Employee rcEmployee;
	private Customer icCurrentCall;
	SMRepair model; / for referencing the model
	int classId;
	int employeeId;
	public Travelling(SMRepair model) {this.model = model;}

	protected static boolean precondition(SMRepair model){
		int days = model.getClock()/1440;
		if(UDPs.getAvailableEmployee() != null && (model.getClock - days * 1440) <= 510){
			return true;
		}
		else{
			return false;
		}
	}

	public void startingEvent(){
		String id = UDP.getAvailableEmployee();
		String[] splitId = id.split(" ");
		classId = Integer.parseInt(splitId[0]);
		employeeId = Integer.parseInt(splitId[1]);
		model.rEmployee[classId][employeeId].status = "Traveling";
		model.rEmployee[classId][employeeId].assignedTo = UDP.getNextCall(classId);
	}

	@Override
	protected double duration(){
		return model.rvp.DuTravelTime();
	}

	@Override
	protected void terminatingEvent(){
		icCurrentCall =  model.rEmployee[classId][employeeId].assignedTo;
		if(icCurrentCall.contractTType == "Basic"){
			output.servicedBasicCalls += 1;
			if(icCurrentCall.arrivalTime - iC.currentCall.timeRequested) <= 1440{
				output.numSatisfiedBasicCalls +=1;
			}
			output.basicCustomerSatisfaction = output.numSatisfiedBasicCalls/output.servicedBasicCalls;
		}else{
			output.servicedPremiumCalls += 1;
			if((icCurrentCall.arrivalTime - icCurrentCall.timeRequested) <= 180){
				output.numSatisfiedBasicCalls += 1;
			} else if (900 < icCurrentCall.ArrivalTime <= 1080){ //need to verify.
				output.numSatisfiedPremiumCalls += 1;
			}
			output.premiumCustomerSatisfaction = output.numSatisfiedPremiumCalls/output.servicedPremiumCalls;
		}
		model.rEmployee[classId][employeeId].assignedTo = icCurrentCall;
		model.rEmployee[classId][employeeId].status = "Arrived";



	}
}