package com.example;

import com.example.product.ProductService;
import com.example.product.Product;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ProductService service = new ProductService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu: ");
            System.out.println("1. Buat Produk");
            System.out.println("2. Tampilkan Semua Produk");
            System.out.println("3. Cari Produk Berdasarkan ID");
            System.out.println("4. Cari Produk Berdasarkan Nama");
            System.out.println("5. Update Produk");
            System.out.println("6. Delete Produk");
            System.out.println("7. Keluar");

            System.out.print("Pilih Menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Masukkan Nama Produk: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan Harga Produk: ");
                    double price = scanner.nextDouble();
                    System.out.print("Masukkan Jumlah Stok ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    service.createProduct(name, price, quantity);
                    System.out.println("Produk Berhasil Disimpan!!");
                    break;
                case 2:
                    System.out.println("Produk Tersedia");
                    printProductTable(service.listProducts());
                    break;
                case 3:
                    System.out.print("Masukkan ID Produk: ");
                    String productId = scanner.nextLine();
                    service.getProduct(productId).ifPresentOrElse(
                        App::printProductDetails,
                        () -> System.out.println("Mohon maaf produk tidak ditemukan.")
                    );
                    break;
                case 4:
                    System.out.print("Cari disini : ");
                    String keyword = scanner.nextLine();
                    System.out.println("Hasil Pencarian: ");
                    printProductTable(service.searchProductsByName(keyword));
                    break;
                case 5:
                    System.out.print("Masukkan ID Produk: ");
                    String updateId = scanner.nextLine();
                    System.out.print("Masukkan Nama Produk: ");
                    String newName = scanner.nextLine();
                    System.out.print("Masukkan Harga Produk: ");
                    double newPrice = scanner.nextDouble();
                    System.out.print("Masukkan Jumlah Stok Produk: ");
                    int newQuantity = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    service.updateProduct(updateId, newName, newPrice, newQuantity);
                    System.out.println("Produk Berhasil Di-Update!!!");
                    break;
                case 6:
                    System.out.print("Masukkan ID Produk: ");
                    String deleteId = scanner.nextLine();
                    service.deleteProduct(deleteId);
                    System.out.println("Produk Berhasil Dihapus!!!!");
                    break;
                case 7:
                    scanner.close();
                    System.out.println("Keluar ...");
                    return;
                default:
                    System.out.println("Menu Tidak Ada, Coba Lagi!");
            }
        }
    }

    private static void printProductTable(List<Product> products) {
        String format = "| %-4s | %-20s | %-10s | %-8s |%n";
        System.out.format("+------+----------------------+------------+------+%n");
        System.out.format("| ID   | Nama                 | Harga      | Stok |%n");
        System.out.format("+------+----------------------+------------+------+%n");
        for (Product product : products) {
            System.out.format(format, product.getId(), product.getName(), product.getPrice(), product.getQuantity());
        }
        System.out.format("+------+----------------------+------------+------+%n");
    }

    private static void printProductDetails(Product product) {
        System.out.println("Product Details:");
        System.out.format("+------+----------------------+------------+------+%n");
        System.out.format("| ID   | Nama                 | Harga      | Stok |%n");
        System.out.format("+------+----------------------+------------+------+%n");
        System.out.format("| %-4s | %-20s | %-10s | %-8s |%n", product.getId(), product.getName(), product.getPrice(), product.getQuantity());
        System.out.format("+------+----------------------+------------+------+%n");
    }
}
