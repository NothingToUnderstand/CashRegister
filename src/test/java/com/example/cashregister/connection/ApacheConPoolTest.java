package com.example.cashregister.connection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ApacheConPoolTest {

    static Connection con;

    @BeforeAll
    public static void init() {
        con = mock(Connection.class);
    }

    @Test
    void getConnection() throws SQLException {
        try (MockedStatic<ApacheConPool> theMock = Mockito.mockStatic(ApacheConPool.class)) {
            theMock.when(ApacheConPool::getConnection).thenReturn(con);
            assertEquals(con, ApacheConPool.getConnection());
        }
    }
}