package zhangtianyagn.com.cn.fragmentdem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import zhangtianyagn.com.cn.fragmentdem.fragment.SearchFragment;
import zhangtianyagn.com.cn.fragmentdem.fragment.SeeFragment;
import zhangtianyagn.com.cn.fragmentdem.fragment.SendFragment;

/**
 * 测试当activity横屏后导致fragment重复的问题，以及展示fragment add-hide-show的用法
 */
public class MainActivity extends AppCompatActivity {

    // 上一次界面 onSaveInstanceState 之前的tab被选中的状态 key 和 value
    private static final String PRV_SELINDEX = "PREV_SELINDEX";
    private int selindex = 0;

    // Fragment的TAG 用于解决app内存被回收之后导致的fragment重叠问题
    private static final String[] FRAGMENT_TAG = { "seefrag", "searchfrag",
            "sendfrag" };
    public static final int FRAGMENT_SEE = 0;// 是否可见界面
    public static final int FRAGMENT_SEARCH = 1;// 搜索设备界面
    public static final int FRAGMENT_SEND = 2;// 发送界面
    private FragmentManager mManager;

    private Fragment mSeeFragment;
    private Fragment mSearchFragment;
    private Fragment mSendFragment;
    // 是否可见界面
    private RelativeLayout mSee;
    private ImageView iv_see;
    private TextView tv_see;
    // 搜索设备界面
    private RelativeLayout mSearch;
    private ImageView iv_search;
    private TextView tv_search;
    // 发送界面
    private RelativeLayout mSend;
    private ImageView iv_send;
    private TextView tv_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        mManager = getFragmentManager();
        if (savedInstanceState != null) { // 读取上一次界面Save的时候tab选中的状态,如果注释掉这个代码块那么就会出现问题了

            Log.e("tag"," =================pre saveInstanceState================= " + selindex);
            selindex = savedInstanceState.getInt(PRV_SELINDEX, selindex);
            Log.e("tag"," =================saveInstanceState================= " + selindex);
            //通过tag获取上次的fragment状态
            mSeeFragment = mManager.findFragmentByTag(FRAGMENT_TAG[FRAGMENT_SEE]);
            mSearchFragment = mManager.findFragmentByTag(FRAGMENT_TAG[FRAGMENT_SEARCH]);
            mSendFragment = mManager.findFragmentByTag(FRAGMENT_TAG[FRAGMENT_SEND]);
        }
        // 选中index
        initFragment(selindex);
        setListener();// 设置点击事件
    }

    private void init() {
        // 是否可见界面
        mSee = findViewById(R.id.rel_see);
        iv_see = findViewById(R.id.iv_see);
        tv_see = findViewById(R.id.tv_see);
        // 搜索设备界面
        mSearch =  findViewById(R.id.rel_search);
        iv_search =findViewById(R.id.iv_search);
        tv_search = findViewById(R.id.tv_search);
        // 发送界面
        mSend = findViewById(R.id.rel_send);
        iv_send = findViewById(R.id.iv_send);
        tv_send = findViewById(R.id.tv_send);
    }

    private void setListener() {
        mSee.setOnClickListener(clickListener);// 是否可见点击事件
        mSearch.setOnClickListener(clickListener);// 搜索设备点击事件
        mSend.setOnClickListener(clickListener);// 发送点击事件
    }

    private OnClickListener clickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rel_see:
                    initFragment(FRAGMENT_SEE);
                    break;
                case R.id.rel_search:
                    initFragment(FRAGMENT_SEARCH);
                    break;
                case R.id.rel_send:
                    initFragment(FRAGMENT_SEND);
                    break;
                default:
                    break;
            }
        }
    };

    protected void initFragment(int i) {
        // 开启事务
        FragmentTransaction transaction = mManager.beginTransaction();
        // 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
        restartBotton();
        // 先隐藏所有Fragment，下面是按需加载
        hideFragment(transaction);

        switch (i) {
            case FRAGMENT_SEE:

                if (mSeeFragment == null || !mSeeFragment.isAdded()) {
                    mSeeFragment = new SeeFragment();
                    transaction.add(R.id.framelayout, mSeeFragment,
                            FRAGMENT_TAG[FRAGMENT_SEE]);
                } else {
                    transaction.show(mSeeFragment);
                }
                // ImageView和TetxView置为蓝色
                iv_see.setImageResource(R.drawable.icon_see02);
                tv_see.setTextColor(getResources().getColor(R.color.blue_color));
                selindex = FRAGMENT_SEE;
                break;
            case FRAGMENT_SEARCH:

                if (mSearchFragment == null) {
                    mSearchFragment = new SearchFragment();
                    transaction.add(R.id.framelayout, mSearchFragment,
                            FRAGMENT_TAG[FRAGMENT_SEARCH]);
                } else {
                    transaction.show(mSearchFragment);
                }
                // ImageView和TetxView置为蓝色
                iv_search.setImageResource(R.drawable.icon_search02);
                tv_search.setTextColor(getResources().getColor(R.color.blue_color));
                selindex = FRAGMENT_SEARCH;
                break;
            case FRAGMENT_SEND:

                if (mSendFragment == null) {
                    mSendFragment = new SendFragment();
                    transaction.add(R.id.framelayout, mSendFragment,
                            FRAGMENT_TAG[FRAGMENT_SEND]);
                } else {
                    transaction.show(mSendFragment);
                }
                // ImageView和TetxView置为蓝色
                iv_send.setImageResource(R.drawable.icon_choose02);
                tv_send.setTextColor(getResources().getColor(R.color.blue_color));
                selindex = FRAGMENT_SEND;
                break;
            default:
                break;
        }

        Log.e("tag"," =================search Inex================= " + selindex);
        // 提交事务
        transaction.commit();
    }

    private void restartBotton() {
        // ImageView置为灰色
        iv_see.setImageResource(R.drawable.icon_see01);
        iv_search.setImageResource(R.drawable.icon_search01);
        iv_send.setImageResource(R.drawable.icon_choose01);
        // TextView置为灰色
        tv_see.setTextColor(getResources().getColor(R.color.black_color));
        tv_search.setTextColor(getResources().getColor(R.color.black_color));
        tv_send.setTextColor(getResources().getColor(R.color.black_color));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(PRV_SELINDEX,selindex);
    }

    // 隐藏Fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (mSeeFragment != null) {
            transaction.hide(mSeeFragment);
        }
        if (mSearchFragment != null) {
            transaction.hide(mSearchFragment);
        }
        if (mSendFragment != null) {
            transaction.hide(mSendFragment);
        }

    }
}