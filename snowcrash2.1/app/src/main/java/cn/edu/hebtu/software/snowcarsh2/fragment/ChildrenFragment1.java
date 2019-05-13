package cn.edu.hebtu.software.snowcarsh2.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import cn.edu.hebtu.software.snowcarsh2.R;
import cn.edu.hebtu.software.snowcarsh2.adapter.childrenfragment1.IndexHorizontalListAdpter;
import cn.edu.hebtu.software.snowcarsh2.adapter.childrenfragment1.IndexVerticalListAdapter;
import cn.edu.hebtu.software.snowcarsh2.bean.IndexHorizontal;
import cn.edu.hebtu.software.snowcarsh2.bean.IndexVertical;
import cn.edu.hebtu.software.snowcarsh2.libs.GlideImageLoader;

public class ChildrenFragment1 extends Fragment {
    public static ChildrenFragment1 newInstance() {
        ChildrenFragment1 fragment = new ChildrenFragment1();
        return fragment;

    }

    private View view;
    //index页面RecyclerView
    private RecyclerView recyclerView;
    //纵向列表数据以及adapter.recyclerView
    private RecyclerView recyclerHorizontal;
    private List<IndexVertical> indexVerticals;
    private IndexVerticalListAdapter indexVerticalListAdapter;
    //横向列表数据以及adapter
    private List<IndexHorizontal> indexHorizontals;
    private IndexHorizontalListAdpter indexHorizontalListAdpter;
    //baner图片地址以及标题
    private Banner mBanner;
    List<String> images = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    //刷新车速
    private int count;
    //轮播图,新闻标题,新闻横向列表,文章标题,abnner
    private View headerBannerView;
    private View newsHeadlinesView;
    private View articleHeadlinesView;
    private View recyclerHorizontalView;
    private View bannerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.childrenfragment1,container,false);
        init();
        //初始化RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_vertical_vertical);

        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerHorizontal = (RecyclerView) recyclerHorizontalView.findViewById(R.id.recycler_horizontal_vertical);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(recyclerHorizontalView.getContext());
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerHorizontal.setLayoutManager(layoutManager2);
        indexHorizontalListAdpter = new IndexHorizontalListAdpter(R.layout.index_horizontal_list, indexHorizontals);
        recyclerHorizontal.setAdapter(indexHorizontalListAdpter);

        //创建适配器
        indexVerticalListAdapter = new IndexVerticalListAdapter(R.layout.index_vertical_list, indexVerticals);
        //动画效果
//            public static final int ALPHAIN = 0x00000001;
//            /**
//             * Use with {@link #openLoadAnimation}
//             */
//            public static final int SCALEIN = 0x00000002;
//            /**
//             * Use with {@link #openLoadAnimation}
//             */
//            public static final int SLIDEIN_BOTTOM = 0x00000003;
//            /**
//             * Use with {@link #openLoadAnimation}
//             */
//            public static final int SLIDEIN_LEFT = 0x00000004;
//            /**
//             * Use with {@link #openLoadAnimation}
//             */
//            public static final int SLIDEIN_RIGHT = 0x00000005;
//
//
//            //使用缩放动画
//            adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //默认动画
        indexVerticalListAdapter.openLoadAnimation();
        //设置重复执行动画
        indexVerticalListAdapter.isFirstOnly(false);
        //设置下拉刷新
        indexVerticalListAdapter.setUpFetchEnable(true);
        //添加View
        indexVerticalListAdapter.addHeaderView(headerBannerView);
        indexVerticalListAdapter.addHeaderView(newsHeadlinesView);
        indexVerticalListAdapter.addHeaderView(recyclerHorizontalView);
        indexVerticalListAdapter.addHeaderView(articleHeadlinesView);
        //给RecyclerView设置适配器
        recyclerView.setAdapter(indexVerticalListAdapter);


        setBanner();

        //条目点击事件
        indexVerticalListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Toast.makeText(getContext(), "点击了第" + (position + 1) + "条条目", Toast.LENGTH_SHORT).show();
            }
        });

        //条目长按事件
        indexVerticalListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

                Toast.makeText(getContext(), "长按了第" + (position + 1) + "条条目", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

//        indexVerticalListAdapter.setUpFetchListener(new BaseQuickAdapter.UpFetchListener() {
//            @Override
//            public void onUpFetch() {
//                startUpFetch();
//            }
//        });


        return view;

    }


    public void init(){

        //刷新次数(后期更改)
        count=1;
        //轮播图,新闻标题,新闻横向列表,文章标题
        headerBannerView = LayoutInflater.from(getContext()).inflate(R.layout.banner_view, null);
        newsHeadlinesView = LayoutInflater.from(getContext()).inflate(R.layout.news_headlines, null);
        articleHeadlinesView = LayoutInflater.from(getContext()).inflate(R.layout.article_headlines,null);
        recyclerHorizontalView= LayoutInflater.from(getContext()).inflate(R.layout.recycler_horizontal_view, null);
        bannerView=LayoutInflater.from(getContext()).inflate(R.layout.banner, null);


        //banner数据
        images.add(new String("https://alioss.gcores.com/uploads/image/c7c67598-1012-41aa-8452-6f31e11fc590_watermark.jpg"));
        titles.add(new String("在迪士尼结婚是一种什么感受（上篇）"));
        images.add(new String("https://alioss.gcores.com/uploads/image/2e3573fc-0faa-40dd-b262-5d77e9bd288f_watermark.jpg"));
        titles.add(new String("《Spatoons》在7月6号的直面会（一）"));
        images.add(new String("http://p0.so.qhimgs1.com/bdr/_240_/t0180a50796dc19564a.jpg"));
        titles.add(new String("十分钟带你了解Vtuber战国时代"));
        images.add(new String("https://ps.ssl.qhimg.com/sdmt/306_135_100/t01129defbd1524486f.jpg"));
        titles.add(new String("《真菌洞窟（FuctionCaver)》开发日志"));

        //纵向列表数据
        //模拟的数据（实际开发中一般是从网络获取的）
        indexVerticals = new ArrayList<>();
        IndexVertical indexVertical;
        for (int i = 0; i < 15; i++) {
            indexVertical = new IndexVertical();
            indexVertical.setTitle("我是第" + i + "条标题");
            indexVertical.setIntroduce("第" + i + "条内容");
            indexVerticals.add(indexVertical);
        }

        //横向列表数据
        indexHorizontals=new ArrayList<>();
        IndexHorizontal indexHorizontal;
        for(int i=0;i<10;i++){
            indexHorizontal = new IndexHorizontal();
            indexHorizontal.setTitle("我是第" + i + "条标题");
            indexHorizontal.setIntroduce("第" + i + "条内容");
            indexHorizontals.add(indexHorizontal);
        }
    };

    public void setBanner(){

        mBanner = (Banner) headerBannerView.findViewById(R.id.banner);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    };
}
