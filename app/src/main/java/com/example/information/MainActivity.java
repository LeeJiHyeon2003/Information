package com.example.information;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView tvResultLabel, tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // 0. TextView 찾아서 멤버필드에 저장
        tvResultLabel = findViewById(R.id.tVResultLabel);
        tvResult = findViewById(R.id.tVResult);

        EditText editTextID = findViewById(R.id.editTextID);
        // 1. 명령버튼 획득: 클릭리스너 장착
        Button btnRequest = findViewById(R.id.btnRequest);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //인텐트 생성 -- 정보를 설정 -- 2번째 액티비티 실행 요청
                Intent it = new Intent(getApplicationContext(), InformationActivity.class);
                String str = editTextID.getText().toString();
                it.putExtra("id", str);
                startActivityForResult(it, 1);
            }
        });
        findViewById(R.id.btnEnd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // 2. 결과 처리 메소드 오버라이징
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1  && resultCode == RESULT_OK) {
            tvResultLabel.setText("결과\n출력");
            String str = "아이디:";
            str += data.getStringExtra("id");
            str += "\n이름:" + data.getStringExtra("name");
            str += "\n나이:" + data.getStringExtra("age");
            str += "\n성별:" + data.getStringExtra("gender");
            str += "\n자격증:" + data.getStringExtra("license");
            tvResult.setText(str);

        }
    }
}