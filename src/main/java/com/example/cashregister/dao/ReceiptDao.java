package com.example.cashregister.dao;

import com.example.cashregister.entity.Receipt;

import java.util.List;

public interface ReceiptDao {
    int createReceipt(int cashierId, String cashierName);

    boolean addProductToReceipt(int receiptId, int productId, int amount);

    boolean deleteReceipt(int id);

    boolean deleteProductInReceipt(int idReceipt, int numberProduct,int productId);

    Receipt getReceipt(int id);

    List<Receipt> getAllReceipts(String column, String direction, Integer limitfrom, Integer limitquantity);

    List<Receipt> getAllReceiptsFromArchive(String column, String direction, Integer limitfrom, Integer limitquantity);

    boolean closeReceipt(int id);

    List<Integer> getReceiptToReport();

    Receipt getReceiptFromArchive(int id);

    int countRows();
    int countRowsArchive();

    boolean cancelProduct(int number,int amount,int idReceipt,int idProduct);
}
