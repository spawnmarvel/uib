package oblig1.Precipitation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;

import oblig1.myStack.MyStack;




public class LoadData {


	private double[][] tab; 
	private PrecipitationDay[] preDay;

	private int nextDay;

	public LoadData(){
		preDay = new PrecipitationDay[366];
		nextDay = 0;
	}

	public PrecipitationDay getFirstDay(){
		nextDay = 1;
		return preDay[nextDay-1];
	}

	public PrecipitationDay getNextDay(){
		nextDay++;
		return preDay[nextDay-1];
	}

	public boolean isNextDay(){
		if (preDay[nextDay] != null) return true;
		else return false;
	}


	// Read the data file
	public void readFile() {

		BufferedReader br = null;

		try {

			br = new BufferedReader(new FileReader("Bergen2010.csv"));
			String line = null;


			tab = new double[13][31];
			for(int i = 0; i < 13; i++){
				for(int j = 0; j < 31; j++){
					tab[i][j] = -1;
				}
			}


			String[] month = (br.readLine()).split(":");
			int dag = 0;
			while ((line = br.readLine()) != null) {
				dag++;
				String[] values = line.split(":");


				int j = 0;
				for (String str : values) {
					j++;
					try{  double aDouble = Double.parseDouble(str);
					tab[j-1][dag-1] = aDouble;
					}catch(Exception ex){}
				}
			}

			int k = 0;
			for( int i = 0; i < 12; i++) {

				for ( int j = 0; j< 31; j++){

					if ( tab[i+1][j] != -1 ) {
						preDay[k] = new PrecipitationDay((int)tab[0][j], month[i+1], tab[i+1][j]);
						k++;
					}
				}

			}



		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		finally {
			try {
				if (br != null)
					br.close();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	

} 
