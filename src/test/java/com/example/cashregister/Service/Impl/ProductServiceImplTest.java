package com.example.cashregister.Service.Impl;

import com.example.cashregister.Service.ProductService;
import com.example.cashregister.dao.ProductDao;
import com.example.cashregister.entity.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.servlet.http.Part;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {
private static ProductDao productDao;
private static ProductService productService;
private static Product product;
private static ArrayList<Product> list;
@BeforeAll
public static void init(){
    productDao=mock(ProductDao.class);
    productService=new ProductServiceImpl(productDao);
    product=new Product(1,"abc",1,1.1,1.1,new byte[1]);
    list=new ArrayList<>();
    list.add(product);
}
    @Test
    void getAll() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            productService.getAll(null,null,null,null);
        });
        when(productDao.getAll(any(String.class),any(String.class),
                any(Integer.class),any(Integer.class))).thenReturn(list);
        assertTrue(productService.getAll("abc","abc",1,1).contains(product));
        assertEquals(1,productService.getAll("abc","abc",1,1).size());
    }

    @Test
    void countRows() throws SQLException {
        when(productDao.countRows()).thenReturn(2);
        assertEquals(2, productService.countRows());
        assertNotEquals(5, productService.countRows());
    }

    @Test
    void get() throws SQLException {
        assertNull(productService.get(null));
        when(productDao.get(any(Integer.class))).thenReturn(product);
        assertEquals(product, productService.get("1"));
        assertEquals(1, productService.get("1").getId());
    }

    @Test
    void createProduct() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            productService.createProduct("ABC", "ABC", "ABC", "ABC", null);
        });
        when(productDao.createProduct(any(String.class), any(Integer.class), any(Double.class),
                any(Double.class), any(byte [].class))).thenReturn(1);
        assertEquals(1, productService.createProduct("ABC", "1", "1", "1", new byte[1]));
    }

    @Test
    void updateProduct() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            productService.updateProduct("ABC", "ABC", "ABC", "ABC", "ABC", null);
        });
        when(productDao.updateProduct(any(Integer.class),
                any(String.class), any(Integer.class), any(Double.class), any(Double.class), any(byte[].class))).thenReturn(true);
        assertTrue(productService.updateProduct("1","abc","1","1.1","1.1",new byte[1]));
    }

    @Test
    void deleteProduct() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            productService.deleteProduct("null");
        });
        when(productDao.deleteProduct(any(Integer.class))).thenReturn(true);
        assertTrue(productService.deleteProduct("1"));
    }

    @Test
    void searchProduct() throws SQLException {
        assertNull(productService.searchProduct(null));
        when(productDao.searchProduct(any(String.class))).thenReturn(product);
        assertEquals(product, productService.searchProduct("abc"));
    }
}