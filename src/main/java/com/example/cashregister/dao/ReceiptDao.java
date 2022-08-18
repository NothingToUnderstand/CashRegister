package com.example.cashregister.dao;

import com.example.cashregister.entity.Receipt;

import java.util.List;

public interface ReceiptDao extends MainDao<Receipt>{
    int createReceipt(int cashierId, String cashierName);

    boolean addProductToReceipt(int receiptId, int productId, int amount);

    boolean deleteReceipt(int id);

    boolean deleteProductInReceipt(int idReceipt, int numberProduct,int productId);

    boolean closeReceipt(int id);

    List<Integer> getReceiptToReport();

    boolean cancelProduct(int number,int amount,int idReceipt,int idProduct);
}
