import Latihan01.Produk;

import java.util.Scanner;

public class AppLatihan01 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Produk[] allProduk = new Produk[100];

        allProduk[0] = new Produk("Kopi Susu", 15000);
        allProduk[1] = new Produk("Kopi Hitam", 10000);
        allProduk[2] = new Produk("Teh Es", 5000);
        allProduk[3] = new Produk("Cappucino", 15000);
        while (true) {
            System.out.println("==========================================");
            System.out.println("                 MENU");
            System.out.println("==========================================");

            System.out.println("1. Lihat Daftar Produk");
            System.out.println("2. Tambah Produk");
            System.out.println("3. Mode Pembelian");
            System.out.println("4. End Program");
            System.out.println("Ketik Pilihan : ");
            System.out.print("(ketik hanya angka) : ");
            int pilihan = input.nextInt();
            input.nextLine();
            if (pilihan == 1) {
                showAll(allProduk);
            } else if (pilihan == 2) {
                for (int i = 0; i < allProduk.length; i++) {
                    if (allProduk[i] == null) {
                        System.out.print("Nama Produk: ");
                        String nama = input.nextLine();

                        System.out.println("Harga Produk");
                        System.out.print("(ketik hanya angka) : ");
                        int harga = input.nextInt();
                        input.nextLine();

                        allProduk[i] = new Produk(nama, harga);
                        System.out.println("\nProduk berhasil ditambahkan!\n");
                        break;
                    }
                }
            } else if (pilihan == 3) {
//                Mode pembelian
                modePembelian(input, allProduk);

            } else if (pilihan == 4) {
                System.out.println("\nProgram selesai. Terima kasih!\n");
                break;
            } else {
                System.out.println("Pilihan Tidak Tersedia\n");
            }
        }
        input.close();
    }

    static void showAll(Produk[] object) {
        System.out.println("\n" + "==========================================");
        System.out.println("              Daftar Produk");
        System.out.println("==========================================");
        for (int i = 0; i < object.length; i++) {
            if (object[i] != null) {

                System.out.println("    No " + (i + 1) + ". Nama = " + object[i].getProduk() + " Rp. " + object[i].getHarga());

            }
        }
        System.out.println("==========================================");
    }

    static void modePembelian(Scanner input, Produk[] allProduk) {
        int total;
        int diskon = 0;

        final int MIN_QTY_DISKON = 5;
        final int DISKON_PERCENT = 15;

        showAll(allProduk);
        System.out.println("Ketik nomor menu");
        System.out.print("(ketik hanya angka) : ");
        int no = input.nextInt() - 1;
        if (no < 0 || no >= allProduk.length || allProduk[no] == null) {
            System.out.println("Pilihan Tidak Valid !!!\n");
            return;
        }
        Produk selected = allProduk[no];

        System.out.println("    No " + (no + 1) + ". Nama = " + selected.getProduk() + " Rp. " + selected.getHarga());

        System.out.println("Jumlah beli : ");
        System.out.print("(ketik hanya angka) : ");
        int totalBeli = input.nextInt();
        input.nextLine();

        if (totalBeli <= 0) {
            System.out.println("Jumlah beli harus lebih dari 0!\n");
            return;
        }

        total = totalBeli * selected.getHarga();

        if (totalBeli >= MIN_QTY_DISKON) {
             diskon = total * DISKON_PERCENT / 100;
        }

        int totalBiayaAkhir = total - diskon;

        System.out.println("==========================================");
        System.out.println("                  RINCIAN                 ");
        System.out.println("\nMembeli " + totalBeli + " " + selected.getProduk());
        System.out.println("Biaya Normal     : Rp. " + total);
        System.out.println("Diskon           : Rp. " + diskon + "\n");
        System.out.println("Total Biaya      : Rp. " + totalBiayaAkhir + "\n");
        System.out.println("Pilihan");
        System.out.println("1. Bayar");
        System.out.println("2. Kembali ke awal");
        System.out.println("Ketik pilihan : ");
        System.out.print("(ketik hanya angka) : ");
        int rincian = input.nextInt();
        System.out.println("==========================================");
        if (rincian == 1) {
            System.out.println("==========================================");
            System.out.println("                PEMBAYARAN                ");
            System.out.println("\nMembeli " + totalBeli + " " + selected.getProduk());
            System.out.println("Total Pembayaran : Rp. " + totalBiayaAkhir + " nya berhasil!!" + "\n");
            System.out.println("Terimakasih atas pembayaran (Demo) nya");
            System.out.println("==========================================" + "\n");
        } else if (rincian == 2) {
            System.out.println("Pembatalan pembayaran berhasil!\n");
        }

    }
}
