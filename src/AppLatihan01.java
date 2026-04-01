import Latihan01.Diskon;
import Latihan01.ItemTransaksi;
import Latihan01.PaymentEnumType;
import Latihan01.Produk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppLatihan01 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Produk[] allProduk = new Produk[100];
        Diskon aturanDiskon = new Diskon();

        allProduk[0] = new Produk("Kopi Susu", 15000);
        allProduk[1] = new Produk("Kopi Hitam", 10000);
        allProduk[2] = new Produk("Teh Es", 5000);
        allProduk[3] = new Produk("Cappucino", 15000);
        allProduk[4] = new Produk("Fortuner", 100000000);

        while (true) {

            showMenuPilihanAwal();

            int pilihan = input.nextInt();
            input.nextLine();
            if (pilihan == 1) {
                showAllProduk(allProduk);
            } else if (pilihan == 2) {
                for (int i = 0; i < allProduk.length; i++) {
                    if (allProduk[i] == null) {
                        System.out.print("Nama Produk: ");
                        String nama = input.nextLine();

                        System.out.println("Harga Produk");
                        System.out.print("(ketik hanya angka) : ");
                        long harga = input.nextLong();
                        input.nextLine();

                        allProduk[i] = new Produk(nama, harga);
                        System.out.println("\nProduk berhasil ditambahkan!\n");
                        break;
                    }
                }
            } else if (pilihan == 3) {
                // Mode pembelian
                modePembelian(input, allProduk, aturanDiskon);

            } else if (pilihan == 4) {
                // Mode Pengaturan
                modePengaturan(input, allProduk, aturanDiskon);
            } else if (pilihan == 5) {
                System.out.println("\nProgram selesai. Terima kasih!\n");
                break;
            } else {
                System.out.println("Pilihan Tidak Tersedia\n");
            }
        }
        input.close();
    }

    static void modePembelian(Scanner input, Produk[] allProduk, Diskon aturanDiskon) {
        List<ItemTransaksi> keranjang = new ArrayList<>();
        StringBuilder totalSemuaProduk = new StringBuilder();

        while (true) {
            showAllProduk(allProduk);
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
                continue;
            }

            keranjang.add(new ItemTransaksi(selected, totalBeli));

            long subtotalSementara = 0;
            for (ItemTransaksi item : keranjang) {
                subtotalSementara += item.getSubtotal();
            }

            showAllCart(keranjang, subtotalSementara);

            int lanjut = input.nextInt();
            input.nextLine();
            if (lanjut == 2) {
                break;
            }
        }


        if (keranjang.isEmpty()) {
            System.out.println("Belum ada produk yang dibeli");
            return;
        }
        int totalQtySemua = 0;
        long subtotalSemua = 0;

        for (int i = 0; i < keranjang.size(); i++) {
            ItemTransaksi item = keranjang.get(i);
            totalQtySemua += item.getQty();
            subtotalSemua += item.getSubtotal();

            totalSemuaProduk.append((i + 1)).append(". ")
                    .append(item.getProduk().getProduk())
                    .append(" x")
                    .append(item.getQty()) // Ambil angkanya, bukan objek item-nya
                    .append(" = Rp. ")
                    .append(item.getSubtotal())
                    .append("\n");
        }


        long diskon = hitungDiskon(aturanDiskon, totalQtySemua, subtotalSemua);

        long totalBiayaAkhir = subtotalSemua - diskon;
        prosesPembayaran(input, totalSemuaProduk, totalQtySemua, subtotalSemua, diskon, totalBiayaAkhir);

    }


    static void modePengaturan(Scanner input, Produk[] object, Diskon aturanDiskon) {

        showAllProduk(object);

        showMenuPilihanPengaturan();

        int pilihan = input.nextInt();

        if (pilihan == 1) {
            showAllProduk(object);
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
            long hargaBaru = input.nextLong();

            System.out.println("Edit menu index ke " + no + ". Data awal : " + object[no].getProduk() + " " + object[no].getHarga());
            System.out.println("Di edit menjadi : " + "Nama " + namaBaru + " harga " + hargaBaru);
            object[no].setProduk(namaBaru);
            object[no].setHarga(hargaBaru);
            System.out.println("Berhasil di edit!!");
            showAllProduk(object);
        } else if (pilihan == 2) {
            showAllProduk(object);
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
                showAllProduk(object);
            } else if (opsiHapus == 2) {
                System.out.println("Penghapusan dibatalkan.\n");

            } else {
                System.out.println("Pilihan tidak valid.\n");
            }
        } else if (pilihan == 3) {
            System.out.println("==========================================");
            System.out.println("            Daftar Diskon : ");
            System.out.println("Minimal order dapat diskon : " + aturanDiskon.getMIN_QTY_DISKON() + " pembelian");
            System.out.println("Persen diskon yang diberi : " + aturanDiskon.getDISKON_PERCENT() + "%");

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

                if (qtyDiskonBaru <= 0) {
                    System.out.println("Minimal qty diskon harus lebih dari 0!");
                    return;
                }
                System.out.print("Maximal Persent diskon : ");
                int maxPersenDiskonBaru = input.nextInt();

                if (maxPersenDiskonBaru < 0 || maxPersenDiskonBaru > 100) {
                    System.out.println("Persen diskon harus antara 0 - 100!");
                    return;
                }
                System.out.println("\n==========================================");
                System.out.println("        Daftar Perubahan Diskon ");
                System.out.println("Minimal order dapat diskon yang baru : " + qtyDiskonBaru + " pembelian");
                System.out.println("Persen diskon yang diberi yang baru : " + maxPersenDiskonBaru + "%");

                aturanDiskon.setMIN_QTY_DISKON(qtyDiskonBaru);
                aturanDiskon.setDISKON_PERCENT(maxPersenDiskonBaru);

                System.out.println("Perubahan terhadap diskon Berhasil!!\n");
                System.out.println("==========================================\n");

                System.out.println("        Daftar Diskon  ");
                System.out.println("Minimal order dapat diskon : " + aturanDiskon.getMIN_QTY_DISKON() + " pembelian");
                System.out.println("Persen diskon yang diberi : " + aturanDiskon.getDISKON_PERCENT() + "%");
            } else if (opsiDiskon == 2) {
                System.out.println("Pembatalan pengeditan terhadap aturan diskon!!\n");
            } else {
                System.out.println("Pilihan tidak valid.\n");
            }
        }
    }

    private static long hitungDiskon(Diskon aturanDiskon, int totalQtySemua, long subtotalSemua) {
        long diskon = 0;
        if (aturanDiskon.getMIN_QTY_DISKON() > 0 && aturanDiskon.getDISKON_PERCENT() > 0 && totalQtySemua >= aturanDiskon.getMIN_QTY_DISKON()) {

            diskon = subtotalSemua * aturanDiskon.getDISKON_PERCENT() / 100;
        }
        return diskon;
    }

    private static void prosesPembayaran(Scanner input, StringBuilder totalSemuaProduk, int totalQtySemua, long subtotalSemua,
                                         long diskon, long totalBiayaAkhir) {
        PaymentEnumType paymentEnumType = null;

        System.out.println("==========================================");
        System.out.println("                  RINCIAN");
        System.out.println("==========================================");
        System.out.println(totalSemuaProduk);
        System.out.println("Total Qty        : " + totalQtySemua);
        System.out.println("Subtotal         : Rp. " + subtotalSemua);
        System.out.println("Diskon           : Rp. " + diskon);
        System.out.println("Total Biaya      : Rp. " + totalBiayaAkhir + "\n");
        System.out.println("Pilihan");
        System.out.println("1. Bayar");
        System.out.println("2. Batalkan");
        System.out.println("Ketik pilihan : ");
        System.out.print("(ketik hanya angka) : ");

        int rincian = input.nextInt();
        System.out.println("==========================================");
        if (rincian == 1) {


            System.out.println("==========================================");
            System.out.println("        Piilh Metode Pembayaran");
            System.out.println("1. CASH");
            System.out.println("2. QRIS");
            System.out.println("3. DEBIT");
            System.out.println("Ketik pilihan : ");
            System.out.print("(ketik hanya angka) : ");
            while (true) {
                int opsiPembayaran = input.nextInt();

                if (opsiPembayaran == 1) {
                    paymentEnumType = PaymentEnumType.CASH;
                    System.out.println("Metode pembayaran menggunakan : " + paymentEnumType.name());
                    rincianDiskonDanPembayaran(totalSemuaProduk, subtotalSemua, diskon, totalBiayaAkhir, paymentEnumType);
                    break;
                } else if (opsiPembayaran == 2) {
                    paymentEnumType = PaymentEnumType.QRIS;
                    System.out.println("Metode pembayaran menggunakan : " + paymentEnumType.name());
                    rincianDiskonDanPembayaran(totalSemuaProduk, subtotalSemua, diskon, totalBiayaAkhir, paymentEnumType);
                    break;
                } else if (opsiPembayaran == 3) {
                    paymentEnumType = PaymentEnumType.DEBIT;
                    System.out.println("Metode pembayaran menggunakan : " + paymentEnumType.name());
                    rincianDiskonDanPembayaran(totalSemuaProduk, subtotalSemua, diskon, totalBiayaAkhir, paymentEnumType);
                    break;
                } else {
                    System.out.println("Pilihan opsi pembayaran tidak tersedia!!\n");
                    System.out.println("Ketik pilihan : ");
                    System.out.print("(ketik hanya angka) : ");
                }
            }


            while (true) {

                long hargaBayar = input.nextLong();
                input.nextLine();

                if (hargaBayar < totalBiayaAkhir) {
                    System.out.println("Uang harus lebih atau pas dari nominal biaya belanja : Rp. " + totalBiayaAkhir);
                    System.out.print("Masukkan uang bayar lagi : ");

                } else {
                    long kembalian = hargaBayar - totalBiayaAkhir;

                    strukPembayaranBerhasil(totalSemuaProduk, totalBiayaAkhir, hargaBayar, kembalian, paymentEnumType);

                    break;
                }

            }
        } else if (rincian == 2) {
            System.out.println("Pembatalan pembayaran berhasil!\n");
        } else {
            System.out.println("Pembatalan pembayaran berhasil!\n");
        }

    }

    private static void rincianDiskonDanPembayaran(StringBuilder totalSemuaProduk, long subtotalSemua, long diskon, long totalBiayaAkhir, PaymentEnumType typePembayaran) {
        System.out.println("==========================================");
        System.out.println("                PEMBAYARAN");
        System.out.println("==========================================");
        System.out.println(totalSemuaProduk);
        System.out.println("Subtotal           : Rp. " + subtotalSemua);
        System.out.println("Diskon             : Rp. " + diskon);
        System.out.println("Total Pembayaran   : Rp. " + totalBiayaAkhir + "\n");
        System.out.println("Metode Pembayaran  : " + typePembayaran);

        System.out.print("Masukkan uang bayar : ");
    }

    private static void strukPembayaranBerhasil(StringBuilder totalSemuaProduk, long totalBiayaAkhir, long hargaBayar, long kembalian, PaymentEnumType typePembayaran) {
        System.out.println("\n==========================================");
        System.out.println("          Pembayaran Berhasil!!\n");
        System.out.println(totalSemuaProduk);
        System.out.println("Metode Pembayaran  : " + typePembayaran);
        System.out.println("Total Biaya        : Rp. " + totalBiayaAkhir);
        System.out.println("Total pembayaran   : Rp. " + hargaBayar);
        System.out.println("Kembalian          : Rp. " + kembalian);
        System.out.println("Terimakasih atas pembayaran (Demo) nya");
        System.out.println("==========================================\n");
    }

    private static void showMenuPilihanAwal() {
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
    }

    private static void showMenuPilihanPengaturan() {
        System.out.println("Pilihan");
        System.out.println("1. Ubah produk");
        System.out.println("2. Hapus produk");
        System.out.println("3. Edit Diskon");

        System.out.println("Ketik pilihan : ");
        System.out.print("(ketik hanya angka) : ");
    }

    static void showAllProduk(Produk[] object) {
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

    private static void showAllCart(List<ItemTransaksi> keranjang, long subtotalSementara) {
        System.out.println("\n==========================================");
        System.out.println("            Produk terpilih  ");
        for (int i = 0; i < keranjang.size(); i++) {
            ItemTransaksi item = keranjang.get(i);
            System.out.println("\n" + (i + 1) + ". " + item.getProduk().getProduk() + " x" + item.getQty() + "\n     Subtotal : Rp. " + item.getSubtotal());
        }
        System.out.println("\nProduk berhasil ditambahkan ke transaksi!");
        System.out.println("Total sementara   : Rp. " + subtotalSementara + "\n");

        System.out.println("Ingin beli lagi?");
        System.out.println("1. Ya");
        System.out.println("2. Selesai");
        System.out.print("(ketik hanya angka) : ");
    }
}

