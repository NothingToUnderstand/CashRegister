package com.example.cashregister.Service.Impl;

import com.example.cashregister.Service.ArchiveReceiptService;
import com.example.cashregister.dao.ArchiveReceiptDao;
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

class ArchiveReceiptServiceImplTest {

    private static ArchiveReceiptService archiveReceiptService;
    private static ArchiveReceiptDao archiveReceiptDao;
    private static Receipt receipt;
    private static ArrayList<Product> list;
    private static ArrayList<Receipt> listR;
    private static Product product;

    @BeforeAll
    public static void init() {
        archiveReceiptDao = mock(ArchiveReceiptDao.class);
        archiveReceiptService = new ArchiveReceiptServiceImpl(archiveReceiptDao);
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
            archiveReceiptService.getAll(null,null,null,null);
        });
        when(archiveReceiptDao.getAll(any(String.class),any(String.class),
                any(Integer.class),any(Integer.class))).thenReturn(listR);
        assertTrue(archiveReceiptService.getAll("abc","abc",1,1).contains(receipt));
        assertEquals(1,archiveReceiptService.getAll("abc","abc",1,1).size());
    }

    @Test
    void countRows() throws SQLException {
        when(archiveReceiptDao.countRows()).thenReturn(2);
        assertEquals(2, archiveReceiptService.countRows());
        assertNotEquals(5, archiveReceiptService.countRows());
    }

    @Test
    void get() throws SQLException {
        assertNull(archiveReceiptService.get(null));
        when(archiveReceiptDao.get(any(Integer.class))).thenReturn(receipt);
        assertEquals(receipt, archiveReceiptService.get("1"));
        assertEquals(1, archiveReceiptService.get("1").getId());
    }
}