package com.example.cashregister.dao;

import com.example.cashregister.entity.Receipt;

import java.sql.SQLException;
import java.util.List;

public interface ReceiptDao extends MainDao<Receipt>{
    int createReceipt(int cashierId, String cashierName) throws SQLException;

    boolean addProductToReceipt(int receiptId, int productId, int amount) throws SQLException;

    boolean deleteReceipt(int id) throws SQLException;

    boolean deleteProductInReceipt(int idReceipt, int numberProduct,int productId) throws SQLException;

    boolean closeReceipt(int id) throws SQLException;

    boolean cancelProduct(int number,int amount,int idReceipt,int idProduct) throws SQLException;
}
