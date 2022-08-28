package com.example.cashregister.Service.Impl;

import com.example.cashregister.Service.ReceiptService;
import com.example.cashregister.dao.ReceiptDao;
import com.example.cashregister.entity.Receipt;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReceiptServiceImpl implements ReceiptService {
    private ReceiptDao receiptDao;
    @Inject
    public ReceiptServiceImpl(ReceiptDao receiptDao){
        this.receiptDao=receiptDao;
    }

    private static final Logger log = Logger.getLogger(UserServiceImpl.class);

    @Override
    public ArrayList<Receipt> getAll(String column, String direction, Integer limitfrom, Integer limitquantity) throws SQLException {
        return this.receiptDao.getAll(column, direction, limitfrom, limitquantity);
    }

    @Override
    public int countRows() throws SQLException {
        return this.receiptDao.countRows();
    }

    @Override
    public Receipt get(String idReceipt) throws SQLException,NumberFormatException {
        int id;
        try {
            id = Integer.parseInt(idReceipt);
        } catch (NumberFormatException e) {
            log.warn("number format exception", e);
            return null;
        }
        return this.receiptDao.get(id);
    }

    @Override
    public int createReceipt(int cashierId, String cashierName) throws SQLException {
        return this.receiptDao.createReceipt(cashierId, cashierName);
    }

    @Override
    public boolean addProductToReceipt(String receiptId_, String productId_, String amount_) throws SQLException,NumberFormatException {
        if (receiptId_==null|| amount_==null|| productId_==null) {
            throw new NumberFormatException();
        }
        int amount;
        int receiptId;
        int productId;
        try {
            amount = Integer.parseInt(amount_);
            receiptId= Integer.parseInt(receiptId_);
            productId = Integer.parseInt(productId_);
        } catch (NumberFormatException e) {
            log.error("number format exception", e);
            throw e;
        }
        return this.receiptDao.addProductToReceipt(receiptId, productId, amount);
    }

    @Override
    public boolean deleteReceipt(String idReceipt) throws SQLException,NumberFormatException{
        int id;
        try {
            id = Integer.parseInt(idReceipt);
        } catch (NumberFormatException e) {
            log.error("number format exception", e);
            throw e;
        }
        return this.receiptDao.deleteReceipt(id);
    }

    @Override
    public boolean deleteProductInReceipt(String idReceipt_, String numberProduct_, String productId_) throws SQLException,NumberFormatException {
        if (numberProduct_==null || idReceipt_==null || productId_==null) {
            throw new NumberFormatException();
        }
        int number;
        int idReceipt;
        int idProduct;
        try {
            number = Integer.parseInt(numberProduct_);
            idReceipt = Integer.parseInt(idReceipt_);
            idProduct = Integer.parseInt(productId_);
        } catch (NumberFormatException e) {
            log.error("number format exception", e);
            throw e;
        }
        return this.receiptDao.deleteProductInReceipt(idReceipt,number,idProduct);
    }

    @Override
    public boolean closeReceipt(String id) throws SQLException,NumberFormatException {
        int idReceipt = 0;
        Receipt receipt=get(id);
        try{
            idReceipt=Integer.parseInt(id);
        }catch (NumberFormatException e){
            log.error("error id is not valid");
        }
        if(receipt.getNumberOfProducts()!=0) {
            return this.receiptDao.closeReceipt(idReceipt);
        }else{
            return false;
        }
    }

    @Override
    public boolean cancelProduct(String number_, String amount_, String idReceipt_, String idProduct_) throws SQLException,NumberFormatException{
        if (number_==null || amount_==null ||
                idReceipt_==null || idProduct_==null) {
            throw new NumberFormatException();
        }
        int number;
        int amount;
        int idReceipt;
        int idProduct;
        try {
            number = Integer.parseInt(number_);
            amount = Integer.parseInt(amount_);
            idReceipt = Integer.parseInt(idReceipt_);
            idProduct = Integer.parseInt(idProduct_);
        } catch (NumberFormatException e) {
            log.error("number format exception", e);
            throw e;
        }
        return this.receiptDao.cancelProduct(number, amount, idReceipt, idProduct);
    }
}
