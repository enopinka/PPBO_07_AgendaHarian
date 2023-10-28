// Nama : Retno Pinka Pratiwi
// NIM  : L0122137

/*File yang berisikan fungsi-fungsi logic */

//import packages yang digunakan
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Logic {

  // fungsi untuk membuat file baru
  public static void createFile(String fileName, String time, String content) {
    try {
      File file = new File(fileName);
      if (file.createNewFile()) {
        // Ketika file yang dibuat belum pernah dibuat sebelumnya
        System.out.println("File berhasil dibuat dengan nama " + file.getName());
      } else {
        // Ketika file yang dibuat pernah dibuat, maka agenda akan ditambahkan di file
        // yang telah ada
        System.out.println("File telah dibuat sebelumnya. Agenda akan ditambahkan ke file tersebut");
      }
      // memanggil writefile() untuk menulis ke dalam file
      writeFile(fileName, time, content);
    } catch (IOException e) {
      System.out.println("Terjadi eror: ");
      e.printStackTrace();
    }
  }

  // fungsi untuk menulis ke dalam file
  public static void writeFile(String fileName, String time, String content) {
    // jika file sudah ada isinya, maka akan ditambahkan di bawahnya
    try {
      FileWriter writer = new FileWriter(fileName, true);
      // format penulisan file yang digunakan sebagai pemisah
      writer.write(time + "/" + content + "/" + "\n");
      System.out.println("Agenda baru berhasil ditambahkan");
      writer.close();
    } catch (IOException e) {
      System.out.println("Terjadi eror: ");
      e.printStackTrace();
    }

  }

  // fungsi untuk membaca sebuah file
  public static void readFile(String fileName) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      System.out.println("------------------------");
      System.out.printf("%-7s | %s\n", "Waktu", "Agenda");
      System.out.println("------------------------");
      while (true) {
        // membaca setiap baris file hingga null ditemukan
        String line = reader.readLine();
        if (line == null)
          break;

        // Setiap pemisah ditemukan, string tersebut disimpan ke dalam array
        String[] arrayLine = line.split("[/]");
        // Menampilkan baris yang dibaca dalam bentuk tabel
        for (int i = 0; i < arrayLine.length; i++) {
          if (i % 2 == 0) {
            System.out.printf("%-7s | %s\n", arrayLine[i], arrayLine[i + 1]);
          }
        }
      }
      System.out.println("------------------------");
      reader.close();
    } catch (Exception e) {
      System.out.println("Terjadi Eror");
      e.printStackTrace();
    }
  }
}
