package Latihan01;

public class Produk {
    private String produk;
    private long harga;

    public Produk(String produk, long harga) {
        this.produk = produk;
        this.harga = harga;
    }

    public String getProduk() {
        return produk;
    }

    public long getHarga() {
        return harga;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public void setHarga(long harga) {
        this.harga = harga;
    }
}