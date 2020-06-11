package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root;
    private final ConcurrentLinkedDeque<Entry<String>> availableChildren = new ConcurrentLinkedDeque<>();
    private final List<Entry<String>> noAvailableChildren = new ArrayList<>();
    private int size = 0;

    public CustomTree() {
        this.root = new Entry<>("");
        availableChildren.offerLast(root);
    }

    static class Entry<T> implements Serializable {

        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }

        private boolean isBlocked() {
            return leftChild == null && rightChild == null && !isAvailableToAddChildren();
        }

        private void unblock(){
            availableToAddRightChildren = true;
            availableToAddLeftChildren = true;
        }

        private void addChild(Entry<T> child) {
            if (leftChild == null) {
                leftChild = child;
                availableToAddLeftChildren = false;
            } else {
                rightChild = child;
                availableToAddRightChildren = false;
            }
        }
    }

    public String getParent(String s) {
        for (Entry<String> entry : availableChildren) {
            Entry<String> next = entry;
            while (next != null) {
                if (next.elementName.equals(s)) {
                    return next.parent.elementName;
                }
                next = next.parent;
            }
        }
        return null;
    }

    @Override
    public boolean add(String s) {
        return addChild(s, Objects.requireNonNull(availableChildren.peekFirst()));
    }

    private boolean addChild(String s, Entry<String> parent) {
        Entry<String> child = new Entry<>(s);
        child.parent = parent;

        parent.addChild(child);

        if (!parent.isAvailableToAddChildren()) {
            availableChildren.pollFirst();
        }
        size++;
        return availableChildren.offerLast(child);
    }

    @Override
    public boolean remove(Object o) {
        if (o.getClass() != String.class) {
            throw new UnsupportedOperationException();
        }
        String s = (String) o;
        Entry<String> removedChild = null;
        Set<Entry<String>> removedChildren = new HashSet<>();
        List<Entry<String>> removedAvailable = new ArrayList<>();

        for (Entry<String> entry : availableChildren) {
            Entry<String> next = entry;
            Set<Entry<String>> temp = new HashSet<>();
            while (next != null) {
                temp.add(next);
                if (next.elementName.equals(s)) {
                    removedChild = next;
                    removedChildren.addAll(temp);
                    removedAvailable.add(entry);
                    break;
                }
                next = next.parent;
            }
        }
        if (removedChild == null) {
            return false;
        }
        size -= removedChildren.size();

        removeChild(removedChild.parent, s);

        checkIsBlockingChild(removedChild.parent);

        availableChildren.removeAll(removedAvailable);

        needUnblockTree();
        return true;
    }

    private void removeChild(Entry<String> parent, String removedChildName){
        if (parent.leftChild == null){
            parent.rightChild = null;
        } else if (parent.rightChild == null){
            parent.leftChild = null;
        } else if (parent.leftChild.elementName.equals(removedChildName)) {
            parent.leftChild = null;
        } else {
            parent.rightChild = null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    private void needUnblockTree(){
        if (availableChildren.isEmpty() && !noAvailableChildren.isEmpty()){
            noAvailableChildren.forEach(child -> {
                child.unblock();
                availableChildren.offerLast(child);
            });
            noAvailableChildren.clear();
        }
    }

    private void checkIsBlockingChild(Entry<String> child){
        if (child.isBlocked()) {
            noAvailableChildren.add(child);
        }
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
