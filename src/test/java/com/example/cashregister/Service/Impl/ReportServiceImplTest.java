package com.example.cashregister.Service.Impl;

import com.example.cashregister.Service.ReportService;
import com.example.cashregister.dao.ReportDao;
import com.example.cashregister.entity.Report;
import com.example.cashregister.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportServiceImplTest {
    private static ReportService reportService;
    private static ReportDao reportDao;
    private static Report report;
    static ArrayList<Report> list;

    @BeforeAll
    public static void init() {
        reportDao = mock(ReportDao.class);
        reportService = new ReportServiceImpl(reportDao);
        report=new Report(1,1,"abc",1,1.1,"11.02.1992",false);
        list=new ArrayList<>();
        list.add(report);
    }

    @Test
    void getAll() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            reportService.getAll(null,null,null,null);
        });
        when(reportDao.getAll(any(String.class),any(String.class),
                any(Integer.class),any(Integer.class))).thenReturn(list);
        assertTrue(reportService.getAll("abc","abc",1,1).contains(report));
        assertEquals(1,reportService.getAll("abc","abc",1,1).size());
    }

    @Test
    void countRows() throws SQLException {
        when(reportDao.countRows()).thenReturn(2);
        assertEquals(2, reportService.countRows());
        assertNotEquals(5, reportService.countRows());
    }

    @Test
    void get() throws SQLException {
        assertNull(reportService.get(null));
        when(reportDao.get(any(Integer.class))).thenReturn(report);
        assertEquals(report, reportService.get("1"));
        assertEquals(1, reportService.get("1").getId());
    }


    @Test
    void createReport() throws SQLException {
        assertThrows(NumberFormatException.class, () -> {
            reportService.createReport(null, null, null);
        });
        when(reportDao.createReport(any(Integer.class), any(String.class), any(boolean.class))).thenReturn(1);
        assertEquals(1, reportService.createReport(1, "ABC", true));
    }
}