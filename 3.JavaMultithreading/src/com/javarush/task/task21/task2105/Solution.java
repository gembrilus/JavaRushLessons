package com.javarush.task.task21.task2105;

import java.util.HashSet;
import java.util.Set;

/* 
Исправить ошибку. Сравнение объектов
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object o) {
        if(o == this) return true;
        if(o == null || this.getClass() != o.getClass()) return false;
        if (!(o instanceof Solution))
            return false;
        Solution n = (Solution) o;
        if (first != null ? !n.first.equals(first) : n.first != null) return false;
        return last != null ? n.last.equals(last) : n.last == null;
    }

    @Override
    public int hashCode() {
        int res = first != null ? first.hashCode() : 0;
        res = 42 * res + (last != null ? last.hashCode() : 0);
        return res;
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Mickey", "Mouse"));
        System.out.println(s.contains(new Solution("Mickey", "Mouse")));
    }
}
