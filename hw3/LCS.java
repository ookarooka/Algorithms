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
    		Set<String> continuing = collectSolution(rStr, r - 1, cStr, c - 1, memo);
    		Set<String> result = new HashSet<String>();
    		for (String s : continuing) {
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
		 boolean[][] haveVisited = new boolean[rStr.length() + 1][cStr.length() + 1];
	    	memoCheck = new int[rStr.length() + 1][cStr.length() + 1];
	    	topDownTableFill(memoCheck, haveVisited,rStr, rStr.length(), cStr, cStr.length());
	    	return collectSolution(rStr, rStr.length(), cStr, cStr.length(), memoCheck);
	    }
	        
	    /**
	     * Fills the memoization table using top-down dynamic programming
	     * @param rStr The String found along the table's rows
	     * @param cStr The String found along the table's cols
	     * @param visited ArrayList of Strings for which the sub-solution has already been found (stored as "rStr|cStr")
	     * @return 2D array containing the memoized values
	     */
	 
	  public static int topDownTableFill(int[][] table, boolean[][] haveVisited, String rStr, int row, String cStr, int column) {
	    	if(row == 0 || column == 0) {
	    		return 0;
	    	}
	    	if(haveVisited[row][column] == true) {
	    		return table[row][column];
	    	} else if(rStr.charAt(row - 1) == cStr.charAt(column - 1)) {
	    		haveVisited[row][column] = true;
	    		table[row][column] = 1 + topDownTableFill(table, haveVisited, rStr, row -1, cStr, column -1);
	    	} else {
	    		haveVisited[row][column] = true;
	    		table[row][column] = Math.max(topDownTableFill(table, haveVisited, rStr, row -1, cStr, column), topDownTableFill(table, haveVisited, rStr, row, cStr, column -1));
	    	}
	    		return table[row][column];
	    }

	}
