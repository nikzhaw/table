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



    /**
     * addTitle
     * adds the titles for the columns
     * @param  titles Strings vor the titles of the columns (the number of Strings has to match the number of columns)
     * @throws RuntimeException if number of arguments doesn't match Columns
     */


    public void addTitle(String ... titles) {
        if (titles.length!=COLUMNS){
            throw new RuntimeException("Wrong number of arguments. The table has " +  COLUMNS + " columns.");
        } else {
            title = titles;
        }
    }


    /**
     * addRow
     * adds a new row to the table
     * @param  args Strings vor the titles of the columns (the number of Strings has to match the number of columns)
     * @throws RuntimeException if number of titles doesn't match Columns
     */

    public void addRow(String ... args){
        if (args.length!=COLUMNS){
            throw new RuntimeException("Wrong number of arguments. The table has " +  COLUMNS + " columns.");
        } else {
            rows.add(args);
            filterdRows.clear();
        }
    }



    /**
     * addFilter
     * @param  column int wich column the filter shoul added
     * @param  filter String for the filtered word in the column
     * @throws RuntimeException if column is bigger than number of columns
     */

    public ArrayList<String[]> addFilter(int column , String filter){
        if (column > COLUMNS){
            throw new RuntimeException("Wrong number of column. The table has only " +  COLUMNS + " columns.");
        }
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



    /**
     * clearAllFilters
     * removes all set filters at once
     */

    void clearAllFilters(){
        filterdRows = rows;
        for (int i = 0; i > COLUMNS; i++){
            filters.add(null);
        }
    }

    /**
     * remove filter
     * removes a filter wich was set before
     * @param  column int for column number from wich the filter should be removed (can't be higher than the number of columns)
     * @throws RuntimeException if column is bigger than number of columns
     */

    public void removeFilter(int column){
        if (column > COLUMNS){
            throw new RuntimeException("Wrong number of column. The table has only " +  COLUMNS + " columns.");
        }
        filters.set(column, null);
        clearFilter();
        for (int i = 0; i < filters.size(); i++){
            if (filters.get(i) != null){
                addFilter(i, filters.get(i));
            }
        }

    }

    /**
     * remove filter
     * removes a filter wich was set before
     * @param  column Strings vor the titles of the columns (Strings has to match the title of columns)
     * @throws RuntimeException if column in not available
     */

    public void removeFilter(String column){
        if (!Arrays.asList(title).contains(column)){
            throw new RuntimeException("There is no column with the name " + column + " in the table.");
        } else {
            removeFilter(Arrays.asList(title).indexOf(column));
        }

    }


    /**
     * getColumn
     * returns all values of a specific column of the table in the filtered condition
     * @param  column int for column number from which should be returned (can't be higher than the number of columns)
     * @throws RuntimeException if column in not available
     * @return ArrayList of the filtered column
     */

    ArrayList<String> getColumn(int column){
        ArrayList<String> col = new ArrayList<>();
        for (String[] filterdRow : filterdRows) {
            col.add(filterdRow[column]);
        }
        return col;
    }


    /**
     * getColumn
     * returns all values of a specific column of the table in the filtered condition
     * @param  columnTitle String for columnTitle from which should be returned (can't be higher than the number of columns)
     * @throws RuntimeException if column in not available
     * @return ArrayList of the filtered column
     */
    ArrayList<String> getColumn(String columnTitle) {
        if (title != null & Arrays.asList(title).contains(columnTitle)){
            return getColumn(Arrays.asList(title).indexOf(columnTitle));
        }
        else {
            return null;
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





    }


