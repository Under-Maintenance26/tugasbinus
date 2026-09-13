/*
 * TUGAS KELOMPOK 1
 * Mata Kuliah: COSC6025 - Data Structures and Algorithm Analysis
 *
 * Anggota Kelompok:
 * 1. Krisna Setiyawan                  - 2902829641
 * 2. Azril Tsani                       - 2902807312
 * 3. Bambang Priyanto                  - 2902819073
 * 4. Haiefa Agasy Aprilya Sari         - 2602311275
 * 5. Muhammad Ramadan Abdul Khalik     - 3002933264
 * Program Studi: Computer Science
 */

import java.util.Scanner;

// Class Lagu menyimpan data dari satu lagu.
class Lagu {
    private String judul;
    private String artis;
    private double durasi;

    public Lagu(String judul, String artis, double durasi) {
        this.judul = judul;
        this.artis = artis;
        this.durasi = durasi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getArtis() {
        return artis;
    }

    public void setArtis(String artis) {
        this.artis = artis;
    }

    public double getDurasi() {
        return durasi;
    }

    public void setDurasi(double durasi) {
        this.durasi = durasi;
    }

    // Method ini menampilkan seluruh informasi lagu.
    public void tampilkanInfo() {
        int menit = (int) durasi / 60;
        int detik = (int) durasi % 60;

        System.out.println("Judul  : " + judul);
        System.out.println("Artis  : " + artis);
        System.out.println("Durasi : " + menit + " menit " + detik + " detik");
    }
}

// Class Playlist mengelola kumpulan objek Lagu di dalam array.
class Playlist {
    private Lagu[] daftarLagu;
    private int jumlahLagu;

    public Playlist(int kapasitas) {
        daftarLagu = new Lagu[kapasitas];
        jumlahLagu = 0;
    }

    // Lagu dimasukkan ke posisi array yang masih kosong.
    public boolean tambahLagu(Lagu lagu) {
        if (jumlahLagu >= daftarLagu.length) {
            return false;
        }

        daftarLagu[jumlahLagu] = lagu;
        jumlahLagu++;
        return true;
    }

    // Perulangan hanya dilakukan sampai jumlah lagu yang sudah terisi.
    public void tampilkanDaftarLagu() {
        if (jumlahLagu == 0) {
            System.out.println("Playlist masih kosong.");
            return;
        }

        System.out.println("\nDaftar Lagu");
        System.out.println("------------------------------");
        for (int i = 0; i < jumlahLagu; i++) {
            System.out.println("Lagu ke-" + (i + 1));
            daftarLagu[i].tampilkanInfo();
            System.out.println("------------------------------");
        }
    }

    // Pencarian dilakukan dari awal array dan tidak membedakan huruf besar atau kecil.
    public Lagu cariLagu(String judul) {
        for (int i = 0; i < jumlahLagu; i++) {
            if (daftarLagu[i].getJudul().equalsIgnoreCase(judul)) {
                return daftarLagu[i];
            }
        }
        return null;
    }

    // Rata-rata diperoleh dari total durasi dibagi jumlah lagu.
    public double hitungRataRataDurasi() {
        if (jumlahLagu == 0) {
            return 0;
        }

        double totalDurasi = 0;
        for (int i = 0; i < jumlahLagu; i++) {
            totalDurasi += daftarLagu[i].getDurasi();
        }
        return totalDurasi / jumlahLagu;
    }
}

// User menjadi parent class untuk Admin dan Member.
abstract class User {
    private String nama;
    protected Playlist playlist;

    public User(String nama, Playlist playlist) {
        this.nama = nama;
        this.playlist = playlist;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // Method ini akan dioverride oleh setiap child class.
    public abstract void tampilkanAkses();
}

// Admin mewarisi atribut dan method dari User.
class Admin extends User {
    public Admin(String nama, Playlist playlist) {
        super(nama, playlist);
    }

    // Isi method berbeda dengan Member sehingga menunjukkan polymorphism.
    @Override
    public void tampilkanAkses() {
        System.out.println("\nLogin sebagai Admin: " + getNama());
        System.out.println("Akses: melihat daftar lagu dan menambahkan lagu.");
    }

    // Admin membuat objek Lagu lalu memasukkannya ke playlist.
    public void tambahLagu(Lagu lagu) {
        if (playlist.tambahLagu(lagu)) {
            System.out.println("Lagu '" + lagu.getJudul() + "' berhasil ditambahkan.");
        } else {
            System.out.println("Playlist sudah penuh. Lagu tidak dapat ditambahkan.");
        }
    }

    public void lihatDaftarLagu() {
        playlist.tampilkanDaftarLagu();
    }
}

// Member juga merupakan turunan dari User, tetapi hak aksesnya berbeda.
class Member extends User {
    public Member(String nama, Playlist playlist) {
        super(nama, playlist);
    }

    // Method yang sama menghasilkan tampilan akses sesuai jenis objeknya.
    @Override
    public void tampilkanAkses() {
        System.out.println("\nLogin sebagai Member: " + getNama());
        System.out.println("Akses: melihat, mencari, dan menghitung rata-rata durasi lagu.");
    }

    public void lihatDaftarLagu() {
        playlist.tampilkanDaftarLagu();
    }

