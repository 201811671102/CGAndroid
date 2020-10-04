package pre.cg.cgandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pre.cg.cgandroid.adapter.UserAdapter;
import pre.cg.cgandroid.pojo.User;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ImageView imageView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;

    private User[] users = {
            new User("苏苏",R.drawable.image_bg),
            new User("苏苏2",R.drawable.image_fo),
            new User("苏苏3",R.drawable.image_forn),
            new User("苏苏4",R.drawable.icon_01),
            new User("苏苏5",R.drawable.icon_02),
    };

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.Toolbar1);
        setSupportActionBar(toolbar);

        /*设置图片显示在最上层*/
        imageView = findViewById(R.id.imagebtn);
        imageView.bringToFront();

        /*
        * Toolbar最左侧的按钮是HomeAsUp按钮，默认为返回
        * id 默认为android.R.id.home
        * */
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            //显示导航按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            //设置导航按钮
            actionBar.setHomeAsUpIndicator(R.drawable.more);
        }

        /*navigationView*/
        navigationView = findViewById(R.id.nav_view);
        //设置默认选中
        navigationView.setCheckedItem(R.id.nav_call);
        //绑定点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //关闭滑动菜单
                drawerLayout.closeDrawers();
                return true;
            }
        });
        /*悬浮框*/
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v,"Snackbar",Snackbar.LENGTH_SHORT)
                        .setAction("undo",new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(MainActivity.this, "undo", Toast.LENGTH_SHORT).show();
                            }
                        });
                snackbar.show();
            }
        });
        /*填充内容*/
        initUser();
        recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);

        /*下拉刷新*/
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        //设置下拉刷新进度条颜色
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(()->{
                    try {
                        Thread.sleep(2000);
                        runOnUiThread(()->{
                            initUser();
                            userAdapter.notifyDataSetChanged();
                            //刷新结束
                            swipeRefreshLayout.setRefreshing(false);
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        });
    }
    private void initUser(){
        userList.clear();
        for (int i = 0;i<50;i++){
            Random random = new Random();
            userList.add(users[random.nextInt(users.length)]);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            //导航按钮
            case android.R.id.home:
                //展示滑动菜单，要求传入一个Gravity参数，保证与布局文件定义一致
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}