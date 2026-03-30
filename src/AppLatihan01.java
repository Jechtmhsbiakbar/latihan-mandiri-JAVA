import Latihan01.Diskon;
import Latihan01.Produk;

import java.util.Scanner;

public class AppLatihan01 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Produk[] allProduk = new Produk[100];
        Diskon minQtyDiskon = new Diskon();
        Diskon maxPersenDiskon = new Diskon();

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
            System.out.println("4. Pengaturan");
            System.out.println("5. End Program");
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
                // Mode pembelian
                modePembelian(input, allProduk, minQtyDiskon, maxPersenDiskon);

            } else if (pilihan == 4) {
                // Mode Pengaturan
                modePengaturan(input, allProduk, minQtyDiskon, maxPersenDiskon);
            } else if (pilihan == 5) {
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

    static void modePembelian(Scanner input, Produk[] allProduk, Diskon qtyDiskon, Diskon percentDiskon) {
        int total;
        int diskon = 0;

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

        if (totalBeli >= qtyDiskon.getMIN_QTY_DISKON()) {
            diskon = total * percentDiskon.getDISKON_PERCENT() / 100;
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

    static void modePengaturan(Scanner input, Produk[] object, Diskon diskonQty, Diskon diskonPersen) {
        showAll(object);

        System.out.println("Pilihan");
        System.out.println("1. Ubah produk");
        System.out.println("2. Hapus produk");
        System.out.println("3. Edit Diskon");

        System.out.println("Ketik pilihan : ");
        System.out.print("(ketik hanya angka) : ");
        int pilihan = input.nextInt();

        if (pilihan == 1) {
            showAll(object);
            System.out.println("Ketik nomor menu yang mau di ubah");
            System.out.print("(ketik hanya angka) : ");
            int no = input.nextInt() - 1;
            if (no < 0 || no >= object.length || object[no] == null) {
                System.out.println("Pilihan Tidak Valid !!!\n");
                return;
            }
            input.nextLine();

            Produk selected = object[no];

            System.out.println("    No " + (no + 1) + ". Nama = " + selected.getProduk() + " Rp. " + selected.getHarga());

            System.out.print("Edit nama : ");
            String namaBaru = input.nextLine();

            System.out.print("Edit harga : ");
            int hargaBaru = input.nextInt();

            System.out.println("Edit menu index ke " + no + ". Data awal : " + object[no].getProduk() + " " + object[no].getHarga());
            System.out.println("Di edit menjadi : " + "Nama " + namaBaru + " harga " + hargaBaru);
            object[no].setProduk(namaBaru);
            object[no].setHarga(hargaBaru);
            System.out.println("Berhasil di edit!!");
            showAll(object);
        } else if (pilihan == 2) {
            showAll(object);
            System.out.println("Ketik nomor menu yang mau di Hapus");
            System.out.print("(ketik hanya angka) : ");
            int no = input.nextInt() - 1;
            if (no < 0 || no >= object.length || object[no] == null) {
                System.out.println("Pilihan Tidak Valid !!!\n");
                return;
            }
            input.nextLine();

            Produk selected = object[no];

            System.out.println("    No " + (no + 1) + ". Nama = " + selected.getProduk() + " Rp. " + selected.getHarga());
            System.out.println("Yakin ingin di hapus? ");
            System.out.println("1. Hapus");
            System.out.println("2. Batal");

            System.out.println("Ketik pilihan : ");
            System.out.print("(ketik hanya angka) : ");
            int opsiHapus = input.nextInt();

            if (opsiHapus == 1) {
                System.out.println("Data dibawah ini: ");
                System.out.println("    No " + (no + 1) + ". Nama = " + selected.getProduk() + " Rp. " + selected.getHarga());
                for (int i = no; i < object.length - 1; i++) {
                    object[i] = object[i + 1];
                }
                object[object.length - 1] = null;

                System.out.println("Berhasil dihapus!!\n");
                showAll(object);
            } else if (opsiHapus == 2) {
                System.out.println("Penghapusan dibatalkan.\n");

            } else {
                System.out.println("Pilihan tidak valid.\n");
            }
        } else if (pilihan == 3) {
            System.out.println("==========================================");
            System.out.println("            Daftar Diskon : ");
            System.out.println("Minimal order dapat diskon : " + diskonQty.getMIN_QTY_DISKON() + " pembelian");
            System.out.println("Persen diskon yang diberi : " + diskonPersen.getDISKON_PERCENT() + "%");

            System.out.println("Pilihan :");
            System.out.println("1. Edit aturan diskon");
            System.out.println("2. Batal");
            System.out.println("==========================================");

            System.out.println("Ketik pilihan : ");
            System.out.print("(ketik hanya angka) : ");
            int opsiDiskon = input.nextInt();

            if (opsiDiskon == 1) {
                System.out.println("\n==========================================");
                System.out.println("        Mode Edit Aturan Diskon  ");
                System.out.print("Minimal kapasitas dapat diskon : ");
                int qtyDiskonBaru = input.nextInt();

                System.out.print("Maximal Persent diskon : ");
                int maxPersenDiskonBaru = input.nextInt();

                System.out.println("\n==========================================");
                System.out.println("        Daftar Perubahan Diskon ");
                System.out.println("Minimal order dapat diskon yang baru : " + qtyDiskonBaru + " pembelian");
                System.out.println("Persen diskon yang diberi yang baru : " + maxPersenDiskonBaru + "%");

                diskonQty.setMIN_QTY_DISKON(qtyDiskonBaru);
                diskonPersen.setDISKON_PERCENT(maxPersenDiskonBaru);

                System.out.println("Perubahan terhadap diskon Berhasil!!\n");
                System.out.println("==========================================\n");

                System.out.println("        Daftar Diskon  ");
                System.out.println("Minimal order dapat diskon : " + diskonQty.getMIN_QTY_DISKON() + " pembelian");
                System.out.println("Persen diskon yang diberi : " + diskonPersen.getDISKON_PERCENT() + "%");
            } else if (opsiDiskon == 2) {
                System.out.println("Pembatalan pengeditan terhadap aturan diskon!!\n");
            } else {
                System.out.println("Pilihan tidak valid.\n");
            }
        }
    }
}
