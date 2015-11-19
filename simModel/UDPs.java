package simModel;

class UDPs 
{
	ModelName model;  // for accessing the clock
	
	// Constructor
	protected UDPs(ModelName model) { this.model = model; }

	// Translate User Defined Procedures into methods
    /*-------------------------------------------------
	                       Example
	    protected int ClerkReadyToCheckOut()
        {
        	int num = 0;
        	Clerk checker;
        	while(num < model.NumClerks)
        	{
        		checker = model.Clerks[num];
        		if((checker.currentstatus == Clerk.status.READYCHECKOUT)  && checker.list.size() != 0)
        		{return num;}
        		num +=1;
        	}
        	return -1;
        }
	------------------------------------------------------------*/
	
	public int getScaledClock(){
		int days = model.getClock()/1440;
		return model.getClock() - days*1440;
	}
	
}
