package com.kitesoft.ex13alertdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //2.2~ 실습) 리스트형태의 AlertDialog 실습때 사용할 String배열 멤버변수
    String[] items= new String[]{"Apple", "Banana", "Orange"};

    //2.4 실습) 체크박스를 가진 리스트형태의 AlertDialog 실습때 사용할 boolean 배열 멤버변수
    boolean[] checked= new boolean[]{true, false, true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBtn(View v){

        //AlertDialog를 만들어주는 건축가(Builder)객체 생성
        AlertDialog.Builder builder= new AlertDialog.Builder(this);

        //1. 건축가객체(Builder)에게 만들고자하는 AlertDialog의 제목과 아이콘을 설정
        builder.setTitle("다이얼로그");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        //2. 만들고자하는 AlertDialog의 메세지를 설정
//        builder.setMessage("Do you wanna Quit??");

        //2.2) AlertDialog의 메세지 영역에 단순 문자열이 아닌 항목(list)형태로 메세지영역 설정해 보기
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//                Toast t= Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT);
//                t.show();
//            }
//        });

        //2.3) 라디오버튼이 있는 항목형태의 설정해 보기
//        builder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//                Toast t= Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT);
//                t.show();
//
//            }
//        });

        //2.4) 체크박스버튼이 있는 리스트형태의 설정
//        builder.setMultiChoiceItems(items, checked, new DialogInterface.OnMultiChoiceClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which, boolean b) {
//                checked[which]= b;
//
//            }
//        });


        //2.5) 커스텀뷰로 메세지영역 설정하기
        LayoutInflater inflater= getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null);

        builder.setView(view);


        //3. 다이얼로그의 하단에 붙일 버튼 설정 [ 최소0개 ~ 최대3개까지 추가가능 : Positive, Negative, Neutral ]
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast t= Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT);
                t.show();
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast t= Toast.makeText(MainActivity.this, "CANCEL", Toast.LENGTH_SHORT);
                t.show();
            }
        });


        //4. 건축가객체(Builder)에게 위에서 설정한 내역으로 AlertDialog 객체를 생성해 달라고 요청
        AlertDialog dialog= builder.create();

        //5. 다이얼로그 보이기!!!
        dialog.setCanceledOnTouchOutside(false);// 다이얼로그의 영역 밖을 터치하였을 때 다이얼로그가 꺼지는 것을 방지
        dialog.show(); //다이얼로그 보이기

        // ** 참고로 아래 설정은 디바이스의 [뒤로가기]버튼을 클릭해도 다이얼로그 꺼지지 않음.
        //dialog.setCancelable(false); //[ 다이얼로그의 버튼들을 누르거나,  dialog.dismiss() 를 호출해야 꺼짐 ]


        // ## 여기서부터는  2.5)실습의 추가내용 #############################
        // 커스텀뷰로 부터 뷰객체들 참조해오기
        dialogEt= view.findViewById(R.id.dialog_et);
        dialogTv= view.findViewById(R.id.dialog_tv);
    }

    //멤버변수
    EditText dialogEt;
    TextView dialogTv;

    //커스텀뷰의 버튼(dialog.xml문서안에 있는 Button)에 설정된 onClick속성의 콜백메소드
    public void clickDialogBtn(View v){
        //MainActivity가 보여주고 있는 activit_main.xml에 EditText, TextView가 있는 것이 아니라서 찾을 수(findView) 없음.
        //EditText et= findViewById(R.id.dialog_et);
        //TextView tv= findViewById(R.id.dialog_tv);
        //tv.setText( et.getText().toString() );

        //이를 찾으려면 EditText, TextView를 가지고 있는 View에게 찾아달라고 해야함.
        //저 위에 builder에게 setView할때 주어졌던 view( inflate를 통해 생성된 )가 dialog.xml의 root Elemnent(LinearLayout)이므로..
        //이 view를 통해 미리 두개의 뷰를 얻어내었어야함.
        dialogTv.setText( dialogEt.getText().toString() );

    }
}
