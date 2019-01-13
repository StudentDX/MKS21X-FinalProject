public class Driver {
  public static void main(String[] args) {
    //Cell testing
    /*
    Cell<String> cell1 = new Cell<String>("Hi");
    System.out.println(cell1.getValue());

    Cell<Integer> cell2 = new Cell<Integer>(5);
    System.out.println(cell2.getValue());

    cell2.setValue(6);
    System.out.println(cell2.getValue());
    System.out.println(cell2);

    Cell<Integer> cell3 = new Cell<Integer>(11);
    System.out.println(cell3.getValue());

    System.out.println(cell2.getValue() + cell3.getValue());
    System.out.println(cell2 + " " + cell3);
    */

    String filename = "TestCSV.csv";
    Sheet sheet1 = new Sheet(filename);

		/*
    System.out.println(sheet1.get(0,0));
    System.out.println(sheet1.get(3,3));
    System.out.println(sheet1.getRow(0));
    System.out.println(sheet1.getRow(2));
    System.out.println(sheet1.getRow(5));
    System.out.println(sheet1.getCol(0));
    System.out.println(sheet1.getCol(2));
    System.out.println(sheet1.getCol(5));

    System.out.println("  ");

    System.out.println("Longest entry in column 0: " + sheet1.longestInCol(0));
    System.out.println("Longest entry in column 2: " + sheet1.longestInCol(2));
    */

    System.out.println(sheet1.get());
    System.out.println(sheet1);

		//testing if Integers are stored seperate from Strings
		System.out.println(sheet1.getCell(2,2));
    System.out.println(sheet1.getCell(3,2));
		System.out.println(sheet1.findSum(2,2,3,2)); //should print 95 not 923
		//System.out.println(sheet1.findSum(1,0,2,0)); //should throw error
    //System.out.println(sheet1.findSum(1,0,1,2)); //should throw error

    /*
    System.out.println(" ");
    sheet1.setRow(1, "Hi");
    sheet1.setCol(3, "Hi");
    sheet1.setAll("hi");
    System.out.println(sheet1);
    */

    System.out.println(" ");
    System.out.println("Jumping to cell in row 1 col 2: " + sheet1.jumpTo(1,2));
    System.out.println(sheet1.get());
    System.out.println("Adding cell in row 1 col 3: " + sheet1.select(1,3));
    System.out.println(sheet1.get());
		
    System.out.println(" ");

    String[] newRow1 = new String[] {"Ann", "444555", "100", "1958", "6", "70"};
    sheet1.addRow(newRow1);
    System.out.println(sheet1);

    String[] newRow2 = new String[] {"Ethan", "800000", "16", "2011", "7", "62"};
    sheet1.addRow(3, newRow2);
    System.out.println(sheet1);
  }
}
