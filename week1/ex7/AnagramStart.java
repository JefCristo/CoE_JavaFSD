package week1.ex7;

import java.util.*;

class AnagramStart {
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s.length() < p.length()) return ans;

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        for (char c : p.toCharArray()) {
            pCount[c - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
            if (i >= p.length()) {
                sCount[s.charAt(i - p.length()) - 'a']--;
            }

            if(isEqual(pCount,sCount)){
                ans.add(i - p.length() +1);
            }
        }
        return ans;
    }


    public static boolean isEqual(int[] arr1 , int[] arr2){
        for(int i =0 ; i<arr1.length;i++){
            if(arr1[i] != arr2[i]) return false;

        }

        return true;
    }
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println("Anagram indices: " + findAnagrams(s, p));
    }
}
