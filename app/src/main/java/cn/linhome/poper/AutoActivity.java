package cn.linhome.poper;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fanwe.lib.utils.FViewUtil;
import com.fanwe.library.activity.SDBaseActivity;

import cn.linhome.lib.poper.FPoper;
import cn.linhome.lib.poper.layouter.BoundLayouter;
import cn.linhome.lib.poper.layouter.FixBoundLayouter;
import cn.linhome.lib.poper.layouter.ViewBoundLayouter;

public class AutoActivity extends SDBaseActivity
{
    private Button btn_big;
    private Button btn_small;

    private Button btn_pop;

    private TestPopView mPopView;

    @Override
    protected void init(Bundle savedInstanceState)
    {
        setContentView(R.layout.act_auto);
        btn_big = findViewById(R.id.btn_big);
        btn_small = findViewById(R.id.btn_small);
        btn_pop = findViewById(R.id.btn_pop);

        btn_big.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FViewUtil.setSize(btn_pop, btn_pop.getWidth() + 100, btn_pop.getHeight() + 100);
            }
        });
        btn_small.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FViewUtil.setSize(btn_pop, btn_pop.getWidth() - 100, btn_pop.getHeight() - 100);
            }
        });

        btn_pop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getPopView().getPoper().attach(!getPopView().getPoper().isAttached());
            }
        });
    }

    public TestPopView getPopView()
    {
        if (mPopView == null)
        {
            mPopView = new TestPopView(this);
            mPopView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            mPopView.getPoper()
                    .addPopLayouter(new ViewBoundLayouter(BoundLayouter.Bound.Width, btn_pop)
                            .setDebug(true))
                    .addPopLayouter(new FixBoundLayouter(BoundLayouter.Bound.Height)
                            .setDebug(true))
                    .setTarget(btn_pop)
                    .setPosition(FPoper.Position.BottomOutsideCenter);
        }
        return mPopView;
    }
}
