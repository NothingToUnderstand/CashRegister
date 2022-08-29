package com.example.cashregister.Service.Impl;

import com.example.cashregister.Service.ReceiptService;
import com.example.cashregister.dao.ReceiptDao;
import com.example.cashregister.entity.Product;
import com.example.cashregister.entity.Receipt;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReceiptServiceImplTest {

    private static ReceiptService receiptService;
    private static ReceiptDao receiptDao;
    private static Receipt receipt;
    private static ArrayList<Product> list;
    private static ArrayList<Receipt> listR;
    private static Product product;

    @BeforeAll
    public static void init() {
        receiptDao = mock(ReceiptDao.class);
        receiptService = new ReceiptServiceImpl(receiptDao);
        product=new Product(1,"abc",1,1.1,1.1,new byte[1]);
        list = new ArrayList<>();
        list.add(product);
        receipt = new Receipt(1, 1, "abc", 0,
                1.1, "11.02.1992", "11.02.1992", list);
        listR = new ArrayList<>();
        listR.add(receipt);
    }

    @Test
    void getAll() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            receiptService.getAll(null,null,null,null);
        });
        when(receiptDao.getAll(any(String.class),any(String.class),
                any(Integer.class),any(Integer.class))).thenReturn(listR);
        assertTrue(receiptService.getAll("abc","abc",1,1).contains(receipt));
        assertEquals(1,receiptService.getAll("abc","abc",1,1).size());
    }

    @Test
    void countRows() throws SQLException {
        when(receiptDao.countRows()).thenReturn(2);
        assertEquals(2, receiptService.countRows());
        assertNotEquals(5, receiptService.countRows());
    }

    @Test
    void get() throws SQLException {
        assertNull(receiptService.get(null));
        when(receiptDao.get(any(Integer.class))).thenReturn(receipt);
        assertEquals(receipt, receiptService.get("1"));
        assertEquals(1, receiptService.get("1").getId());
    }

    @Test
    void createReceipt() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            receiptService.createReceipt(null, null);
        });
        when(receiptDao.createReceipt(any(Integer.class), any(String.class))).thenReturn(1);
        assertEquals(1, receiptService.createReceipt(1, "ABC"));
    }

    @Test
    void addProductToReceipt() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            receiptService.addProductToReceipt(null,null,null);
        });
        assertThrows(NumberFormatException.class, () -> {
            receiptService.addProductToReceipt("null","null","null");
        });
        when(receiptDao.addProductToReceipt(any(Integer.class),any(Integer.class),any(Integer.class))).thenReturn(true);
        assertTrue(receiptService.addProductToReceipt("1","1","1"));
    }

    @Test
    void deleteReceipt() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            receiptService.deleteReceipt("null");
        });
        when(receiptDao.deleteReceipt(any(Integer.class))).thenReturn(true);
        assertTrue(receiptService.deleteReceipt("1"));
    }

    @Test
    void deleteProductInReceipt() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            receiptService.deleteProductInReceipt(null,null,null);
        });
        assertThrows(NumberFormatException.class, () -> {
            receiptService.deleteProductInReceipt("null","null","null");
        });
        when(receiptDao.deleteProductInReceipt(any(Integer.class),any(Integer.class),any(Integer.class))).thenReturn(true);
        assertTrue(receiptService.deleteProductInReceipt("1","1","1"));
    }

    @Test
    void closeReceipt() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            receiptService.closeReceipt("null");
        });
        when(receiptDao.get(any(Integer.class))).thenReturn(receipt);
        when(receiptDao.closeReceipt(any(Integer.class))).thenReturn(true);
        assertFalse(receiptService.closeReceipt("1"));
        when(receiptDao.get(any(Integer.class))).thenReturn(new Receipt(1, 1, "abc", 1,
                1.1, "11.02.1992", "11.02.1992", list));
        assertTrue(receiptService.closeReceipt("1"));
    }

    @Test
    void cancelProduct() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            receiptService.cancelProduct(null,null,null,null);
        });
        assertThrows(NumberFormatException.class, () -> {
            receiptService.cancelProduct("null","null","null","null");
        });
        when(receiptDao.cancelProduct(any(Integer.class),any(Integer.class),any(Integer.class),any(Integer.class))).thenReturn(true);
        assertTrue(receiptService.cancelProduct("1","1","1","1"));
    }
}