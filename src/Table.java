import java.util.ArrayList;
import java.util.List;

public class Table {

    private final int COLUMNS;
    private ArrayList<String[]> rows;
    private String[] title;
    private ArrayList<String[]> filterdRows;
    private ArrayList<Integer> filters;


    public Table(int ROWS) {
        this.COLUMNS = ROWS;
        rows = new ArrayList<>();
        filterdRows = new ArrayList<>();

    }


    public Table(int ROWS, String[] title) {
        this.COLUMNS = ROWS;
        rows = new ArrayList<>();
        filterdRows = new ArrayList<>();

    }



    public boolean addTitle(String ... args){
        if (args.length!=COLUMNS){
            return false;
        } else {
            title = args;
            return true;
        }
    }



    public boolean addRow(String ... args){
        if (args.length!=COLUMNS){
            return false;
        } else {
            rows.add(args);
            filterdRows.clear();
            return true;
        }
    }

    public ArrayList<String[]> addFilter(String columnTitle , String filter) {
        return new ArrayList<>();
    }

    public ArrayList<String[]> addFilterOld(int column , String filter){
        if (filterdRows.isEmpty()){
            filterdRows  = rows;
        }
        ArrayList<String[] > newList = new ArrayList<>();
        for (String[] r : filterdRows){
            if (r[column].equals(filter)){
                System.out.println(r[column] + " = " + filter);
                newList.add(r);
            }
        }
        filterdRows = newList;
        return newList;
    }


    public ArrayList<String[]> addFilter(int column , String filter){
        if (filterdRows.isEmpty()){
            filterdRows  = rows;
        }

      //  ArrayList<String[]> filterList = (ArrayList<String[]>) filterdRows.stream().filter(row -> row[column].equals(filter)).toList();
      //  List<String[]> filterList = filterdRows.stream().filter(row -> row[column].equals(filter)).toList();
        List<String[]> filterList = filterdRows.stream().filter(row -> row[column].equals(filter)).toList();
        ArrayList<String[]> newList = new ArrayList<>(filterList);
        filterdRows = newList;

        return newList;
    }


    public void clearFilter(){
        filterdRows = rows;
    }

    public void printFilteredTable() {
        for (String[] row : filterdRows) {
            String start = "| ";
            for (String value : row) {
                start = start + value + " | ";
            }
            System.out.println(start);
        }

    }
        public void printTable() {
            for (String[] row : rows) {
                String start = "| ";
                for (String value : row) {
                    start = start + value + " | ";
                }
                System.out.println(start);
            }

        }


    }


