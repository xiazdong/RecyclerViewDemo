package me.xiazdong.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import me.xiazdong.recyclerviewdemo.demo1.Activity1;
import me.xiazdong.recyclerviewdemo.demo2.Activity2;
import me.xiazdong.recyclerviewdemo.demo3.Activity3;
import me.xiazdong.recyclerviewdemo.demo4.Activity4;
import me.xiazdong.recyclerviewdemo.demo5.Activity5;
import me.xiazdong.recyclerviewdemo.demo6.Activity6;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jump(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.btn_1:
                intent.setClass(this,Activity1.class);
                break;
            case R.id.btn_2:
                intent.setClass(this,Activity2.class);
                break;
            case R.id.btn_3:
                intent.setClass(this, Activity3.class);
                break;
            case R.id.btn_4:
                intent.setClass(this, Activity4.class);
                break;
            case R.id.btn_5:
                intent.setClass(this, Activity5.class);
                break;
            case R.id.btn_6:
                intent.setClass(this, Activity6.class);
                break;
        }
        startActivity(intent);
    }

}
