package lcs;
import java.util.HashSet;
import java.util.Set;


public class LCS {

    public static int[][] memoCheck;

    // -----------------------------------------------
    // our little helper
    // -----------------------------------------------

     private static Set<String> collectSolution (String rStr, int r, String cStr, int c, int[][] memo) {
       //base
     	if (r == 0 || c == 0) {
        Set<String> empty = new HashSet<String>();
			empty.add("");
			return empty;
		  }
      ///recur1
     	if (rStr.charAt(r - 1) == cStr.charAt(c - 1)) {
     		Set<String> result = new HashSet<String>();
    		Set<String> temp = collectSolution(rStr, r - 1, cStr, c - 1, memo);
    		for (String s : temp) {
    			result.add(s + rStr.charAt(r - 1));
    		}
    		return result;
    	}

      ///recur2
     	Set<String> merge = new HashSet<String>();
     	if (memo[r][c - 1] >= memo[c][r - 1]) {
     		merge.addAll(collectSolution(rStr, r, cStr, c - 1, memo));
     	}
      ///recur3
     	if (memo[r - 1][c] >= memo[c - 1][r]){
     		merge.addAll(collectSolution(rStr, r - 1, cStr, c, memo));
     	}
     	  return merge;
     }


    // -----------------------------------------------
    // Bottom-Up LCS
    // -----------------------------------------------

	public static Set<String> bottomUpLCS (String rStr, String cStr) {
        int[][] memoTable = new int[rStr.length() + 1][cStr.length() + 1];
        memoCheck = memoTable;
        for(int r = 1; r < rStr.length() + 1; r++) {
            for(int c = 1; c < cStr.length() + 1; c++) {
                if( rStr.charAt(r-1) == cStr.charAt(c-1) ) {
                   memoTable[r][c] = 1 + memoTable[r-1][c-1];
                }
                else {
                   memoTable[r][c]= Math.max( memoTable[r][c-1] , memoTable[r-1][c] );
                }
            }
        }
        return collectSolution(rStr, rStr.length(), cStr, cStr.length(), memoCheck);
    }
   // -----------------------------------------------
   // Top-Down LCS
   // -----------------------------------------------

	 public static Set<String> topDownLCS (String rStr, String cStr) {
		 int[][] memoTable = new int[rStr.length() + 1][cStr.length() + 1];
	        memoCheck = memoTable;
	     boolean[][] filler = new boolean[rStr.length() + 1][cStr.length() + 1];
	    	lcsRecursiveHelper(rStr, rStr.length(), cStr, cStr.length(), memoCheck, filler);
	    	return collectSolution(rStr, rStr.length(), cStr, cStr.length(), memoCheck);
	    }
	        
	//Top Down Recursive Helper//
	 
	  static int lcsRecursiveHelper(String rStr, int r, String cStr, int c, int[][] memo, boolean[][] filler) {
		 //base
		  if (r == 0 || c == 0) {
				return 0;
				  }
	    	if (filler[r][c] == true) {
	    		return memo[r][c];
	     //recur1
	    	} else if(rStr.charAt(r - 1) == cStr.charAt(c - 1)) {
	    		filler[r][c] = true;
	    		memo[r][c] = 1 + lcsRecursiveHelper(rStr, r-1, cStr, c-1, memo, filler);
	    	} else {
	    		filler[r][c] = true;
	     //recur2&3
	    		memo[r][c] = Math.max(lcsRecursiveHelper(rStr, r-1, cStr, c, memo, filler), 
	    		lcsRecursiveHelper(rStr, r, cStr, c-1, memo, filler));
	    	}
	    		return memo[r][c];
	    }

	}
