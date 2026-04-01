package Latihan01;

public class ItemTransaksi {
    private Produk produk;
    private int qty;

    public ItemTransaksi(Produk produk, int qty) {
        this.produk = produk;
        this.qty = qty;
    }

    public Produk getProduk() {
        return produk;
    }

    public void setProduk(Produk produk) {
        this.produk = produk;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public long getSubtotal() {
        return produk.getHarga() * qty;
    }
}
