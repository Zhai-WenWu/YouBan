package cn.bjhdltcdn.p2plive.ui.adapter;

import android.app.Activity;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import cn.bjhdltcdn.p2plive.R;
import cn.bjhdltcdn.p2plive.app.App;
import cn.bjhdltcdn.p2plive.model.HobbyInfo;
import cn.bjhdltcdn.p2plive.model.OrganizationInfo;
import cn.bjhdltcdn.p2plive.ui.viewholder.BaseViewHolder;

/**
 * 活动了表兴趣标签列表
 */

public class ActiveListOrganizationRecyclerViewAdapter extends BaseRecyclerAdapter {
    private List<OrganizationInfo> list;
    private Activity mActivity;
    private RequestOptions options;

    public ActiveListOrganizationRecyclerViewAdapter(Activity mActivity) {
        this.mActivity = mActivity;
        list = new ArrayList<OrganizationInfo>();
        options = new RequestOptions()
                .fitCenter()
                .placeholder(R.mipmap.error_bg)
                .error(R.mipmap.error_bg);
    }

    public void setList(List<OrganizationInfo> list) {
        this.list = list;
        if (list != null) {
            this.list = list;
        }
    }

    public List<OrganizationInfo> getList() {
        return list;
    }

    public OrganizationInfo getItem(int position){
        if(list!=null&&list.size()>0){
            return list.get(position);
        }else{
            return null;
        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        ImageHolder holder = new ImageHolder(LayoutInflater.from(App.getInstance()).inflate(R.layout.active_list_organization_item_layout, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        if (getItemCount() > 0) {
            if (holder instanceof ImageHolder) {
                final ImageHolder imageHolder = (ImageHolder) holder;
                final TextView tabTextView = imageHolder.tabTextView;
                final OrganizationInfo organizationInfo = list.get(position);
                tabTextView.setText(organizationInfo.getOrganName());
            }
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ImageHolder extends BaseViewHolder {
        TextView tabTextView;

        public ImageHolder(View itemView) {
            super(itemView);
            tabTextView = (TextView) itemView.findViewById(R.id.post_label_two_text);
        }

    }


    public void onDestroy() {
        if (mActivity != null) {
            mActivity = null;
        }

        if (list != null) {
            list.clear();
        }
        list = null;
    }

}