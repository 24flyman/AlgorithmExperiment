import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Palindrome {

	public String s;
	public int len;
	public List<List<Integer>> patitions = new ArrayList<List<Integer>>();
	public List<Integer> patition = new ArrayList<Integer>();
	
	public boolean isPd(int a, int b){
		while(a <= b){
			if(s.charAt(a++) != s.charAt(b--))
				return false;
		}
		return true;
	}
	
	void findPd(int start){
		for (int i = start; i < len; i++) {
			if(isPd(start, i)){
				if(i == len-1){
					List<Integer> p = new ArrayList<Integer>();
					p.addAll(patition);
					patitions.add(p);
					patition.remove(patition.size()-1);
				}
				else{ 
					patition.add(i);
					findPd(i + 1);
				}
			}else if(i == len-1){
				patition.remove(patition.size()-1);
			}
		}
	}
	
	public static void main(String[] args) {
		Palindrome pd = new Palindrome();
		pd.patition.add(0);
		System.out.println("Please enter a string for palindromic string division:");
		Scanner scan = new Scanner(System.in);
		pd.s = scan.nextLine();
		pd.len = pd.s.length();
		pd.findPd(0);
		System.out.println("All possible divisions:");
		System.out.println("[");
		for(List<Integer> i : pd.patitions){
			i.add(pd.len-1);
			for (int j = 0; j < i.size()-1; j++) {
				if(j != 0) {
					System.out.print("\"" + pd.s.substring(i.get(j)+1, i.get(j+1)+1) + "\"");
				}else{ 
					System.out.print("  [\"" + pd.s.substring(i.get(j), i.get(j+1)+1) + "\"");
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

