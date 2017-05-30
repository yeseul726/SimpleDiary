package yeseul.kr.hs.emirim.simplediary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

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
    public String readDiary(String fileName){
        return null;
    }
}
