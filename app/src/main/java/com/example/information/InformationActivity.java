package com.example.information;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InformationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_infomation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.sub), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //1. 실험요청한 인텐트 획득 -- 정보 획득
        Intent it = getIntent();
        String id = it.getStringExtra("id");

        //2. 각종 입력 위젯 획득
        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextAge = findViewById(R.id.editTextAge);

        RadioButton radioMale = findViewById(R.id.radioMale);
        RadioButton radioFeMale = findViewById(R.id.radioFeMale);

        CheckBox cBInfo = findViewById(R.id.cBInfo);
        CheckBox cBAI = findViewById(R.id.cBAI);
        CheckBox cBSecurity = findViewById(R.id.cBSecurity);

        //3. 평행버튼 획득 -- 클릭리스너 장착
        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 3-1. onClick() 할일
                //      - 인텐트 생성
                Intent it = new Intent();
                //      - 인텐트에 입력 정보를 설정 -- 전송
                String Name = editTextName.getText().toString();
                it.putExtra("name", name);
                it.putExtra("age", editTextAge.getText().toString());

                if (radioMale.isChecked())
                    it.putExtra("gender","남자");
                else
                    it.putExtra("gender","여자");

                String license = "";
                if (cBInfo.isChecked())
                    license += "\n  정보처리기사";
                if (cBAI.isChecked())
                    license += "\n 인공지능전문가";
                if (cBSecurity.isChecked())
                    license += "\n 정보보안기사";
                it.putExtra("license",license);
                setResult(RESULT_OK, it);
                finish();

            }
        });
}
