package com.developer.alfin.chipstock;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class RecordOutDAO_Impl implements RecordOutDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfBarangKeluar;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfBarangKeluar;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByIdKeluar;

  public RecordOutDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBarangKeluar = new EntityInsertionAdapter<BarangKeluar>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `BarangKeluar`(`id`,`kode`,`nama`,`stok`,`satuan`,`date_exp`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BarangKeluar value) {
        stmt.bindLong(1, value.getId());
        if (value.getKode() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getKode());
        }
        if (value.getNama() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getNama());
        }
        if (value.getStok() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getStok());
        }
        if (value.getSatuan() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSatuan());
        }
        if (value.getDate_exp() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDate_exp());
        }
      }
    };
    this.__updateAdapterOfBarangKeluar = new EntityDeletionOrUpdateAdapter<BarangKeluar>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `BarangKeluar` SET `id` = ?,`kode` = ?,`nama` = ?,`stok` = ?,`satuan` = ?,`date_exp` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BarangKeluar value) {
        stmt.bindLong(1, value.getId());
        if (value.getKode() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getKode());
        }
        if (value.getNama() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getNama());
        }
        if (value.getStok() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getStok());
        }
        if (value.getSatuan() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSatuan());
        }
        if (value.getDate_exp() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDate_exp());
        }
        stmt.bindLong(7, value.getId());
      }
    };
    this.__preparedStmtOfDeleteByIdKeluar = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM BarangKeluar WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public long inserBarangKeluat(BarangKeluar barangKeluar) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfBarangKeluar.insertAndReturnId(barangKeluar);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateBarangKeluar(BarangKeluar barangKeluar) {
    __db.beginTransaction();
    try {
      __updateAdapterOfBarangKeluar.handle(barangKeluar);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int deleteByIdKeluar(long id) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByIdKeluar.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, id);
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteByIdKeluar.release(_stmt);
    }
  }

  @Override
  public List<BarangKeluar> getAllBarangKeluar() {
    final String _sql = "SELECT * FROM BarangKeluar ORDER BY date_exp ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfKode = _cursor.getColumnIndexOrThrow("kode");
      final int _cursorIndexOfNama = _cursor.getColumnIndexOrThrow("nama");
      final int _cursorIndexOfStok = _cursor.getColumnIndexOrThrow("stok");
      final int _cursorIndexOfSatuan = _cursor.getColumnIndexOrThrow("satuan");
      final int _c