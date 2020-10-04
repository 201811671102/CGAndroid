package pre.cg.cgandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class UserActivity extends AppCompatActivity {
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_IMAGE_ID = "user_image-id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        /*接收User数据*/
        Intent intent = getIntent();
        String userName = intent.getStringExtra(USER_NAME);
        int userImageId = intent.getIntExtra(USER_IMAGE_ID,0);

        Toolbar toolbar = findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        ImageView imageView = findViewById(R.id.user_image_view);
        TextView textView = findViewById(R.id.user_content_text);

        /*设置标题栏*/
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            /*显示导航按钮*/
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(userName);
        Glide.with(this).load(userImageId).into(imageView);
        /*设置主题内容*/
            /*生成数据*/
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<1000;i++){
            stringBuilder.append(userName);
        }
        textView.setText(stringBuilder.toString());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}