    // Member mencari judul lagu melalui method cariLagu milik Playlist.
    public void cariLagu(String judul) {
        Lagu laguDitemukan = playlist.cariLagu(judul);

        if (laguDitemukan != null) {
            System.out.println("\nLagu ditemukan:");
            laguDitemukan.tampilkanInfo();
        } else {
            System.out.println("Lagu dengan judul '" + judul + "' tidak ditemukan.");
        }
    }

    // Hasil rata-rata ditampilkan dalam detik agar sama dengan input durasi.
    public void tampilkanRataRataDurasi() {
        double rataRata = playlist.hitungRataRataDurasi();
        System.out.printf("Rata-rata durasi lagu: %.2f detik%n", rataRata);
    }
}

public class PlaylistOOP {
    // Fungsi utama membuat data awal, menerima login, lalu menjalankan menu.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Playlist playlist = new Playlist(20);

        playlist.tambahLagu(new Lagu("Secukupnya", "Hindia", 214));
        playlist.tambahLagu(new Lagu("Monokrom", "Tulus", 214));
        playlist.tambahLagu(new Lagu("Evaluasi", "Hindia", 204));

        boolean programBerjalan = true;

        System.out.println("====================================");
        System.out.println("  SISTEM MANAJEMEN PLAYLIST MUSIK");
        System.out.println("====================================");

        while (programBerjalan) {
            System.out.print("\nMasukkan nama pengguna: ");
            String nama = scanner.nextLine();

            System.out.print("Apakah Anda admin? (ya/tidak): ");
            String jawaban = scanner.nextLine();

            User pengguna;
            if (jawaban.equalsIgnoreCase("ya")) {
                pengguna = new Admin(nama, playlist);
            } else {
                pengguna = new Member(nama, playlist);
            }

            // Java memilih tampilkanAkses() berdasarkan objek Admin atau Member.
            pengguna.tampilkanAkses();

            boolean gantiPengguna = false;
            while (!gantiPengguna && programBerjalan) {
                tampilkanMenu();
                int pilihan = bacaAngka(scanner, "Pilih menu (1-7): ");

                switch (pilihan) {
                    case 1:
                        pengguna.tampilkanAkses();
                        break;
                    case 2:
                        tampilkanDaftarBerdasarkanUser(pengguna);
                        break;
                    case 3:
                        if (pengguna instanceof Member) {
                            System.out.print("Masukkan judul lagu yang dicari: ");
                            String judul = scanner.nextLine();
                            ((Member) pengguna).cariLagu(judul);
                        } else {
                            System.out.println("Menu pencarian digunakan oleh Member.");
                        }
                        break;
                    case 4:
                        if (pengguna instanceof Admin) {
                            tambahLaguBaru(scanner, (Admin) pengguna);
                        } else {
                            System.out.println("Member tidak memiliki akses untuk menambahkan lagu.");
                        }
                        break;
                    case 5:
                        if (pengguna instanceof Member) {
                            ((Member) pengguna).tampilkanRataRataDurasi();
                        } else {
                            System.out.println("Menu rata-rata durasi digunakan oleh Member.");
                        }
                        break;
                    case 6:
                        gantiPengguna = true;
                        System.out.println("Silakan login dengan pengguna lain.");
                        break;
                    case 7:
                        programBerjalan = false;
                        System.out.println("Program selesai. Terima kasih.");
                        break;
                    default:
                        System.out.println("Pilihan menu tidak tersedia.");
                }
            }
        }

        scanner.close();
    }

    // Menu dibuat terpisah supaya fungsi main lebih mudah dibaca.
    private static void tampilkanMenu() {
        System.out.println("\nMenu");
        System.out.println("1. Tampilkan nama dan akses pengguna");
        System.out.println("2. Tampilkan daftar lagu");
        System.out.println("3. Cari lagu (Member)");
        System.out.println("4. Tambah lagu (Admin)");
        System.out.println("5. Hitung rata-rata durasi (Member)");
        System.out.println("6. Ganti pengguna");
        System.out.println("7. Keluar");
    }

    // Admin dan Member sama-sama dapat melihat daftar lagu.
    private static void tampilkanDaftarBerdasarkanUser(User pengguna) {
        if (pengguna instanceof Admin) {
            ((Admin) pengguna).lihatDaftarLagu();
        } else {
            ((Member) pengguna).lihatDaftarLagu();
        }
    }

    // Data dari Admin diubah menjadi objek Lagu sebelum dimasukkan ke array.
    private static void tambahLaguBaru(Scanner scanner, Admin admin) {
        System.out.print("Masukkan judul lagu: ");
        String judul = scanner.nextLine();

        System.out.print("Masukkan nama artis: ");
        String artis = scanner.nextLine();

        double durasi = bacaDesimal(scanner, "Masukkan durasi lagu dalam detik: ");
        admin.tambahLagu(new Lagu(judul, artis, durasi));
    }

    // Input angka dibaca sebagai String dulu supaya program tidak error saat salah ketik.
    private static int bacaAngka(Scanner scanner, String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    // Fungsi ini memastikan nilai durasi berupa angka positif.
    private static double bacaDesimal(Scanner scanner, String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                double nilai = Double.parseDouble(scanner.nextLine());
                if (nilai > 0) {
                    return nilai;
                }
                System.out.println("Durasi harus lebih dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("Input durasi harus berupa angka.");
            }
        }
    }
}
