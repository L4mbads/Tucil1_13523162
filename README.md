<h1 align="center">Tugas Kecil 1 IF2211 Strategi Algoritma</h1>
<h3 align="center">Penyelesaian IQPuzzlerPro dengan Algoritma Brute Force</p>

## Daftar Isi

- [Overview](#overview)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Author](#author)

## Overview

IQPuzzlerSolver adalah program untuk mencari solusi dari permainan puzzle [IQPuzzlerPro](https://www.smartgames.eu/uk/one-player-games/iq-puzzler-pro-0) milik SmartGames.
Program ini menggunakan algoritma brute force untuk mencari solusi, dimana semua kombinasi peletakan puzzle dicoba hingga solusi valid ditemukan atau semua kombinasi telah dicoba.
Komponen pada permainan ini antara lain adalah:
- Board (Papan) – Board merupakan komponen utama yang menjadi tujuan permainan dimana pemain harus mampu mengisi seluruh area papan menggunakan blok-blok yang telah disediakan.
- Blok – Blok adalah komponen yang digunakan pemain untuk mengsi papan kosong hingga terisi penuh. Setiap blok memiliki bentuk yang unik dan semua blok harus digunakan untuk menyelesaikan puzzle.

Program dibuat dengan Java versi 21  

[![Java](https://skillicons.dev/icons?i=java)]()


## Requirements
Pastikan Java Runtime Environment terpasang di sistem operasi agar bisa menjalankan program. Untuk mengkompilasi program, pastikan juga Java Development Kit terpasang.


## Installation

Untuk menjalankan program, maka lakukan langkah berikut:

1. Klon repositori ini ke lokal:
```shell
git clone https://github.com/L4mbads/Tucil1_13523162
```

2. Masuk ke repo lokal:
```shell
cd Tucil1_13523162
```

3. Untuk mengkompilasi program, gunakan salah satu dari skrip build sesuai dengan sistem operasi:
```shell
./build.sh  # jika menggunakan Linux
```
```pwsh
./build.bat # jika menggunakan Windows
```

4. Jalankan program:
```shell
java -cp bin com.fachriza.iqpuzzlersolver.App     # CLI
java -cp bin com.fachriza.iqpuzzlersolver.App GUI # GUI
```

Pastikan requirements terpenuhi sebelum menjalankan program.

## Usage
- Pastikan file input berupa .txt berada di dalam folder ```config```.
- Masukkan file input ke program.
- Tunggu hingga program mendapatkan solusi (atau tidak ada solusi).
- Anda bisa menyimpan solusi ke dalam file teks dan gambar di folder ```test```.

## Author
- [Fachriza Ahmad Setiyono](https://github.com/L4mbads) - 13523162 - K3
