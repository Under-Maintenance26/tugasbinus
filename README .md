# Tugas Kelompok 1 Sistem Manajemen Playlist Musik

Repository ini berisi program Java sederhana untuk mengelola playlist musik. Program dibuat menggunakan konsep Object-Oriented Programming dan array sesuai instruksi tugas COSC6025 Data Structures and Algorithm Analysis.

## Anggota Kelompok

| Nama | NIM | Program Studi |
| --- | --- | --- |
| Krisna Setiyawan | 2902829641 | Computer Science |
| Azril Tsani | 2902807312 | Computer Science |
| Bambang Priyanto | 2902819073 | Computer Science |
| Haiefa Agasy Aprilya Sari | 2602311275 | Computer Science |
| Muhammad Ramadan Abdul Khalik | 3002933264 | Computer Science |

## Fitur Program

- Admin dapat melihat daftar lagu dan menambahkan lagu baru.
- Member dapat melihat daftar lagu, mencari lagu berdasarkan judul, dan menghitung rata-rata durasi.
- Lagu disimpan menggunakan array objek `Lagu` dengan kapasitas 20 lagu.
- Pengguna dapat berganti dari Admin menjadi Member tanpa menutup program.

## Penjelasan OOP

### Encapsulation

Atribut `judul`, `artis`, dan `durasi` pada class `Lagu` dibuat `private`. Data tersebut dibaca atau diubah melalui getter dan setter. Atribut `nama` pada class `User` juga dibuat `private`.

### Inheritance

Class `Admin` dan `Member` menggunakan `extends User`. Artinya, kedua class tersebut mewarisi atribut nama, referensi playlist, constructor, getter, dan setter dari parent class `User`.

### Polymorphism

Method `tampilkanAkses()` dibuat sebagai method abstract pada class `User`, kemudian dioverride oleh `Admin` dan `Member`. Saat variabel bertipe `User` berisi objek `Admin`, yang dijalankan adalah method milik `Admin`. Kalau objeknya `Member`, Java menjalankan method milik `Member`.

### Penggunaan Array

Objek lagu disimpan pada `Lagu[] daftarLagu`. Variabel `jumlahLagu` dipakai untuk mencatat berapa posisi array yang sudah terisi. Penambahan, pencarian, dan perhitungan rata-rata dilakukan dengan perulangan sampai indeks `jumlahLagu`.

## Cara Menjalankan

Pastikan komputer sudah memiliki JDK. Jalankan perintah berikut dari folder project:

```bash
javac PlaylistOOP.java
java PlaylistOOP
```

## Hasil Eksekusi

![Hasil eksekusi program](screenshot-hasil.png)

## Video Demo

Video demo sudah tersedia pada file [result.mp4](result.mp4).

Setelah repository ini diunggah ke GitHub kelompok, salin link repository dan video dari repository tersebut ke LMS.

## Isi File

- `PlaylistOOP.java` merupakan source code utama.
- `screenshot-hasil.png` merupakan bukti hasil eksekusi program.
- `result.mp4` merupakan video demonstrasi.
- `Laporan_Tugas_Kelompok_1.docx` merupakan laporan singkat tugas.
