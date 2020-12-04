package local.hal.st31.android.saigoku33memo80655;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

public class DataAccess {
    //  主キーによる本尊名取得
    public static String selectPrincipalImageByPK(SQLiteDatabase db, int id) {
        String sql = "SELECT honzon FROM temples WHERE _id = " + id;
        System.out.println(sql);
        Cursor cursor = db.rawQuery(sql, null);
        System.out.println(cursor);
        String result = "";
        while (cursor.moveToNext()) {
            int idxContent = cursor.getColumnIndex("honzon");
            result = cursor.getString(idxContent);
        }
        return result;
    }

    //  主キーによる宗旨取得
    public static String selectReligionByPK(SQLiteDatabase db, int id) {
        String sql = "SELECT shushi FROM temples WHERE _id = " + id;
        System.out.println(sql);
        Cursor cursor = db.rawQuery(sql, null);
        String result = "";
        while (cursor.moveToNext()) {
            int idxContent = cursor.getColumnIndex("shushi");
            result = cursor.getString(idxContent);
        }
        return result;
    }

    //  主キーによる所在地取得
    public static String selectAddressByPK(SQLiteDatabase db, int id) {
        String sql = "SELECT address FROM temples WHERE _id = " + id;
        System.out.println(sql);
        Cursor cursor = db.rawQuery(sql, null);
        String result = "";
        while (cursor.moveToNext()) {
            int idxContent = cursor.getColumnIndex("address");
            result = cursor.getString(idxContent);
        }
        return result;
    }

    //  主キーによるURL取得
    public static String selectURLByPK(SQLiteDatabase db, int id) {
        String sql = "SELECT url FROM temples WHERE _id = " + id;
        System.out.println(sql);
        Cursor cursor = db.rawQuery(sql, null);
        String result = "";
        while (cursor.moveToNext()) {
            int idxContent = cursor.getColumnIndex("url");
            result = cursor.getString(idxContent);
        }
        return result;
    }

    //  主キーによる感想取得
    public static String selectImpressionByPK(SQLiteDatabase db, int id) {
        String sql = "SELECT note FROM temples WHERE _id = " + id;
        System.out.println(sql);
        Cursor cursor = db.rawQuery(sql, null);
        String result = "";
        while (cursor.moveToNext()) {
            int idxContent = cursor.getColumnIndex("note");
            result = cursor.getString(idxContent);
        }
        return result;
    }

    /**
     * 主キーによるレコード存在チェックメソッド
     *
     * @param db SQLiteDatabaseオブジェクト
     * @param id 主キー値
     * @return 主キーに対応するcontentカラムの値
     */
    public static boolean findRowByPK(SQLiteDatabase db, int id) {
        String sql = "SELECT COUNT(*) AS count FROM temples WHERE _id = " + id;
        System.out.println(sql);
        Cursor cursor = db.rawQuery(sql, null);
        boolean result = false;
        // データがあればtrueなければfalse
        if (cursor.moveToFirst()) {
            int idxCount = cursor.getColumnIndex("count");
            int count = cursor.getInt(idxCount);
            if (count >= 1) {
                return true;
            }
        }
        return result;
    }

    public static int update(SQLiteDatabase db, int id, String honzon, String shushi, String address, String url, String note) {
        String sql = "UPDATE temples SET honzon = ?,shushi = ?,address = ?,url = ?,note = ? WHERE _id = ?";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindString(1, honzon);
        stmt.bindString(2, shushi);
        stmt.bindString(3, address);
        stmt.bindString(4, url);
        stmt.bindString(5, note);
        stmt.bindLong(6, id);
        int result = stmt.executeUpdateDelete();
        return result;
    }

    public static Long insert(SQLiteDatabase db, int id, String name, String honzon, String shushi, String address, String url, String note) {
        String sql = "INSERT INTO temples(_id,name,honzon,shushi,address,url,note) VALUES(?,?,?,?,?,?,?)";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindLong(1, id);
        stmt.bindString(2, name);
        stmt.bindString(3, honzon);
        stmt.bindString(4, shushi);
        stmt.bindString(5, address);
        stmt.bindString(6, url);
        stmt.bindString(7, note);
        Long insertedId = stmt.executeInsert();
        return insertedId;
    }

}
