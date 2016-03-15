
public class Cracker {
	public static int[] counter = new int[26];
	public static int e = 'E' - 65;
	
	public static void main (String[] args) throws Exception{
		//String text = args[0];
		String text = "uthivtbpjtgixcstgtgstchitwisxtudgbpjhatwbvtqgpcci";
		for(int i = 0; i < text.length(); i++){
			char c = text.charAt(i);
			c = Character.toUpperCase(c);
			if( c < 65 || c > 90) throw new Exception("Illegal characters");
			counter[c - 65]++;
		}
		int most = 0;
		for(int i = 0; i < counter.length; i++){
			if(counter[i] > counter[most]){
				most = i;
			}
		}
		int key = (most - e + 26)%26;
		String cleartext = "";
		for(int i = 0; i < text.length(); i ++){
			char c = text.charAt(i);
			c = Character.toUpperCase(c);
			int ch = c - 65;
			ch = (ch - key + 26)%26;
			ch = ch + 65;
			cleartext += (char)ch;
		}
		System.out.println(key);
		System.out.println(cleartext);
	}

}
