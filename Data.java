import java.util.ArrayList;

public class Data {
String username;
int userID;
static int Passward = 72051;
ArrayList<Double> MoneyList;
double totalbalance  ,deposit, withdraw;


public Data (String n , int id){
	username = n;
	userID = id;
	MoneyList = new ArrayList<Double>();
}

public Data() {
}

public String toString()
	{
		String s	=	"";

		s	+=	"\nUser Name: "+username+"\tUser ID:  "+userID+"\t"+MoneyList;
		
		return s;
	}

}

