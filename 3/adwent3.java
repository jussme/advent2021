import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class adwent3{
	public static void main(String[] args){
		try{
			File file = new File(args[0]);
			BufferedReader bufferedReader = 
				new BufferedReader(new FileReader(file));

			String line = bufferedReader.readLine().trim();
			List<Integer> onesCounters = new ArrayList<>();
			for(int it = 0; it < line.length(); ++it){
				onesCounters.add(0);
			}
			serviceLine(line, onesCounters);
			int recordCount = 0;
			while(bufferedReader.ready()){
				++recordCount;
				line = bufferedReader.readLine().trim();
				serviceLine(line, onesCounters);
			}

			
			System.out.println("gamma * epsilon = " +
				getPowerConsumption(onesCounters, recordCount));
		} catch (IOException e){
			e.printStackTrace();
			System.exit(1);
		}	
	}

	private static void serviceLine(String line, List<Integer> onesCounters){
		for(int it = 0; it < line.length(); ++it){
			if(line.charAt(line.length() - 1 - it) == '1'){
				onesCounters.set(it, onesCounters.get(it).intValue() + 1);
			}
		}
	}

	private static int getPowerConsumption(List<Integer> onesCounters, int recordCount) {
		short gamma = 0;
		short epsilon = 0;
		for(int pos = 0; pos < onesCounters.size(); ++pos){
			if(onesCounters.get(pos) > recordCount/2){
				gamma += 1 << pos;
			} else {
				epsilon += 1 << pos;
			}
		}

		return gamma * epsilon;
	}
}