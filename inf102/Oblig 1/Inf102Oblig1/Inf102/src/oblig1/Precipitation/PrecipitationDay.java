package oblig1.Precipitation;


public class PrecipitationDay{

private int dayL;
private String monthL;
private double persipitationL;
private PrecipitationDay previousDay;

public PrecipitationDay(int day, String month, double persipitation){
  dayL = day;
  monthL = month.trim();
  persipitationL = persipitation;
  previousDay = null;
}

public PrecipitationDay getPrevious(){
  return previousDay;
}

public void setPrevious(PrecipitationDay d){
  previousDay = d;
}



public String toString(){
	String str = "" + dayL + "\t" + monthL +"\t"+ persipitationL;
	if ( previousDay != null)
		return str +"\t\t"+ previousDay.getDay() + "  " + previousDay.getMonth() + "\t\t\t" + previousDay.getPersipitation();
	else
		return str + "\t\tOut of range"; 
		
}

public int getDay(){
return dayL;
}

public String getMonth(){
return monthL;
}

public double getPersipitation(){
  return persipitationL;
}
public static void main(String [] args) {
	PrecipitationDay a = new PrecipitationDay(12, "jan", 0.02);
	PrecipitationDay b = new PrecipitationDay(11, "jan", 0.01);
	PrecipitationDay c = new PrecipitationDay(12, "jan", 0.10);
	if(a.getPersipitation() < b.getPersipitation()) {
		b.setPrevious(a); 
		System.out.println("en");
	}
	else {
		a.setPrevious(b);
		System.out.println("to");
	}
	//System.out.println(a.toString());
	//System.out.println(b.toString());

}



}