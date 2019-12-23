package com.CK;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

class RandomizedCollection {
    Map<Integer, Set<Integer>> numToIndex;
    List<Integer> list;
    Random rand;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        numToIndex = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        list.add(val);
        int index = list.size() - 1;
        boolean res = false;
        if (!numToIndex.containsKey(val)) {
            res = true;
            numToIndex.put(val, new HashSet<>());
        }
        numToIndex.get(val).add(index);
        return res;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!numToIndex.containsKey(val)) {
            return false;
        }
        int size = list.size();
        if (list.get(size - 1) == val) {
            list.remove(size - 1);
            numToIndex.get(val).remove(size - 1);
            if (numToIndex.get(val).isEmpty()) {
                numToIndex.remove(val);
            }
            return true;
        }
        int lastIndex = size - 1, lastNum = list.get(lastIndex), valIndex = numToIndex.get(val).iterator().next();
        list.set(valIndex, lastNum);
        list.set(lastIndex, val);
        list.remove(lastIndex);
        numToIndex.get(lastNum).remove(lastIndex);
        numToIndex.get(lastNum).add(valIndex);
        numToIndex.get(val).remove(valIndex);
        if (numToIndex.get(val).isEmpty()) {
            numToIndex.remove(val);
        }
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        int size = list.size();
        if (size == 0)
            return Integer.MIN_VALUE;

        int randomIndex = rand.nextInt(size);
        return list.get(randomIndex);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */