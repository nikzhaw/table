import java.util.*;


/**
 * Invisible table of Strings which can be filtered by columns
 *
 * @author Niklaus Haenggi
 * @version 0.2
 */
public class Table {

    private final int COLUMNS;

    private ArrayList<String[]> rows;
    private String[] title;
 //   private ArrayList<Integer>[] filterdRows;
    private HashMap<String, ArrayList<Integer>>[] filters;
    private ArrayList<String[]> filterdRows;


    /**
     * Constructor
     *  * @param  COLUMNS number of table columns (this can't be changed)
     */
    public Table(int COLUMNS) {
        this.COLUMNS = COLUMNS;
        rows = new ArrayList<>();
        filterdRows = new ArrayList<String[]>();
        filters = new HashMap[COLUMNS];


        for (int i = 0; i < COLUMNS; i++){
            filters[i] = new HashMap<>();
        }
    }



    /**
     * Constructor
     * @param  COLUMNS number of table columns (this can't be changed later)
     * @param  title title of the columns as String array, this musst match to the number of columns
     */
    public Table(int COLUMNS, String[] title) {
        if (title.length!=COLUMNS){
            throw new RuntimeException("Title length does not match to the number of columns");
        } else {
            this.title = title;
            this.COLUMNS = COLUMNS;
            rows = new ArrayList<>();
            filterdRows = new ArrayList<>();
            filters = new HashMap[COLUMNS];
            for (int i = 0; i < COLUMNS; i++){
                filters[i] = new HashMap<>();
            }

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

    public void addFilter(int column , String filter){
        if (column > COLUMNS){
            throw new RuntimeException("Wrong number of column. The table has only " +  COLUMNS + " columns.");
        }
        ArrayList<Integer> newFilter = new ArrayList<>();
        for (int i =0 ; i < rows.size() ; i++) {

            if (rows.get(i)[column].equals(filter)){
                newFilter.add(i);
            }

        }

        filters[column].put(filter, newFilter);

    }


    /**
     * remove filter
     * removes a filter wich was set before
     * @param  column int for column number from wich the filter should be removed (can't be higher than the number of columns)
     * @throws RuntimeException if column is bigger than number of columns
     * @return
     */

    public void removeFilter(int column , String filter) {
        if (column > COLUMNS) {
            throw new RuntimeException("Wrong number of column. The table has only " + COLUMNS + " columns.");
        }
        if (!filters[column].keySet().contains(filter)) {
            throw new RuntimeException("Filter is not set for column " + column);
        } else {
            filters[column].remove(filter);


        }
    }


    /**
     * crateFilteredTable
     * updates the filtered Table according the set filters
     * @return
     */

        public  ArrayList<String[]> crateFilteredTable(){
            HashSet<Integer> allShown = new HashSet<>();
            System.out.println(Arrays.toString(filters));
            for (HashMap<String, ArrayList<Integer>> filter : filters) {
                if (!filter.isEmpty()){
                    System.out.println("test");
                    System.out.println(Arrays.toString(filter.keySet().toArray()));
                Collection<ArrayList<Integer>> columFilters = filter.values();

                    HashSet<Integer> showsRows = new HashSet<>();
                    for (ArrayList<Integer> columFilter : columFilters) {
                        HashSet<Integer> entries = new HashSet<Integer>(columFilter);

                        showsRows.addAll(entries);
                        System.out.println(Arrays.toString(showsRows.toArray()));
                    }
                    if (allShown.isEmpty()){
                        allShown.addAll(showsRows);
                    } else{
                        allShown.retainAll(showsRows);
                        System.out.println(Arrays.toString(allShown.toArray()));
                    }


                }


            }

            System.out.println(filters);


            ArrayList<String[]> filtered = new ArrayList<>();
            for (int i = 0; i < rows.size(); i++) {
                if (allShown.contains(i)) {
                    filtered.add(rows.get(i));

                }
                }

            filterdRows = filtered;
           return filtered;

        }


        /**
         * remove filter
         * removes a filter wich was set before
         * @param  column Strings vor the titles of the columns (Strings has to match the title of columns)
         * @throws RuntimeException if column in not available
         */

        public void removeFilter(String column){


            if (!Arrays.asList(title).contains(column)) {
                throw new RuntimeException("There is no column with the name " + column + " in the table.");
            } else {
                removeFilter(column);
            }

        }


    /**
     * getColumn
     * returns all values of a specific column of the table in the filtered condition
     * @param  column int for column number from which should be returned (can't be higher than the number of columns)
     * @throws RuntimeException if column in not available
     * @return ArrayList of the filtered column
     */

    public ArrayList<String> getColumn(int column) {
        if (column > COLUMNS) {
            throw new RuntimeException("Wrong number of column. The table has only " + COLUMNS + " columns.");
        } else {
            ArrayList<String> col = new ArrayList<>();
            for (String[] filterdRow : filterdRows) {
                col.add(filterdRow[column]);
            }
            return col;
        }
    }


    /**
     * getColumn
     * returns all values of a specific column of the table in the filtered condition
     * @param  columnTitle String for columnTitle from which should be returned (can't be higher than the number of columns)
     * @throws RuntimeException if column in not available
     * @return ArrayList of the filtered column
     */
    ArrayList<String> getColumn(String columnTitle) {
        if (!Arrays.asList(title).contains(columnTitle)) {
            throw new RuntimeException("There is no column with the name " + columnTitle + " in the table.");
        } else {
            if (title != null & Arrays.asList(title).contains(columnTitle)) {
                return getColumn(Arrays.asList(title).indexOf(columnTitle));
            } else {
                return null;
            }
        }
    }





    /**
     * clearAllFilters
     * removes all set filters at once
     */

    void clearAllFilters(){
        filterdRows = rows;
        for (int i = 0; i > COLUMNS; i++){
            filters[i].clear();
        }
    }





    /**
     * getColumnValues
     * returns all values of a specific column of the table in the filtered condition
     * the Values are returned only once even if there are duplicates in the column
     * @param  column int for column number from which should be returned (can't be higher than the number of columns)
     * @throws RuntimeException if column in not available
     * @return ArrayList of the Values from filtered column
     */

    ArrayList<String> getColumnValues(int column) {
        HashSet<String>  entries = new HashSet<String>(getColumn(column));
        ArrayList values = new ArrayList<>(entries);
        return values;
    }



    /**
     * getColumnValues
     * returns all values of a specific column of the table in the filtered condition
     * the Values are returned only once even if there are duplicates in the column
     * @param  columnTitle String for columnTitle from which should be returned (can't be higher than the number of columns)
     * @throws RuntimeException if column in not available
     * @return ArrayList of the Values from filtered column
     */
    ArrayList<String> getColumnEntries(String columnTitle) {
        if (title != null & Arrays.asList(title).contains(columnTitle)) {
            throw new RuntimeException("There is no column with the name " + columnTitle + " in the table.");
        } else {

                HashSet<String> entries = new HashSet<String>(getColumn(Arrays.asList(title).indexOf(columnTitle)));
                ArrayList values = new ArrayList<>(entries);
                return values;

            }
        }









    /**
     * getRowOrigin
     *
     * returns a specific row according the row number of the original table
     * @param  number int for row number (can't be higher than the number of total rows)
     * @throws RuntimeException if row is higher then the number of total rows
     * @return Array of the requested row
     */


    String[] getRowOrigin(int number) {
        if (number > rows.size()) {
            throw new RuntimeException("Requested row is bigger than the number of rows in the table, the table has " + rows.size() + " rows.");
        } else {
            return rows.get(number);
        }
    }



    /**
     * getRowFiltered
     *
     * returns a specific row according the row number of the filtered table
     * @param  number int for row number (can't be higher than the number of total rows)
     * @throws RuntimeException if row is higher then the number of total rows
     * @return Array of the requested row
     */


    String[] getRowFiltered(int number) {
        if (number > filterdRows.size()) {
            throw new RuntimeException("Requested row is bigger than the number of rows in the table, the table has " + filterdRows.size() + " rows.");
        } else {
            return filterdRows.get(number);
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


