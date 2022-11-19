import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Invisible table of Strings which can be filtered by columns
 *
 * @author Niklaus Haenggi
 * @version 0.1
 */
public class Table {

    private final int COLUMNS;

    private ArrayList<String[]> rows;
    private String[] title;
    private ArrayList<String[]> filterdRows;
    private ArrayList<String> filters = new ArrayList<>();
  //  String[] filters;


    /**
     * Constructor
     *  * @param  COLUMNS number of table columns (this can't be changed)
     */
    public Table(int COLUMNS) {
        this.COLUMNS = COLUMNS;
        rows = new ArrayList<>();
        filterdRows = new ArrayList<>();

        for (int i = 0; i < COLUMNS; i++){
            filters.add(null);
        }
    }

    /**
     * Constructor
     * @param  COLUMNS number of table columns (this can't be changed later)
     * @param  title title of the columns as String array, this musst match to the number of columns
     */
    public Table(int COLUMNS, String[] title) {
        this.COLUMNS = COLUMNS;
        rows = new ArrayList<>();
        filterdRows = new ArrayList<>();

        for (int i = 0; i < COLUMNS; i++){
            filters.add(null);
        }
        if (title.length!=COLUMNS){
            System.out.println("Title length does not match to the number of columns");
        } else {
            this.title = title;

        }
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


        List<String[]> filterList = filterdRows.stream().filter(row -> row[column].equals(filter)).toList();
        ArrayList<String[]> newList = new ArrayList<>(filterList);
        filterdRows = newList;
        System.out.println(filters);
        filters.set(column, filter);
        return newList;
    }


    private void clearFilter(){
        filterdRows = rows;
    }

    void clearAllFilters(){
        filterdRows = rows;
        for (int i = 0; i > COLUMNS; i++){
            filters.add(null);
        }
    }

    public void removeFilter(int column){
        filters.set(column, null);
        clearFilter();
        for (int i = 0; i < filters.size(); i++){
            //  filters[i] = null;
        //    System.out.println(filters.get(i));
            if (filters.get(i) != null){
                addFilter(i, filters.get(i));
            }
        }

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

    ArrayList<String> getColumn(String columnTitle) {
        if (title != null & Arrays.asList(title).contains(columnTitle)){
            return getColumn(Arrays.asList(title).indexOf(columnTitle));
    }
       else {
           return null;
        }
    }
        ArrayList<String> getColumn(int column){
            ArrayList<String> col = new ArrayList<>();
            for (String[] filterdRow : filterdRows) {
                col.add(filterdRow[column]);
            }
            return col;
        }



    }


