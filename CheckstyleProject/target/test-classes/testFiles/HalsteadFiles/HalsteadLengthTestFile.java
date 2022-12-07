package testFiles.HalsteadFiles;

public class HalsteadLengthTestFile {
	public void Hello(int[] a) {
	    for(int i : a){
	    	a[i] = Goodbye(i,i+1,i+2);
	    }
	}
	
	public int Goodbye(int a, int b, int c) {
		int z = a + b + c;
		return z;
	}
	

}
