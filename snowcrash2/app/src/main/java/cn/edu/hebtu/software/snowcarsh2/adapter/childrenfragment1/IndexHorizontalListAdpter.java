package cn.edu.hebtu.software.snowcarsh2.adapter.childrenfragment1;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.edu.hebtu.software.snowcarsh2.R;
import cn.edu.hebtu.software.snowcarsh2.bean.IndexHorizontal;
import cn.edu.hebtu.software.snowcarsh2.bean.IndexVertical;

public class IndexHorizontalListAdpter extends BaseQuickAdapter<IndexHorizontal, BaseViewHolder> {
    public IndexHorizontalListAdpter(@LayoutRes int layoutResId, @Nullable List<IndexHorizontal> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IndexHorizontal item) {
        //可链式调用赋值
        helper.setText(R.id.index_horizontal_list_title, item.getTitle())
                .setText(R.id.index_horizontal_list_introduxe, item.getIntroduce())
                .setImageResource(R.id.index_horizontal_list_image, R.drawable.m1);

        //获取当前条目position
        //int position = helper.getLayoutPosition();
    }

}
