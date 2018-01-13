import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DicDivide {
	
	String s;
	int len;
	List<List<Integer>> patitions = new ArrayList<List<Integer>>();
	List<Integer> patition = new ArrayList<Integer>();
	List<String> dic = new ArrayList<String>();
	int[][] sDic;
	
	void init() throws Exception{
		FileInputStream inputStream = new FileInputStream("words.txt");  
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str;
        while((str = bufferedReader.readLine()) != null)  
            dic.add(str);  
        
		sDic = new int[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = i; j < len; j++) {
				if(dic.contains(s.substring(i, j+1)))
					sDic[i][j] = 1;
				else sDic[i][j] = 0;
			}
		}
	}
	
	void divide(int start){
		for (int i = start; i < len; i++) {
			if(sDic[start][i] == 1){
				if(i == len-1){
					List<Integer> p = new ArrayList<Integer>();
					p.addAll(patition);
					patitions.add(p);
					patition.remove(patition.size()-1);
				}
				else{ 
					patition.add(i);
					divide(i + 1);
				}
			}else if(i == len-1){
				patition.remove(patition.size()-1);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		DicDivide dicDivide = new DicDivide();
		dicDivide.patition.add(0);
		Scanner scan = new Scanner(System.in);
		dicDivide.s = scan.nextLine();
		dicDivide.len = dicDivide.s.length();
		dicDivide.init();
		dicDivide.divide(0);
		if(dicDivide.patitions.isEmpty()){
			System.out.println("Sorry, there is no such division!");
		}
		else{
			System.out.println("All possible divisions:");
			System.out.println("[");
			for(List<Integer> i : dicDivide.patitions){
				i.add(dicDivide.len-1);
				for (int j = 0; j < i.size()-1; j++) {
					if(j != 0) {
						System.out.print("\"" + dicDivide.s.substring(i.get(j)+1, i.get(j+1)+1) + "\"");
					}else{ 
						System.out.print("  [\"" + dicDivide.s.substring(i.get(j), i.get(j+1)+1) + "\"");
					}
					if(j == i.size()-2)
						System.out.println("]");
					else 
						System.out.print(", ");
					}
				}
			System.out.println("]");
		}
	}
}
