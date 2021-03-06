package cn.bjhdltcdn.p2plive.mvp.presenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.util.LinkedHashMap;
import java.util.Map;

import cn.bjhdltcdn.p2plive.api.ApiData;
import cn.bjhdltcdn.p2plive.api.InterfaceUrl;
import cn.bjhdltcdn.p2plive.callback.JsonCallback;
import cn.bjhdltcdn.p2plive.httpresponse.GetNearHotPostListResponse;
import cn.bjhdltcdn.p2plive.mvp.contract.GetNearHotPostListContract;
import cn.bjhdltcdn.p2plive.mvp.view.BaseView;

public class GetNearHotPostListPresenter implements GetNearHotPostListContract.Presenter {

    private BaseView mView ;

    public GetNearHotPostListPresenter(BaseView mView) {
        this.mView = mView;
    }


    @Override
    public void cancelTag(String tag) {
        OkGo.getInstance().cancelTag(tag);
    }

    @Override
    public void onDestroy() {
        if (mView != null) {
            mView = null;
        }
    }

    @Override
    public void getNearHotPostList(long userId, int sort,int pageSize,int pageNumber){
        Map map = new LinkedHashMap(1);
        map.put("userId",userId);
        map.put("sort",sort);
        map.put("pageSize",pageSize);
        map.put("pageNumber",pageNumber);
        String tag = mView.getClass().getSimpleName();

        try {
            ApiData.getInstance().postData(InterfaceUrl.URL_GETNEARHOTPOSTLIST,tag, map, new JsonCallback<GetNearHotPostListResponse>(GetNearHotPostListResponse.class) {

                @Override
                public void onStart(Request request) {
                    super.onStart(request);
                    if (mView != null) {
                        mView.showLoading();
                    }
                }

                @Override
                public void onSuccess(Response<GetNearHotPostListResponse> response) {
                    if (mView != null) {
                        mView.updateView(InterfaceUrl.URL_GETNEARHOTPOSTLIST,response.body());
                    }
                }

                @Override
                public void onError(Response response) {
                    super.onError(response);

                    if (mView != null) {
                        mView.updateView(InterfaceUrl.URL_GETNEARHOTPOSTLIST,response.body());
                    }


                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    if (mView != null) {
                        mView.hideLoading();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
