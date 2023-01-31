package com.bunny.woodenfish;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.graphics.drawable.Drawable;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bunny.woodenfish.utils.SaveData;
import com.bunny.woodenfish.view.ClearEditText;
import com.bunny.woodenfish.view.RandomAnimation;

import java.util.Objects;

/**
 * Project:  Bunny电子木鱼
 * Comments: 主界面Activity类
 * JDK version used: <JDK1.8>
 * Create Date：2023-01-30
 * Version: 1.0
 */

public class MainActivity extends AppCompatActivity {

    private ImageView woodenFish,setting;
    private TextView tvGongDeSum;
    private SoundPool soundPool;
    private int music;
    SaveData saveData = new SaveData(this);
    private int gongDeSum = 0;
    private final String TAG = "Wooden Fish LOG TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        woodenFish = findViewById(R.id.woodenFish);
        setting = findViewById(R.id.setting);
        tvGongDeSum = findViewById(R.id.tvGongDeSum);
        RandomAnimation plusOne = findViewById(R.id.plusOne);
        if (saveData.loadString("musicSw") == null){
            saveData.saveString("SW_on","vibratorSw");
        }
        if (saveData.loadString("musicSw") == null){
            saveData.saveString("SW_on","musicSw");
        }
        if (saveData.loadString("gongDeSum") == null){
            saveData.saveString("0","gongDeSum");
            tvGongDeSum.setText(saveData.loadString("gongDeSum"));
        }
        if (saveData.loadString("gongDeSum") != null){
            int currentGongDe = Integer.parseInt(saveData.loadString("gongDeSum"));
            if (currentGongDe > 999999){
                tvGongDeSum.setText("999999+");
            }
            if (currentGongDe >= 0 && currentGongDe <= 999999){
                tvGongDeSum.setText(String.valueOf(currentGongDe));
            }
            gongDeSum = currentGongDe;
        }
        Vibrator myVibrator = (Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
        soundPool = new SoundPool.Builder().setMaxStreams(10).build();
        music = soundPool.load(MainActivity.this, R.raw.sound, 1); //把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级
        woodenFish.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    int currentGongDe = Integer.parseInt(saveData.loadString("gongDeSum"));
                    ScaleAnimation animation = new ScaleAnimation(0.75f, 1.0f, 0.75f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(100);
                    animation.setFillAfter(true);
                    woodenFish.startAnimation(animation);
                    if (saveData.loadString("musicSw").equals("SW_on")){
                        soundPool.play(music, 1, 1, 0, 0, 1);
                    }
                    if (saveData.loadString("vibratorSw").equals("SW_on")){
                        myVibrator.cancel();
                        myVibrator.vibrate(new long[]{5, 30}, -1);
                    }
                    currentGongDe = currentGongDe + 1;
                    if (currentGongDe > 999999){
                        tvGongDeSum.setText("999999+");
                        saveData.saveString(String.valueOf(currentGongDe),"gongDeSum");
                    }
                    if (currentGongDe >= 0 && currentGongDe <= 999999){
                        tvGongDeSum.setText(String.valueOf(currentGongDe));
                        saveData.saveString(tvGongDeSum.getText().toString(),"gongDeSum");
                    }
                    plusOne.addView();
                }
                return false;
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation animation = new ScaleAnimation(0.75f, 1.0f, 0.75f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(100);
                animation.setFillAfter(true);
                setting.startAnimation(animation);
                settingDialog();
            }
        });
    }

    public void settingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog dialog = builder.create();
        LayoutInflater factory = LayoutInflater.from(MainActivity.this);
        final View view = factory.inflate(R.layout.setting_dialog,null);
        dialog.setTitle("Bunny电子木鱼 - 设置");
        dialog.setView(view);
        dialog.setCancelable(true);
        dialog.show();
        Button close = view.findViewById(R.id.close);
        Button save = view.findViewById(R.id.save);
        Button setZero = view.findViewById(R.id.setZero);
        ImageView vibratorSw = view.findViewById(R.id.vibratorSw);
        ImageView musicSw = view.findViewById(R.id.musicSw);
        EditText etGongDe = view.findViewById(R.id.etGongDe);
        etGongDe.setText(saveData.loadString("gongDeSum"));
        if (saveData.loadString("vibratorSw").equals("SW_off"))
        {
            vibratorSw.setImageDrawable(getDrawable(R.drawable.switch_off));
        }
        if (saveData.loadString("musicSw").equals("SW_off"))
        {
            musicSw.setImageDrawable(getDrawable(R.drawable.switch_off));
        }
        vibratorSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation animation = new ScaleAnimation(0.75f, 1.0f, 0.75f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(100);
                animation.setFillAfter(true);
                vibratorSw.startAnimation(animation);
                Drawable.ConstantState vibratorSwCurr = vibratorSw.getDrawable().getCurrent().getConstantState();
                //找到需要比较的图片ConstantState类对象
                Drawable.ConstantState switch_on = getDrawable(R.drawable.switch_on).getConstantState();
                Drawable.ConstantState switch_off = getDrawable(R.drawable.switch_off).getConstantState();
                if(vibratorSwCurr.equals(switch_on))
                {
                    vibratorSw.setImageDrawable(getDrawable(R.drawable.switch_off));
                }
                if(vibratorSwCurr.equals(switch_off))
                {
                    vibratorSw.setImageDrawable(getDrawable(R.drawable.switch_on));
                }
            }
        });
        musicSw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation animation = new ScaleAnimation(0.75f, 1.0f, 0.75f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(100);
                animation.setFillAfter(true);
                musicSw.startAnimation(animation);
                Drawable.ConstantState musicSwCurr = musicSw.getDrawable().getCurrent().getConstantState();
                //找到需要比较的图片ConstantState类对象
                Drawable.ConstantState switch_on = getDrawable(R.drawable.switch_on).getConstantState();
                Drawable.ConstantState switch_off = getDrawable(R.drawable.switch_off).getConstantState();
                if(musicSwCurr.equals(switch_on))
                {
                    musicSw.setImageDrawable(getDrawable(R.drawable.switch_off));
                }
                if(musicSwCurr.equals(switch_off))
                {
                    musicSw.setImageDrawable(getDrawable(R.drawable.switch_on));
                }
            }
        });
        setZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData.saveString("0","gongDeSum");
                tvGongDeSum.setText("0");
                etGongDe.setText("0");
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation animation = new ScaleAnimation(0.75f, 1.0f, 0.75f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(100);
                animation.setFillAfter(true);
                save.startAnimation(animation);
                String etGongDeStr = etGongDe.getText().toString();
                if (TextUtils.isEmpty(etGongDe.getText().toString())){
                    Toast.makeText(MainActivity.this, "输入为空，请输入数字", Toast.LENGTH_SHORT).show();
                    return ;
                }else{
                    if (!saveData.loadString("gongDeSum").equals(etGongDe.getText().toString())){
                        saveData.saveString(etGongDeStr,"gongDeSum");
                        int currentGongDe = Integer.parseInt(saveData.loadString("gongDeSum"));
                        if (currentGongDe > 999999){
                            tvGongDeSum.setText("999999+");
                        }
                        if (currentGongDe >= 0 && currentGongDe <= 999999){
                            tvGongDeSum.setText(String.valueOf(currentGongDe));
                        }
                    }
                    String vibratorSwString = "SW_on";
                    String musicSwString = "SW_on";
                    //获取当前图片ConstantState类对象
                    Drawable.ConstantState vibratorSwCurr = vibratorSw.getDrawable().getCurrent().getConstantState();
                    Drawable.ConstantState musicSwCurr = musicSw.getDrawable().getCurrent().getConstantState();
                    Drawable.ConstantState switch_off = getDrawable(R.drawable.switch_off).getConstantState();
                    if(vibratorSwCurr.equals(switch_off))
                    {
                        vibratorSwString = "SW_off";
                    }
                    if(musicSwCurr.equals(switch_off))
                    {
                        musicSwString = "SW_off";
                    }
                    saveData.saveString(vibratorSwString,"vibratorSw");
                    saveData.saveString(musicSwString,"musicSw");
                    dialog.dismiss();
                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScaleAnimation animation = new ScaleAnimation(0.75f, 1.0f, 0.75f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(100);
                animation.setFillAfter(true);
                close.startAnimation(animation);
                dialog.dismiss();
            }
        });
    }

    //再按一次退出提示
    long exitTime = 0;
    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, R.string.tap_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
            return;
        }
        finish();
    }
}