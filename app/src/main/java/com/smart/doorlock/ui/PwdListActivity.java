package com.smart.doorlock.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;

import com.smart.doorlock.adapter.PwdAdapter;
import com.smart.doorlock.entity.PwdEntity;
import com.smart.doorlock.ui.base.BaseActivity;
import com.smart.doorlock.util.DisplayUtil;
import com.smart.doorlock.widget.swipemenulistview.SwipeMenuCreator;
import com.smart.doorlock.widget.swipemenulistview.SwipeMenuItem;
import com.smart.doorlock.widget.swipemenulistview.SwipeMenuListView;
import com.smart.lock.R;
import com.smart.doorlock.widget.swipemenulistview.SwipeMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by sunfusheng on 2015/6/25.
 */
public class PwdListActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.smlv_listView)
    SwipeMenuListView smlvListView;

    private List<PwdEntity> pwdList;
    private PwdAdapter pwdAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_list);
        ButterKnife.inject(this);

        initActionBar();
        initData();
        initView();
    }

    private void initActionBar() {
        mToolbar.setTitle("密码配置");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initData() {
        pwdList = new ArrayList<>();
        pwdList.add(new PwdEntity("张三", "123456", "第一个密码"));
        pwdList.add(new PwdEntity("李四", "123456", "第一个密码"));
        pwdList.add(new PwdEntity("王五", "123456", "第一个密码"));
        pwdList.add(new PwdEntity("小六", "123456", "第一个密码"));
        pwdList.add(new PwdEntity("小七", "123456", "第一个密码"));
    }

    private void initView() {
        initXlistView();
        pwdAdapter = new PwdAdapter(this, pwdList);
        smlvListView.setAdapter(pwdAdapter);
        setSwipeMenuCreator();
        initSwipeMenuItemClickListener();
    }

    private void initXlistView() {
        smlvListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    private void setSwipeMenuCreator() {
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
                openItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                openItem.setWidth(DisplayUtil.dip2px(PwdListActivity.this, 90));
                openItem.setTitle("删除");
                openItem.setTitleSize(16);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);
            }
        };
        smlvListView.setMenuCreator(creator);
    }

    private void initSwipeMenuItemClickListener() {
        smlvListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        deleteNewsListItem(position);
                        break;
                }
                return true;
            }
        });
    }

    private void deleteNewsListItem(int position) {
        PwdEntity entity = pwdList.get(position);
        pwdList.remove(entity);
        pwdAdapter.notifyDataSetChanged();
    }

}