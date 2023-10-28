// Nama : Retno Pinka Pratiwi
// NIM : L0122137

/* Program membuat agenda sederhana
 * dengan menggunakan tanggal sebagai nama file dan waktu
 * untuk menambahkan detail setiap agenda
 * 
 * Program ini memiliki 2 buah file, file utama (file saat ini) yang berisi fungsi-fungsi 
 * yang berhubungan dengan tampilan sedangkan file Logic yang berisi fungsi logika yang digunakan
 * untuk menjalankan program
 */

//import package yang digunakan
import java.io.File;
import java.io.FilenameFilter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PPBO_07_L0122137 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Logic logic = new Logic();

    // menampilkan menu
    while (true) {
      // memanggil fungsi printFileList() untuk menampilkan daftar file
      printFileList();

      // memanggil fungsi printMenu() yang berisi daftar menu
      int option = printMenu(scanner);
      System.out.println(" ");

      // pilihan menu yang dipilih
      if (option == 1) {
        createFile(logic, scanner); // memanggil fungsi untuk membuat file baru
      } else if (option == 2) {
        addNote(scanner); // memanggil fungsi untuk menambahkan agenda di file lama
      } else if (option == 3) {
        readFile(logic, scanner); // memanggil fungsi untuk menampilkan file tertentu
      } else if (option == 4) {
        help(scanner); // memanggil fungsi untuk menampilkan pesan bantuan
      } else {
        break;
      }
    }
    scanner.close();
  }

  // fungsi menampilkan menu yang mengembalikan opsi yang dipilih
  public static int printMenu(Scanner scanner) {
    System.out.println(" ");
    System.out.println("Agenda Harian");
    System.out.println("1. Buat file baru");
    System.out.println("2. Tambah agenda di file yang sudah ada");
    System.out.println("3. Lihat Agenda");
    System.out.println("4. Bantuan");
    System.out.println("5. Keluar");
    System.out.print(">> ");

    int option = scanner.nextInt();
    scanner.nextLine();
    return option;
  }

  // fungsi untuk menampilkan daftar file dengan ekstensi .txt di dalam direktori
  // saat ini
  public static void printFileList() {
    File directory = new File(".");

    // file dengan ekstensi .txt masuk ke dalam array
    File[] files = directory.listFiles(new FilenameFilter() {
      public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(".txt");
      }
    });
    System.out.println("------------------------");
    System.out.printf("%-4s | %s\n", "No.", "Nama File");
    System.out.println("------------------------");

    // menampillkan daftar file yang disimpan di dalam array
    if (files.length != 0) {
      for (int i = 0; i < files.length; i++) {
        System.out.printf("%-4d | %s\n", i + 1, files[i].getName());
      }
    } else {
      System.out.println("     tidak ada file");
    }
    System.out.println("------------------------");
  }

  // fungsi untuk memvalidasi input waktu yang ditentukan yang mengembalikan nilai
  // boolean
  public static boolean checkTimePattern(String time) {
    // fungsi waktu yang benar adalah hh:mm
    Pattern pattern = Pattern.compile("([01][1-9]|2[0-4]):([0-5][0-9])");
    Matcher matcher = pattern.matcher(time);
    boolean result = matcher.find();
    return result;
  }

  public static boolean checkDatePattern(String date) {
    // fungsi waktu yang benar adalah dd-MM-yyyy
    Pattern pattern = Pattern.compile("(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})");
    Matcher matcher = pattern.matcher(date);
    boolean result = matcher.find();
    return result;
  }

  // fungsi tambahan agar dapat memasukkan <enter> sebelum program dilanjutkan
  public static void EntertoNext(Scanner scanner) {
    System.out.println(" ");
    System.out.println("Tekan <enter> untuk melanjutkan");
    scanner.nextLine();
  }

  // fungsi membuat file baru
  public static void createFile(Logic logic, Scanner scanner) {
    // untuk mendapatkan tanggal saat ini
    LocalDate date = LocalDate.now();
    // memformat tanggal supaya seperti yang diinginkan, yaitu dd-MM-yyyy
    String dateFormatter = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    // file yang dibuat menggunakan format nama file tanggal saat ini
    System.out.println("Tanggal saat ini: " + dateFormatter);
    String fileName = dateFormatter + ".txt";

    // memasukkan waktu ke dalam agenda
    String time;
    while (true) {
      System.out.println("Masukkan Waktu");
      System.out.print(">> ");
      time = scanner.nextLine();
      // cek dengan memanggil fungsi checkTimePattern apakah sesuai format atau tidak
      if (checkTimePattern(time)) {
        // ketika input yang dimasukkan sesuai pattern, maka keluar dari perulangan
        break;
      }
      System.out.println("Input waktu yang Anda masukkan salah");
    }
    // memasukkan agenda
    System.out.println("Masukkan Agenda Anda");
    System.out.print(">> ");
    String content = scanner.nextLine();

    // memanggil fungsi yang terdapat pada file logic untuk dilanjutkan programnya
    // dengan 3 buah argumen, yaitu namafile, waktu, dan isi agenda
    Logic.createFile(fileName, time, content);
    EntertoNext(scanner);

  }

  // menambah catatan di file yang telah ada
  public static void addNote(Scanner scanner) {
    // memasukkan tanggal file dibuat
    String date;
    while (true) {
      System.out.println("Masukkan tanggal file dibuat");
      System.out.print(">> ");
      date = scanner.nextLine();
      // cek dengan memanggil fungsi checkTimePattern apakah sesuai format atau tidak
      if (checkDatePattern(date)) {
        // ketika input yang dimasukkan sesuai pattern, maka keluar dari perulangan
        break;
      }
      // ketika input yang dimasukkan tidak sesuai
      System.out.println("Input waktu yang Anda masukkan salah");
    }

    String findFileName = date;
    // memasukkan waktu ke dalam agenda
    String time;
    while (true) {
      System.out.println("Masukkan Waktu");
      System.out.print(">> ");
      time = scanner.nextLine();
      // cek dengan memanggil fungsi checkTimePattern apakah sesuai format atau tidak
      if (checkTimePattern(time)) {
        break;
      }
      System.out.println("Input waktu yang Anda masukkan salah");
    }

    // memasukkan agenda
    System.out.println("Masukkan Agenda Anda");
    System.out.print(">> ");
    String content = scanner.nextLine();

    // memanggil fungsi yang terdapat pada file logic untuk dilanjutkan programnya
    // dengan 3 buah argumen, yaitu nama file yang dicari, waktu, dan isi agenda
    Logic.writeFile(findFileName + ".txt", time, content);
    EntertoNext(scanner);
  }

  // membaca file yang dibuat
  public static void readFile(Logic logic, Scanner scanner) {

    // memasukkan tanggal file sebagai nama dari file yang ingin dilihat
    System.out.println("Masukkan tanggal file dibuat");
    System.out.print(">> ");
    String findFileName = scanner.nextLine();

    // memanggil fungsi yang terdapat pada file logic untuk dilanjutkan programnya
    // dengan sebuah argumen, yaitu nama file yang dicari
    Logic.readFile(findFileName + ".txt");
    EntertoNext(scanner);
  }

  // fungsi yang berisi panduan singkat mengenai program ini
  public static void help(Scanner scanner) {
    System.out.println("[HALAMAN PANDUAN]");
    System.out.printf(
        "\n[TENTANG PROGAM]\nProgram ini adalah program menulis agenda sederhana dimana pengguna dapat menambahkan\nagenda harian di hari dan waktu tertentu.\n\n");
    System.out.printf("[PANDUAN PENULISAN]\n");
    System.out.printf(
        "- Penulisan Tanggal\n  untuk memasukkan tanggal, format yang dimasukkan adalah dd-MM-yyyy.\n  Beberapa fungsi memerlukan input tanggal diantaranya adalah menambahkan agenda di file \n  dan melihat daftar agenda sebuah file.\n");
    System.out.printf(
        "- Penulisan Waktu\n  untuk memasukkan waktu, format jam yang dimasukkan adalah hh:mm. \n  Beberapa fungsi yang memerlukan input waktu diantaranya adalah \n  buat file baru dan menambahkan agenda di file yang telah ada.\n\n");
    EntertoNext(scanner);
  }
}
