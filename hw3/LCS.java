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
        Set<String> nulld = new HashSet<String>();
			nulld.add("");
			return nulld;
		  }
      ///recur1
     	Set<String> value = new HashSet<String>();
     	Set<String> temp = collectSolution(rStr, r-1, cStr, c-1, memo);
     	if (rStr.charAt(r-1) == cStr.charAt(c-1)) {		
    		for (String k : temp) {
    			value.add(k + rStr.charAt(r-1));
    		}
    		return value;
    	}

      ///recur2
     	if (memo[r][c-1] >= memo[c][r-1]) {
     		value.addAll(collectSolution(rStr, r-1, cStr, c, memo));
     	}
      ///recur3
     	if (memo[r - 1][c] >= memo[c - 1][r]){
     		value.addAll(collectSolution(rStr, r, cStr, c-1, memo));
     	}
     	  return value;
     }


    // -----------------------------------------------
    // Bottom-Up LCS
    // -----------------------------------------------

	public static Set<String> bottomUpLCS (String rStr, String cStr) {
        int[][] memo = new int[rStr.length() + 1][cStr.length() + 1];
        memoCheck = memo;
        for(int r = 1; r < rStr.length() + 1; r++) {
            for(int c = 1; c < cStr.length() + 1; c++) {
                if(rStr.charAt(r-1) == cStr.charAt(c-1) ) {
                   memo[r][c] = 1 + memo[r-1][c-1];
                }
                else {
                   memo[r][c]= Math.max(memo[r][c-1] , memo[r-1][c]);
                }
            }
        }
        return collectSolution(rStr, rStr.length(), cStr, cStr.length(), memoCheck);
    }
   // -----------------------------------------------
   // Top-Down LCS
   // -----------------------------------------------

	 public static Set<String> topDownLCS (String rStr, String cStr) {
		 int[][] memo = new int[rStr.length() + 1][cStr.length() + 1];
	        memoCheck = memo;
	    	lcsRecursiveHelper(rStr, rStr.length(), cStr, cStr.length(), memoCheck);
	    	return collectSolution(rStr, rStr.length(), cStr, cStr.length(), memoCheck);
	    }
	        
	//Top Down Recursive Helper//
	 
	  static int lcsRecursiveHelper(String rStr, int r, String cStr, int c, int[][] result) {
		 //base
		  if (r == 0 || c == 0) {
				return 0;
		  }
		//recur1  
		  if (result[r][c] !=0) {
				return result[r][c];
	     //recur2&3
	    	} else if(rStr.charAt(r - 1) == cStr.charAt(c - 1)) {
	    		result[r][c] = 1 + lcsRecursiveHelper(rStr, r-1, cStr, c-1, result);
	    	} else {
	    		result[r][c] = Math.max(lcsRecursiveHelper(rStr, r-1, cStr, c, result),
	    		lcsRecursiveHelper(rStr, r, cStr, c-1, result));
	    	}
	    		return result[r][c];
	    }

	}
