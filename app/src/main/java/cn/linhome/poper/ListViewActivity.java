package cn.linhome.poper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.fanwe.lib.looper.FLooper;
import com.fanwe.lib.looper.impl.FSimpleLooper;
import com.fanwe.library.adapter.SDSimpleAdapter;
import com.fanwe.library.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import cn.linhome.lib.poper.FPoper;

public class ListViewActivity extends AppCompatActivity
{
    private ListView lv_content;
    private ViewGroup fl_container;

    private FLooper mLooper = new FSimpleLooper();
    private WeakHashMap<View, FPoper> mMapViewPoper = new WeakHashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_listview);
        lv_content = findViewById(R.id.lv_content);
        fl_container = findViewById(R.id.fl_container);

        List<String> listModel = new ArrayList<>();
        for (int i = 0; i < 100; i++)
        {
            listModel.add(String.valueOf(i));
        }
        mAdapter.setData(listModel);
        lv_content.setAdapter(mAdapter);

        mLooper.setInterval(1000);
        mLooper.start(new Runnable()
        {
            @Override
            public void run()
            {
                LogUtil.i("View:" + mMapViewPoper.size() + " " +
                        "Child:" + fl_container.getChildCount());
            }
        });
    }

    private FPoper getPoper(View view)
    {
        FPoper poper = mMapViewPoper.get(view);
        if (poper == null)
        {
            poper = new FPoper(ListViewActivity.this)
                    .setDebug(true)
                    .setContainer(fl_container)
                    .setPopView(R.layout.view_pop)
                    .setTarget(view);
            mMapViewPoper.put(view, poper);
        }
        return poper;
    }

    private SDSimpleAdapter<String> mAdapter = new SDSimpleAdapter<String>(null, this)
    {
        @Override
        public int getLayoutId(int position, View convertView, ViewGroup parent)
        {
            return R.layout.item_list_view;
        }

        @Override
        public void bindData(int position, View convertView, ViewGroup parent, String model)
        {
            Button btn1 = get(R.id.btn1, convertView);
            Button btn2 = get(R.id.btn2, convertView);
            Button btn3 = get(R.id.btn3, convertView);
            Button btn4 = get(R.id.btn4, convertView);
            Button btn5 = get(R.id.btn5, convertView);

            getPoper(btn1).attach(true);
            getPoper(btn2).attach(true);
            getPoper(btn3).attach(true);
            getPoper(btn4).attach(true);
            getPoper(btn5).attach(true);
        }
    };

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mLooper.stop();
    }
}
