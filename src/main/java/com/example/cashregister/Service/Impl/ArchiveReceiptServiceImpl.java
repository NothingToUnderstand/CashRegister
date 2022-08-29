package com.example.cashregister.Service.Impl;

import com.example.cashregister.Service.ArchiveReceiptService;
import com.example.cashregister.dao.ArchiveReceiptDao;
import com.example.cashregister.entity.Receipt;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArchiveReceiptServiceImpl implements ArchiveReceiptService {
    private ArchiveReceiptDao archiveReceiptDao;
    @Inject
    public ArchiveReceiptServiceImpl(ArchiveReceiptDao archiveReceiptDao){
        this.archiveReceiptDao=archiveReceiptDao;
    }
    private static final Logger log = Logger.getLogger(ArchiveReceiptServiceImpl.class);
    @Override
    public ArrayList<Receipt> getAll(String column, String direction, Integer limitfrom, Integer limitquantity) throws SQLException {
        if(column==null||direction==null||limitfrom==null||limitquantity==null){
            throw new NumberFormatException();
        }
        return this.archiveReceiptDao.getAll(column, direction, limitfrom, limitquantity);
    }

    @Override
    public int countRows() throws SQLException {
        return this.archiveReceiptDao.countRows();
    }

    @Override
    public Receipt get(String idReceipt) throws SQLException,NumberFormatException {
        int id;
        try {
            id = Integer.parseInt(idReceipt);
        } catch (NumberFormatException e) {
            log.warn("number format exception", e);
           return  null;
        }
        return this.archiveReceiptDao.get(id);
    }
}
