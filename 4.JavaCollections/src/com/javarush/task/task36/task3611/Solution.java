package com.javarush.task.task36.task3611;

import java.util.Set;
import java.util.TreeSet;

/* 
Сколько у человека потенциальных друзей?
*/

public class Solution {
    private boolean[][] humanRelationships;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.humanRelationships = generateRelationships();

        Set<Integer> allFriendsAndPotentialFriends = solution.getAllFriendsAndPotentialFriends(4, 2);
        System.out.println(allFriendsAndPotentialFriends);                              // Expected: [0, 1, 2, 3, 5, 7]
        Set<Integer> potentialFriends = solution.removeFriendsFromSet(allFriendsAndPotentialFriends, 4);
        System.out.println(potentialFriends);                                           // Expected: [2, 5, 7]

        Set<Integer> allFriendsAndPotentialFriends2 = solution.getAllFriendsAndPotentialFriends(0, 2);
        System.out.println(allFriendsAndPotentialFriends2);                              // Expected: [1, 2, 3, 4, 5, 6]
        Set<Integer> potentialFriends2 = solution.removeFriendsFromSet(allFriendsAndPotentialFriends2, 0);
        System.out.println(potentialFriends2);                                           // Expected: [2, 3, 6]

        Set<Integer> allFriendsAndPotentialFriends3 = solution.getAllFriendsAndPotentialFriends(7, 2);
        System.out.println(allFriendsAndPotentialFriends3);                              // Expected: [3, 4]
        Set<Integer> potentialFriends3 = solution.removeFriendsFromSet(allFriendsAndPotentialFriends3, 2);
        System.out.println(potentialFriends3);                                           // Expected: [3, 4]

    }

    public Set<Integer> getAllFriendsAndPotentialFriends(int index, int deep) {
        TreeSet<Integer> result = new TreeSet<>();

        if (deep == 0) {
            return result;
        }
        for (int i = index+1; i < humanRelationships.length; i++){
            if (humanRelationships[i][index]){
                result.add(i);
                result.addAll(getAllFriendsAndPotentialFriends(i, deep-1));
            }
        }

        for (int j = 0; j < index; j++) {
            if (humanRelationships[index][j]) {
                result.add(j);
                result.addAll(getAllFriendsAndPotentialFriends(j, deep-1));
            }
        }
        result.remove(index);
        return result;
    }

    // Remove from the set the people with whom you already have a relationship
    public Set<Integer> removeFriendsFromSet(Set<Integer> set, int index) {
        for (int i = 0; i < humanRelationships.length; i++) {
            if ((i < index) && (index < humanRelationships.length) && humanRelationships[index][i]) {
                set.remove(i);
            } else if ((i > index) && humanRelationships[i][index]) {
                set.remove(i);
            }
        }
        return set;
    }

    // Return test data
    private static boolean[][] generateRelationships() {
        return new boolean[][]{
                {true},                                                                 //0
                {true, true},                                                           //1
                {false, true, true},                                                    //2
                {false, false, false, true},                                            //3
                {true, true, false, true, true},                                        //4
                {true, false, true, false, false, true},                                //5
                {false, false, false, false, false, true, true},                        //6
                {false, false, false, true, false, false, false, true}                  //7
        };
    }
}