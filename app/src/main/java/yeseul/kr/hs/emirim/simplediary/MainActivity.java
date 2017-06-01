package yeseul.kr.hs.emirim.simplediary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    DatePicker date;
    EditText edit;
    Button but;
    String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = (DatePicker)findViewById(R.id.date_pick);
        edit = (EditText)findViewById(R.id.edit);
        but = (Button)findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FileOutputStream fOut = openFileOutput(fileName, Context.MODE_PRIVATE));
                    String str = edit.getText().toString();
                    fOut.write(str.getBytes());
                    Toast.makeText(MainActivity.this, "정상적으로 " + fileName + "파일이 저장되었습니다.", Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Calendar cal = Calendar.getInstance(); //추상클래스이기 때문에 new 연산자로 객체 생성을 못함
        int year = cal.get(Calendar.YEAR);
        final int month = cal.get(Calendar.MONTH); // +1하지 않기
        final int day = cal.get(Calendar.DATE);

        date.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = year + "_" + (month + 1) + "_" + day + ".txt";
                String readData = readDiary(fileName);
                edit.setText(readData);
                but.setEnabled(true);
            }
        });
    }
    public String readDiary(String fileName) {
        String diaryStr = null;
        FileInputStream fIn = null;
        try {
            fIn = openFileInput(fileName);
            byte[] buf = new byte[500]; //500바이트씩 읽어온다는 의미
            fIn.read(buf); //IOEception 예외처리가 필요함
            diaryStr = new String(buf).trim(); //읽어온 Byte값을 String으로 변환하는 방법, trim() 메소드를 사용하면 500Byte보다 적게 입력했을 시 남는 뒷부분의 공백들을 없애줌
            but.setText("수정 하기");
        } catch (FileNotFoundException e) {
            edit.setText("일기가 존재하지 않습니다.");
            but.setText("새로 저장"); //버튼의 라벨을 "새로 저장"으로 설정
        } catch (IOException e) { //예외처리

        }

        return diaryStr;
    }
}
