package com.javarush.task.task18.task1815;

import java.util.List;

/* 
Таблица
*/

public class Solution {
    public class TableInterfaceWrapper implements TableInterface{
        private TableInterface instance;

        public TableInterfaceWrapper(TableInterface instance) {
            this.instance = instance;
        }

        @Override
        public void setModel(List rows) {
            System.out.println(rows.size());
            instance.setModel(rows);
        }

        @Override
        public String getHeaderText() {
            return instance.getHeaderText().toUpperCase();
        }

        @Override
        public void setHeaderText(String newHeaderText) {
            instance.setHeaderText(newHeaderText);
        }
    }

    public interface TableInterface {
        void setModel(List rows);

        String getHeaderText();

        void setHeaderText(String newHeaderText);
    }

    public static void main(String[] args) {
    }
}