package local.hal.st31.android.saigoku33memo80655;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//  SQLiteOpenHelperを継承する
/**
 * データーベースファイル
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     * データーベースファイル名の定義フィールド
     */
    private static final String DATABASE_NAME = "templememo.db";

    /**
     * バージョン情報の定義フィールド
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * コンストラクタ
     *
     * @param context コンテキスト
     */

    //  コンストラクタ・onCreate・onUpgradeがないとコンパイルエラー
    public DatabaseHelper(Context context) {
        //  引数なしのコンストラクタがないので作成しないとコンパイルエラー
        //  バージョンは基本的に1
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE temples (");
        //  各テーブルの主キーはINTEGERの"_id"と決まっている
        sb.append("_id INTEGER,");
        sb.append("name TEXT,");
        sb.append("honzon TEXT,");
        sb.append("shushi TEXT, ");
        sb.append("address TEXT, ");
        sb.append("url TEXT, ");
        sb.append("note TEXT, ");
        sb.append("PRIMARY KEY(_id)");
        sb.append(");");
        String sql = sb.toString();
        System.out.println(sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
    }
}