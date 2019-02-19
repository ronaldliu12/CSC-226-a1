/**
 *
 * @author Rahnuma Islam Nishat
 * September 19, 2018
 * CSC 226 - Fall 2018
 
	edited by Ronald Liu	V00838627
 */
import java.util.*;
import java.io.*;

public class QuickSelect {
	
	
	public static int findMedian( int[] C ){
			
			int median; 
			
			Arrays.sort(C);
			
			if ( C.length % 2 == 0 ){
				median = (C.length / 2) - 1;
			} else {
				median = C.length / 2;
			}
			
			return C[median];
	}
	
	public static int pickPivot(int[] B){
		
		//group of size 7, number of medians = n/7		

		int p;
		
		//System.out.println( "testing on B.length: " + B.length );
		
		p = medianOfmedians(B);

		return p;
		
	}
	
	public static int medianOfmedians(int[] B){
		
		int median = -1;
		
		if ( B.length > 7 ){
			
			int[] mediansArray = new int[ B.length ]; 
			 
			int[] temp = new int[7];
			
			int m = 0;
					
			for ( int i = 0; i < B.length / 7; i++) {
				
				for ( int j = 0; j < 7 ; j++ ){
					
					temp[j] = B[ (i*7) + j];

				}

				mediansArray[m] = findMedian(temp);
				m++;
			}
			
			if ( B.length % 7 != 0 ){
				for ( int i = 0; i < B.length % 7; i++ ){
					
					mediansArray[m] = B[ B.length - 1 - i ];
					m++;
				}
			}
			
			int[] mediansArray2 = new int[ m ];
			
			if ( m > 0 ){
				for ( int i = 0 ; i < m ; i++ ){
					mediansArray2[i] = mediansArray[i];
				}
			}
			
			//System.out.println( "testing on medianArray size: " + m );
			
			median = medianOfmedians(mediansArray2);
			
		} else {
			
			median = findMedian(B);
			
			//System.out.println( "testing on median: " + B[median] );
			
			return median;
			
		}
		return median;
	}
    
    
    public static int QuickSelect(int[] A, int k) {
        //Write your code here 

		
		//for ( int i = 0; i < A.length ; i++ ) {
			//System.out.print( A[i] + " " );
		//}
		//System.out.println();
		
		if ( k > A.length ) {
			return -1;
		}
		
		if (A.length == 1){
			return A[0];
		}
		
		int p;
		
		p = pickPivot(A);
		
		//System.out.println( "testing on p: " + p );
		
		int[] smallArray = new int[A.length];
		int[] largeArray = new int[A.length];
		
		int L = 0;
		int G = 0;
		int duplicate = 0;

		
		for ( int i = 0 ; i < A.length ; i++ ) {
			
			if ( A[i] < p ){
				smallArray[L] = A[i];
				L++;
			}
			else if ( A[i] == p && duplicate == 1){
				largeArray[G] = A[i];
				G++;
			}	
			else if ( A[i] > p ){
				largeArray[G] = A[i];
				G++;
			}	
			else{
				duplicate = 1;
			}
		}
		
		int[] smallArray2 = new int[L];
		int[] largeArray2 = new int[G];
		
		//System.out.println( "testing on L: " + L );
		if ( L > 0 ){
			for ( int i = 0 ; i < L ; i++ ){
				smallArray2[i] = smallArray[i];
			}
		}
		//System.out.println( "testing on G: " + G );
		if ( G > 0 ){
			for ( int i = 0 ; i < G ; i++ ){
				largeArray2[i] = largeArray[i];
			}
		}
		
		if ( k <= L ){
			
			return QuickSelect( smallArray2, k );
		}
		
		else if ( k == ( L + 1 ) ){
			
			return p;
		}
		
		else {
			return QuickSelect( largeArray2, k - L -1 );
		}
		
    }
	
	
	
    
    public static void main(String[] args) {
        Scanner s;
        int[] array;
        int k;
        if(args.length > 0) {
			try{
				s = new Scanner(new File(args[0]));
				int n = s.nextInt();
				array = new int[n];
				for(int i = 0; i < n; i++){
					array[i] = s.nextInt();
				}
			} catch(java.io.FileNotFoundException e) {
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n", args[0]);
        }
	else {
			s = new Scanner(System.in);
			System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
			int temp = s.nextInt();
			ArrayList<Integer> a = new ArrayList();
			while(temp >= 0) {
				a.add(temp);
				temp=s.nextInt();
			}
			array = new int[a.size()];
			for(int i = 0; i < a.size(); i++) {
			array[i]=a.get(i);
			}
			
			System.out.println("Enter k");
        }
		
        k = s.nextInt();
        System.out.println("The " + k + "th smallest number is the list is "
			   + QuickSelect(array,k));	
    }
}
