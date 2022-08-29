package com.example.cashregister.Service;

import com.example.cashregister.entity.Receipt;

import java.sql.SQLException;
import java.util.List;

public interface ReceiptService extends MainService<Receipt>{
    Integer createReceipt(Integer cashierId, String cashierName) throws SQLException;

    boolean addProductToReceipt(String receiptId, String productId, String amount) throws SQLException;

    boolean deleteReceipt(String id) throws SQLException;

    boolean deleteProductInReceipt(String idReceipt, String numberProduct,String productId) throws SQLException;

    boolean closeReceipt(String id) throws SQLException;

    boolean cancelProduct(String number,String amount,String idReceipt,String idProduct) throws SQLException;
}
