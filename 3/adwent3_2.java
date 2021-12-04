import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class adwent3_2{
	private interface Judge{
		public char whatCharToPrune(int index, List<String> survivors);
	}

	public static void main(String[] args){
		try{
			File file = new File(args[0]);
			BufferedReader bufferedReader = 
				new BufferedReader(new FileReader(file));

			List<String> lines = new ArrayList<>();
			while(bufferedReader.ready()){
				lines.add(bufferedReader.readLine().trim());
			}

			//oxygen
			List<String> survivors = new ArrayList<>(lines);
			pruneUntillLastSurvivor((i, surv) -> {
				return howCommonAt(i, '1', surv) >= 0? '0':'1';
			}, survivors);
			int more = strBinaryToInt(survivors.get(0));

			//co2
			survivors = new ArrayList<>(lines);
			pruneUntillLastSurvivor((i, surv) -> {
				return howCommonAt(i, '0', surv) <= 0? '1':'0';
			}, survivors);
			int less = strBinaryToInt(survivors.get(0));
			
			System.out.println("oxygen * co2 = " + more*less);
		} catch (IOException e){
			e.printStackTrace();
			System.exit(1);
		}	
	}

	private static void pruneUntillLastSurvivor(Judge judge, List<String> survivors) {
		int pos = 0;
		do{
			pruneAt(pos, judge.whatCharToPrune(pos, survivors), survivors);
			++pos;
		}while(survivors.size() > 1);
	}

	private static int strBinaryToInt(String numberStr) {
		numberStr = numberStr.trim();
		int val = 0;
		for(int it = 0; it < numberStr.length(); ++it) {
			if(numberStr.charAt(it) == '1'){
				val += 1 << numberStr.length() - 1 - it;
			}
		}
		return val;
	}

	private static void pruneAt(int index, char bit, List<String> lines){
		for(int it = 0; it < lines.size(); ++it){
			if(lines.get(it).charAt(index) != bit){
				lines.remove(it);
				--it;
			}
		}
	}

	private static int howCommonAt(int index, char bit, List<String> lines){
		int bitCount = countBitsAt(index, bit, lines);
		float middlePoint = lines.size()/(float)2;
		if(bitCount > middlePoint)
			return 1;
		else
			if(bitCount < middlePoint)
				return -1;
			else
				return 0;
	}

	private static int countBitsAt(int index, char bit, List<String> lines) {
		int nOfBits = 0;
		for(String line : lines) {
			if(line.charAt(index) == bit){
				++nOfBits;
			}
		}
		return nOfBits;
	}
}