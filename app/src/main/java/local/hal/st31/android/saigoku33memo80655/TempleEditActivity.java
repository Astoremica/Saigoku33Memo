package local.hal.st31.android.saigoku33memo80655;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TempleEditActivity extends AppCompatActivity {

    private int _selectTempleNo = 0;
    private String _selectTempleName = "";
    private DatabaseHelper _helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temple_edit);

        Intent intent = getIntent();
        _selectTempleNo = intent.getIntExtra("selectTempleNo", 0);
        _selectTempleName = intent.getStringExtra("selectTempleName");

        _helper = new DatabaseHelper(getApplicationContext());

        TextView textViewTempleName = findViewById(R.id.textViewTempleName);
        textViewTempleName.setText(_selectTempleName);

        SQLiteDatabase db = _helper.getWritableDatabase();
        // 本尊名
        String principalImage = DataAccess.selectPrincipalImageByPK(db, _selectTempleNo);
        EditText editTextPrincipalImage = findViewById(R.id.editTextPrincipalImage);
        editTextPrincipalImage.setText(principalImage);
        // 宗旨
        String religion = DataAccess.selectReligionByPK(db, _selectTempleNo);
        EditText editTextReligion = findViewById(R.id.editTextReligion);
        editTextReligion.setText(religion);
        // 所在地
        String address = DataAccess.selectAddressByPK(db, _selectTempleNo);
        EditText editTextAddress = findViewById(R.id.editTextAddress);
        editTextAddress.setText(address);
        // URL
        String url = DataAccess.selectURLByPK(db, _selectTempleNo);
        EditText editTextURL = findViewById(R.id.editTextURL);
        editTextURL.setText(url);
        // 感想
        String note = DataAccess.selectImpressionByPK(db, _selectTempleNo);
        EditText editTextTextMultiLine = findViewById(R.id.editTextMultiLine_Impression);
        editTextTextMultiLine.setText(note);

    }

    @Override
    protected void onDestroy() {
        _helper.close();
        super.onDestroy();
    }

    public void onSaveButtonClick(View view) {
        // 本尊
        EditText editTextPrincipalImage = findViewById(R.id.editTextPrincipalImage);
        String principalImage = editTextPrincipalImage.getText().toString();
        // 宗旨
        EditText editTextReligion = findViewById(R.id.editTextReligion);
        String religion = editTextReligion.getText().toString();
        // 所在地
        EditText editTextAddress = findViewById(R.id.editTextAddress);
        String address = editTextAddress.getText().toString();
        // URL
        EditText editTextURL = findViewById(R.id.editTextURL);
        String url = editTextURL.getText().toString();
        // 感想
        EditText editTextMultiLine_Impression = findViewById(R.id.editTextMultiLine_Impression);
        String impression = editTextMultiLine_Impression.getText().toString();

        SQLiteDatabase db = _helper.getWritableDatabase();
        boolean exist = DataAccess.findRowByPK(db, _selectTempleNo);
        if (exist) {
            DataAccess.update(db, _selectTempleNo, principalImage,religion,address,url,impression);
        } else {
            DataAccess.insert(db, _selectTempleNo, _selectTempleName, principalImage,religion,address,url,impression);
        }
        finish();
    }
}