import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Sheet {
  private ArrayList<ArrayList<Cell>> data;
  private int[] rows;
  private int[] cols;

  public Sheet(String filename) {
    ArrayList<ArrayList<Cell>> data = new ArrayList<ArrayList<Cell>>();
    ArrayList<String> lines = new ArrayList<String>();
    try {
      File csv = new File(filename);
      Scanner in = new Scanner(csv);
      while(in.hasNext()) {
        String line = in.next();
        lines.add(line);
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      e.printStackTrace();
      System.exit(1);
    }

    for (int i = 0; i < lines.size(); i++) {
      String[] entries = lines.get(i).split(",");
      data.add(new ArrayList<Cell>());
      for (int j = 0; j < entries.length; j++) {
        Cell<String> newCell = new Cell(entries[j]);
        data.get(i).add(newCell);
      }
    }
  }

  //Returns the cell at the row and col given
  public Cell get(int row, int col) {
    return data.get(row).get(col);
  }

  //Returns String contains the contents of the row at the index given
  public String getRow(int index) {
    String ans = "";
    for (int i = 0; i < data.get(index).size(); i++) {
      ans = ans + data.get(index).get(i).getValue() + "  ";
    }
    return ans;
  }
}
