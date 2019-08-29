import java.io.*;
import java.util.*;

public class Go_with_the_Flow {
	static int water = 0;
	
	static int before(Hashtable<Integer, Integer> x, int y){
		int max = 0;
		for(int i=-1;i<2;i++)
			if(x.get(y+i)>max)max= x.get(y+i);
		return max+1;
	}
	
	static Hashtable<Integer, Integer> clean(Hashtable<Integer,Integer> x){
		for(int j=0;j<=water;j++)	
			x.put(j, 0);
		return x;
	}
	
	static int flow(ArrayList<StringTokenizer> x){
		Hashtable<Integer, Integer> river  = new Hashtable<Integer, Integer>();
		Hashtable<Integer, Integer> stream  = new Hashtable<Integer, Integer>();
		int most = 0;
		river = (Hashtable<Integer, Integer>) clean(river).clone();
		stream = (Hashtable<Integer, Integer>) clean(stream).clone();
		for(int i=0;i<x.size();i++){
			int pos = 0;
			while(x.get(i).countTokens()>1){
				pos += (x.get(i).nextToken()).length()+1;
				int fish = 1;
				if(i!=0)
					fish = before(river,pos);
				stream.put(pos, fish);
				if(fish>most)most=fish;
			}river = (Hashtable<Integer, Integer>) stream.clone();
			stream = (Hashtable<Integer, Integer>) clean(stream).clone();
		}
		return most;
	}
	
	static ArrayList<StringTokenizer> stone(StringTokenizer x){
		ArrayList<StringTokenizer> low = new ArrayList<StringTokenizer>();
		int ln = x.toString().length();
		String high = x.nextToken();
		while(x.countTokens()>0){
			String o = x.nextToken();
			if((high+" "+o).length()>water){
				low.add(new StringTokenizer(high));
				high = "";
			}
			if(high.length()!=0)high+=" "+o;
			else high+=o;
		}
		low.add(new StringTokenizer(high));
		return low;
	}
	
	public static void main(String[] args) throws IOException{
		Scanner key = new Scanner(System.in);
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		int loop = key.nextInt();
		String ok = stdin.readLine();
		StringTokenizer x = new StringTokenizer(ok);
		int tmp = 0;
		String Answer = "0 0";
		for(int i=2;i<ok.length()/2;i++){
			water = i;
			int o = flow((stone(new StringTokenizer(ok))));
			if(o>tmp){
				tmp = o;
				Answer=Integer.toString(water)+" "+Integer.toString(o);
			}
		}
		System.out.println(Answer);
	}
}
