public class Main {

    public static void main(String[] args) {
        Table table = new Table(3 );
        table.addTitle( "Row1","Row2","Row3");
        table.addRow("A", "1" ,"D");
        table.addRow("A", "1" ,"D");
        table.addRow("B", "2","C");
        table.addRow("B", "1" ,"C");
        table.addRow("C", "1" ,"A");
        table.addRow("C", "2","A");
        table.addRow("C", "1" ,"A");
        try {
            table.addRow("B", "1" ,"C" , "f");
        } catch (Exception e) {
            e.printStackTrace();
        }


        table.printTable();

        table.printFilters();
        System.out.println("    ");
 //       table.clearFilter();

        table.addFilter(1,"1");

        System.out.println("    ");
        System.out.println("    ");

        table.printFilteredTable();
        table.printFilters();
        System.out.println("    ");

        table.addFilter(2,"D");
        table.addFilter(2,"A");


        table.printFilters();
        System.out.println("    ");

        System.out.println("hallo");
        table.printFilteredTable();
        System.out.println("hallo");
        table.printFilteredTable();


        System.out.println("    ");
        System.out.println("    ");



    //    System.out.println(table.filters);


        table.removeFilter(2);

        System.out.println("    ");
     //   System.out.println(table.filters);
        System.out.println("    ");


        table.printFilteredTable();


        table.clearAllFilters();


        System.out.println("    ");
        System.out.println("    ");

        table.printFilteredTable();


}}

