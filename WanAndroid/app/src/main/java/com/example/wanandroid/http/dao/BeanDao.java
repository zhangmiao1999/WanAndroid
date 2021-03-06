package com.example.wanandroid.http.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.wanandroid.http.bean.Bean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BEAN".
*/
public class BeanDao extends AbstractDao<Bean, Long> {

    public static final String TABLENAME = "BEAN";

    /**
     * Properties of entity Bean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Author = new Property(1, String.class, "author", false, "AUTHOR");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property NiceDate = new Property(3, String.class, "niceDate", false, "NICE_DATE");
        public final static Property CharName = new Property(4, String.class, "charName", false, "CHAR_NAME");
        public final static Property CharSuperName = new Property(5, String.class, "charSuperName", false, "CHAR_SUPER_NAME");
        public final static Property Link = new Property(6, String.class, "link", false, "LINK");
    }


    public BeanDao(DaoConfig config) {
        super(config);
    }
    
    public BeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"AUTHOR\" TEXT," + // 1: author
                "\"TITLE\" TEXT," + // 2: title
                "\"NICE_DATE\" TEXT," + // 3: niceDate
                "\"CHAR_NAME\" TEXT," + // 4: charName
                "\"CHAR_SUPER_NAME\" TEXT," + // 5: charSuperName
                "\"LINK\" TEXT);"); // 6: link
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Bean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(2, author);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String niceDate = entity.getNiceDate();
        if (niceDate != null) {
            stmt.bindString(4, niceDate);
        }
 
        String charName = entity.getCharName();
        if (charName != null) {
            stmt.bindString(5, charName);
        }
 
        String charSuperName = entity.getCharSuperName();
        if (charSuperName != null) {
            stmt.bindString(6, charSuperName);
        }
 
        String link = entity.getLink();
        if (link != null) {
            stmt.bindString(7, link);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Bean entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(2, author);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String niceDate = entity.getNiceDate();
        if (niceDate != null) {
            stmt.bindString(4, niceDate);
        }
 
        String charName = entity.getCharName();
        if (charName != null) {
            stmt.bindString(5, charName);
        }
 
        String charSuperName = entity.getCharSuperName();
        if (charSuperName != null) {
            stmt.bindString(6, charSuperName);
        }
 
        String link = entity.getLink();
        if (link != null) {
            stmt.bindString(7, link);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Bean readEntity(Cursor cursor, int offset) {
        Bean entity = new Bean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // author
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // niceDate
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // charName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // charSuperName
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // link
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Bean entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAuthor(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setNiceDate(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCharName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCharSuperName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setLink(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Bean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Bean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Bean entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
